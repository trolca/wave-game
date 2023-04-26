package me.trolca.main.abstarcts;

import me.trolca.main.Location;

import java.awt.*;

public  abstract class MenuObject {

    protected int x,y,width,height;

    public MenuObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public abstract void render(Graphics g);

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void setLocation(Location location){
        this.x = location.getX();
        this.y = location.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Location  getLocation(){
        return new Location(this.x, this.y);
    }

}
