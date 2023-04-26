package me.trolca.main.abstarcts;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public abstract class Menu extends MouseMotionAdapter implements MouseListener {

    protected ArrayList<MenuObject> menuObjects = new ArrayList<>();

    protected boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else {
            return false;
        }
    }


    public abstract void render(Graphics g);


    public ArrayList<MenuObject> getMenuObjects() {
        return menuObjects;
    }


}
