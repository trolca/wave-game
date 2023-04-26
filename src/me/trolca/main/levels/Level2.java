package me.trolca.main.levels;

import me.trolca.main.HUD;
import me.trolca.main.abstarcts.Level;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.objects.enemies.BasicEnemy;
import me.trolca.main.objects.enemies.SpeedEnemy;

import java.awt.*;
import java.util.Random;

public class Level2 extends Level {
    public Level2(GameHandler gameHandler, HUD hud) {
        super(gameHandler, hud);
    }

    @Override
    public void tick() {

    }

    private Random r = new Random();

    @Override
    public int getScoreObjective() {
        return 2000;
    }

    @Override
    public Color getNameColor() {
        return Color.YELLOW.darker();
    }

    @Override
    public String getName() {
        return "Harder";
    }

    @Override
    public int getLevelNum() {
        return 2;
    }

    @Override
    public void drawThubnail(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int startX = this.x + 80;
        int startY = this.y + 20;

        g2.setColor(isOver && !isLocked ? Color.RED.darker() : Color.RED);
        for(int i=0; i < 25; i++){

            g2.setComposite(AlphaComposite.SrcOver.derive((float) i/25));
            g2.fillRect(startX, startY, 25, 25);
            startX += 4;
            startY += 4;
        }

        startX = this.x + 100;
        startY = this.y + 200;

        for(int i=0; i < 33; i++){

            g2.setComposite(AlphaComposite.SrcOver.derive((float) i/33));
            g2.fillRect(startX, startY, 25, 25);
            startX -= 3;
            startY -= 3;
        }
    }

    @Override
    public void startLogic() {

        gameHandler.clearEnemys();

        gameHandler.addGameObject(new SpeedEnemy(100, 50, ID.SPEED_ENEMY, gameHandler));
    }

    @Override
    public void levelTick() {

        switch (hud.getScore()){


            case 300 ->{
                gameHandler.addGameObject(new BasicEnemy(100, 100, ID.BASIC_ENEMY, gameHandler));
            }

            case 800 ->{
                gameHandler.addGameObject(new SpeedEnemy(r.nextInt(0, 500), r.nextInt(0, 200), ID.SPEED_ENEMY, gameHandler));
            }

            case 1500 ->{
                gameHandler.addGameObject(new BasicEnemy(r.nextInt(0, 450), r.nextInt(0, 450), ID.BASIC_ENEMY, gameHandler));
                gameHandler.addGameObject(new SpeedEnemy(r.nextInt(0, 500), r.nextInt(0, 200), ID.SPEED_ENEMY, gameHandler));
            }
        }
    }
}
