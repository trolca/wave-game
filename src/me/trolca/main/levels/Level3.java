package me.trolca.main.levels;

import me.trolca.main.HUD;
import me.trolca.main.abstarcts.Level;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.objects.enemies.BasicEnemy;
import me.trolca.main.objects.enemies.FollowEnemy;
import me.trolca.main.objects.enemies.SpeedEnemy;

import java.awt.*;
import java.util.Random;

public class Level3 extends Level {

    public Level3(GameHandler gameHandler, HUD hud) {
        super(gameHandler, hud);
    }


    @Override
    public void tick() {

    }

    Random r = new Random();

    @Override
    public int getScoreObjective() {
        return 2000;
    }

    @Override
    public Color getNameColor() {
        return new Color(155, 149, 18);
    }


    @Override
    public String getName() {
        return "Whats this thing?";
    }

    @Override
    public int getLevelNum() {
        return 3;
    }

    @Override
    public void drawThubnail(Graphics g) {

    }

    @Override
    public void startLogic() {
        gameHandler.clearEnemys();
        gameHandler.addGameObject(new FollowEnemy(100, 100, ID.BASIC_ENEMY, gameHandler));
    }

    @Override
    public void levelTick() {
        switch (hud.getScore()){


            case 400 ->{
                gameHandler.addGameObject(new BasicEnemy(r.nextInt(0, 450), r.nextInt(0, 450), ID.BASIC_ENEMY, gameHandler));
                gameHandler.addGameObject(new BasicEnemy(r.nextInt(0, 450), r.nextInt(0, 450), ID.BASIC_ENEMY, gameHandler));
            }

            case 900 ->{
                gameHandler.addGameObject(new SpeedEnemy(r.nextInt(0, 500), r.nextInt(0, 200), ID.SPEED_ENEMY, gameHandler));
            }

            case 1500 ->{
                gameHandler.addGameObject(new BasicEnemy(r.nextInt(0, 450), r.nextInt(0, 450), ID.BASIC_ENEMY, gameHandler));
                gameHandler.addGameObject(new SpeedEnemy(r.nextInt(0, 500), r.nextInt(0, 200), ID.SPEED_ENEMY, gameHandler));
            }
        }
    }
}
