package me.trolca.main.objects;

import com.sun.tools.javac.Main;
import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.enums.Face;
import me.trolca.main.enums.GameState;
import me.trolca.main.enums.ID;
import me.trolca.main.MainGame;
import me.trolca.main.handlers.GameHandler;

import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject {

    private int health = 100;
    private GameHandler gameHandler;
    private final int width,height;

    public Player(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id);
        this.gameHandler = gameHandler;
        width = 40;
        height = 40;
    }

    @Override
    public void tick() {
        boolean hasCollided;
        hasCollided = checkCollisonWall(true);
        if(!hasCollided) checkCollisonWall(false);

        x += velX;
        y += velY;

        x = MainGame.clamp(x, 0, MainGame.WIDTH-55);
        y = MainGame.clamp(y, 0, MainGame.HEIGHT-79);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, this.width, this.height);


    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, this.width, this.height);
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


    private boolean checkCollisonWall(boolean isX){

        if((velX != 0 && isX) || (velY != 0 && !isX)) {
            for (GameObject gameObject : gameHandler.getGameObjects()) {

                if (gameObject.getId() == ID.WALL) {
                    Face face = (isX ? (this.velX > 0 ? Face.EAST : Face.WEST) : (this.velY > 0 ? Face.SOUTH : Face.NORTH));

                        int startX=0;
                        int startY=0;
                        int rayWidth = 1;
                        int rayHeight = 1;

                        switch (face){
                            case NORTH ->{
                                startX = this.x;
                                startY = this.y;
                                rayWidth = this.width;
                            }

                            case EAST -> {
                                startX = this.x + this.width;
                                startY = this.y;
                                rayHeight = this.height;
                            }

                            case SOUTH -> {
                                startX = this.x;
                                startY = this.y+this.height;
                                rayWidth = this.width;
                            }

                            case WEST -> {
                                startX = this.x;
                                startY = this.y;
                                rayHeight = this.height;
                            }


                        }

                    int x = getDistanceFromObject(face, startX, startY, rayWidth, rayHeight, gameObject);
                    if (x == 1 || x == 0) {
                        if (velX != 0 && isX){
                            Rectangle intersection = getBounds().intersection(gameObject.getBounds());
                            if(face == Face.EAST) this.x -= intersection.getWidth();
                            else this.x += intersection.getWidth();
                            velX = 0;
                        }
                        if (velY != 0 && !isX){
                            Rectangle intersection = getBounds().intersection(gameObject.getBounds());
                            if(face == Face.SOUTH) this.y -= intersection.getHeight();
                            else this.y += intersection.getHeight();
                            velY = 0;
                        }
                        return true;
                    }

                }
            }
        }

        return false;
    }

}
