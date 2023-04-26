package me.trolca.main.objects;

import com.sun.tools.javac.Main;
import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.enums.GameState;
import me.trolca.main.enums.ID;
import me.trolca.main.MainGame;
import me.trolca.main.handlers.GameHandler;

import java.awt.*;

public class Player extends GameObject {

    private int health = 100;
    private GameHandler gameHandler;

    public Player(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id);
        this.gameHandler = gameHandler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        for(GameObject gameObject : gameHandler.getGameObjects()){
            if(gameObject instanceof Wall wall){
                if(getBounds().intersects(wall.getBounds())){
                    x -= velX;
                    y -= velY;
                }
            }
        }


        x = MainGame.clamp(x, 0, MainGame.WIDTH-55);
        y = MainGame.clamp(y, 0, MainGame.HEIGHT-79);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 40, 40);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    public void dealDamage(int howMuch){
        health -= howMuch;
        if(health <= 0 && MainGame.getGameState() != GameState.DEAD){
            gameHandler.getParticleHandler().clearTrails();
            gameHandler.clearEnemys();
            health = 0;
            MainGame.setGameState(GameState.DEAD);
            gameHandler.getParticleHandler().addParticle(new EffectParticle(0, 0, 50, ID.EFFECT_PARTICLE ,Color.WHITE, gameHandler));
        }
    }

    public void healPlayer(int howMuch){
        health += howMuch;
    }

    public int getMaxHealth(){
        return 100;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

}
