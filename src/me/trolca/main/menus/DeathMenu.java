package me.trolca.main.menus;

import me.trolca.main.HUD;
import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Menu;
import me.trolca.main.enums.GameState;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.handlers.LevelHandler;
import me.trolca.main.objects.enemies.BasicEnemy;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DeathMenu extends Menu {

    private GameHandler gameHandler;
    private HUD hud;

    private boolean[] isOn = new boolean[2];

    public DeathMenu(GameHandler gameHandler, HUD hud){
        this.gameHandler = gameHandler;
        this.hud = hud;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(new Font("Roboto", Font.BOLD, 75));

        g2.setColor(Color.RED);

        g2.drawString("You died!", 190, 100);

        if(isOn[0]) g2.setColor(Color.LIGHT_GRAY);
        else g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));



        g2.drawRect(175, 160, 350, 110);
        g2.setFont(new Font("Roboto", Font.PLAIN, 60));
        g2.drawString("Try again", 225, 232);

        if(isOn[1]) g2.setColor(Color.LIGHT_GRAY);
        else g2.setColor(Color.WHITE);

        g.drawRect(175, 300, 350, 110);
        g2.drawString("Main menu", 200, 378);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(MainGame.getGameState() != GameState.DEAD) return;

        if(isOn[0] || isOn[1]) {
            gameHandler.clearEnemys();
            gameHandler.addGameObject(new BasicEnemy(250, 250, ID.BASIC_ENEMY, gameHandler));
            gameHandler.getParticleHandler().clearTrails();
            hud.restartScores();
            gameHandler.getPlayer().healPlayer(100);
            gameHandler.getPlayer().setX(MainGame.WIDTH/2);
            gameHandler.getPlayer().setY(MainGame.HEIGHT/2);

        }

        if(isOn[0]){
            MainGame.setGameState(GameState.RUNNING);
            LevelHandler.getCurrLevel().startLogic();
        }
        if(isOn[1]) MainGame.setGameState(GameState.MENU);

    }

    public void mouseMoved(MouseEvent e){

        if(MainGame.getGameState() != GameState.DEAD) return;

        int x = e.getX();
        int y = e.getY();

        isOn[0] = mouseOver(x, y, 175, 160, 350, 110);
        isOn[1] = mouseOver(x, y, 175, 300, 350, 110);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
