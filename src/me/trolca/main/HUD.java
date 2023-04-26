package me.trolca.main;

import me.trolca.main.enums.GameState;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.handlers.LevelHandler;
import me.trolca.main.handlers.ParticleHandler;
import me.trolca.main.objects.EffectParticle;
import me.trolca.main.objects.Player;

import java.awt.*;

public class HUD {

    private GameHandler gameHandler;
    private int score = 0;

    public HUD(GameHandler gameHandler){
        this.gameHandler = gameHandler;
    }

    public void tick(){
        score++;

        if(score >= LevelHandler.getCurrLevel().getScoreObjective()){

            MainGame.setGameState(GameState.FINISHED);

            setScore(0);
            gameHandler.getPlayer().setLocation(new Location(MainGame.WIDTH/2, MainGame.HEIGHT/2));
            gameHandler.getPlayer().setHealth(100);


        }


    }

    public void render(Graphics g){
        Player player = gameHandler.getPlayer();

        g.setColor(Color.RED);
        g.setFont(new Font("Roboto", Font.BOLD, 20));
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));
        g2.drawString("Health: ", 10, 20);
        g2.setComposite(AlphaComposite.SrcOver);

        drawScores(g);
        drawHealth(g, player);

    }

    private void drawScores(Graphics g){
        g.setFont(new Font("Roboto", Font.PLAIN, 18));

        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Level: "+ LevelHandler.getCurrLevel().getLevelNum(), 10, 95);
        g.drawString("Score: "+ score + "/" + LevelHandler.getCurrLevel().getScoreObjective(), 10, 120);


    }

    public void restartScores(){
        score = 0;
    }

    private void drawHealth(Graphics g, Player player){
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));
        g2.setColor(Color.WHITE);
        g2.drawRect(10, 30, 150, 40);
        g2.setColor(Color.GRAY);
        g2.fillRect(11, 31, 149, 39);
        if(player.getHealth() <= 0) return;
        float howMuchFill =  ( ((float) player.getHealth()/player.getMaxHealth()));
        g2.setColor(new Color(148-(int)(107.0*howMuchFill), (int) (196.0*howMuchFill), 24));
        g2.fillRect(11, 31, MainGame.clamp((int) (149*howMuchFill), 0, 149), 39);
        g2.setComposite(AlphaComposite.SrcOver);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
