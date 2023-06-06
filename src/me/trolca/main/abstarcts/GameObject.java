package me.trolca.main.abstarcts;

import me.trolca.main.Location;
import me.trolca.main.MainGame;
import me.trolca.main.enums.Face;
import me.trolca.main.enums.ID;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameObject {

    protected int x, y;
    protected int velX, velY;
    protected ID id;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }



    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public ID getId(){
        return id;
    }

    public Location getLocation(){
        return new Location(x, y);
    }

    public void setLocation(Location location){
        x = location.getX();
        y = location.getY();
    }



    public int getDistanceFromObject(Face face, int startX, int startY, int width, int height, GameObject gameObject){
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
            Rectangle rectangle = new Rectangle(traceX, traceY, width, height);
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



}
