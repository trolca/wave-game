package me.trolca.main.abstarcts;

import me.trolca.main.Location;
import me.trolca.main.enums.ID;

import java.awt.*;

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



}
