package me.trolca.main.menuObjects;

import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.abstarcts.Menu;
import me.trolca.main.abstarcts.MenuObject;
import me.trolca.main.enums.ID;

import java.awt.*;

public class Text extends MenuObject {

    private String text;
    private Font font;
    private Color color;

    public Text(int x, int y, String text, Font font, Color color) {
        super(x, y, 0, 0);
        this.text = text;
        this.font  = font;
        this.color =  color;
    }

    @Override
    public void render(Graphics g) {

        g.setColor(color);
        g.setFont(font);

        g.drawString(text, x, y);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
