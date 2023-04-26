package me.trolca.main.abstarcts;

import me.trolca.main.HUD;
import me.trolca.main.handlers.GameHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Level {

    protected int x, y;
    protected boolean isOver=false;
    protected boolean isLocked=true;
    protected int width=250, height=250;
    private final ImageIcon lockIcon = new ImageIcon("Pictures/klodka.png");

    protected GameHandler gameHandler;
    protected HUD hud;

    public Level(GameHandler gameHandler, HUD hud){
        this.gameHandler = gameHandler;
        this.hud = hud;
    }


    public void render(Graphics g){

        Graphics2D g2 = (Graphics2D) g;

        drawThubnail(g);

        if(isLocked) g.drawImage(lockIcon.getImage(), x+50, y+40, 150, 150, lockIcon.getImageObserver());

        g2.setColor(isOver || isLocked ? getNameColor().darker() : getNameColor());

        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 10, 10);

        int nameLenght = getName().length();
        int size = 26-(nameLenght <= 10 ? 0 : (nameLenght-10 >= 26 ? 5 : nameLenght-10) );

        g2.setFont(new Font("Roboto", Font.BOLD, size));
        g2.drawString("Level "+getLevelNum()+": "+getName(), (int) (x+(nameLenght-10)*1.5), y+height+29);

        g2.setColor(isOver && !isLocked ? Color.YELLOW : Color.YELLOW.darker());

        g2.setFont(new Font("Roboto", Font.BOLD, 23));
        g2.drawString("Objective: " + getScoreObjective() + " points", x, y-10);

    }

    public abstract void tick();

    public abstract int getScoreObjective();

    public abstract Color getNameColor();

    public abstract void levelTick();

    public abstract String getName();

    public abstract int getLevelNum();

    public abstract void drawThubnail(Graphics g);

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public abstract void startLogic();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height+50);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setX(int x) {
        this.x = x;
    }

   public void setY(int y) {
        if(this.y == y) return;
        this.y = y;
   }





}
