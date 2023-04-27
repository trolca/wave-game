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

    public Player(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id);
        this.gameHandler = gameHandler;
    }

    @Override
    public void tick() {

        if(velX != 0 || velY != 0) {
            for (GameObject gameObject : gameHandler.getGameObjects()) {

                if (gameObject.getId() == ID.WALL) {
                    Face face = (this.velY == 0 ? (this.velX > 0 ? Face.EAST : Face.WEST) : (this.velY > 0 ? Face.SOUTH : Face.NORTH));


                    for (int i = this.x; i < this.x + 40; i++) {
                        int x = getDistanceFromObject(Face.NORTH, PlayerCorner.TOP_LEFT, i, this.y, gameObject);
                        if (x == 1) {
                            if (velX != 0) velX = 0;
                            if (velY != 0) velY = 0;
                        }

                    }

                }
            }
        }

            x += velX;
            y += velY;

        x = MainGame.clamp(x, 0, MainGame.WIDTH-55);
        y = MainGame.clamp(y, 0, MainGame.HEIGHT-79);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 40, 40);


    }

    public int getDistanceFromObject(Face face,PlayerCorner corner, int startX, int startY, GameObject gameObject){
        int addDir;




        if(face == Face.NORTH || face == Face.SOUTH){
            addDir = face == Face.NORTH ? -1 : 1;
        }else{
            addDir = face == Face.EAST ? 1 : -1;
        }

        boolean isDone=false;
        int howFar=0;
        int traceX= startX;
        int traceY= startY;
        ArrayList<Rectangle> sussy = new ArrayList<>();

        while(!isDone){

            if(face == Face.NORTH || face == Face.SOUTH){ //y
                traceY += addDir;
            }else{ //x
                traceX += addDir;
            }

            howFar++;
            Rectangle rectangle = new Rectangle(traceX, traceY, 1, 1);
            sussy.add(rectangle);
            if(rectangle.intersects(gameObject.getBounds())){
                isDone = true;
            }

            if(traceX < 0 || traceY < 0 || traceX > MainGame.WIDTH || traceY > MainGame.HEIGHT){
                isDone = true;
                howFar = -1;
            }


        }


        return howFar;
    }

    public int getDistanceFromObject(Face face,PlayerCorner corner, GameObject gameObject){
        int addDir;
        int startX;
        int startY;

        switch (corner){
            case TOP_RIGHT -> {
                startX = (int) (this.x+this.getBounds().getWidth());
                startY = this.y;
            }

            case BOTTOM_LEFT -> {
                startX = this.x;
                startY = (int) (this.y+this.getBounds().getHeight());
            }

            case BOTTOM_RIGHT -> {
                startX = (int) (this.x+this.getBounds().getWidth());
                startY = (int) (this.y+this.getBounds().getWidth());
            }

            case CENTER -> {
                startX = (int) (this.x + ( this.getBounds().getWidth()/2 ));
                startY = this.y;
            }

            default -> {
                startX = this.x;
                startY = this.y;
            }
        }




        if(face == Face.NORTH || face == Face.SOUTH){
            addDir = face == Face.NORTH ? -1 : 1;
        }else{
            addDir = face == Face.EAST ? 1 : -1;
        }

        boolean isDone=false;
        int howFar=0;
        int traceX= startX;
        int traceY= startY;
        ArrayList<Rectangle> sussy = new ArrayList<>();

        while(!isDone){

            if(face == Face.NORTH || face == Face.SOUTH){ //y
                traceY += addDir;
            }else{ //x
                traceX += addDir;
            }

            howFar++;
            Rectangle rectangle = new Rectangle(traceX, traceY, 1, 1);
            sussy.add(rectangle);
            if(rectangle.intersects(gameObject.getBounds())){
                isDone = true;
            }

            if(traceX < 0 || traceY < 0 || traceX > MainGame.WIDTH || traceY > MainGame.HEIGHT){
                isDone = true;
                howFar = -1;
            }


        }

        return howFar;
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


    public enum PlayerCorner{
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        CENTER
    }

}
