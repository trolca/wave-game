package me.trolca.main.levels;

import me.trolca.main.HUD;
import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.abstarcts.Level;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.objects.Wall;
import me.trolca.main.objects.enemies.BasicEnemy;

import java.awt.*;
import java.util.Random;

public class Level1 extends Level {

    private Random r = new Random();

    public Level1(GameHandler gameHandler, HUD hud) {
        super(gameHandler, hud);
        isLocked=false;
    }


    @Override
    public void tick() {
        if(isOver) gameHandler.tick();
    }

    @Override
    public int getScoreObjective() {
        return 1500;
    }

    @Override
    public Color getNameColor() {
        return Color.GREEN;
    }

    @Override
    public String getName() {
        return "Easy peasy";
    }

    @Override
    public int getLevelNum() {
        return 1;
    }

    @Override
    public void drawThubnail(Graphics g) {


    }

    @Override
    public void startLogic() {

        gameHandler.clearEnemys();
        gameHandler.addGameObject(new BasicEnemy(50, 50, ID.BASIC_ENEMY, gameHandler));
        gameHandler.addGameObject(new Wall(350, 250, 100, 10,  Color.PINK, ID.WALL, gameHandler));
    }

    @Override
    public void levelTick() {

        switch (hud.getScore()){

            case 300 ->{
                gameHandler.addGameObject(new BasicEnemy(r.nextInt(250), r.nextInt(250),ID.BASIC_ENEMY, gameHandler));
            }

            case 1000 ->{
                gameHandler.addGameObject(new BasicEnemy(r.nextInt( 400), r.nextInt(400), ID.BASIC_ENEMY, gameHandler));
            }

        }

    }
}
