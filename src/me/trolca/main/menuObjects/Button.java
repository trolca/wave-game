package me.trolca.main.menuObjects;

import me.trolca.main.GameUtils;
import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.MenuObject;

import java.awt.*;

public class Button extends MenuObject {

    private final Color color;
    private final String text;
    private Font font;
    private int startX;
    private int borderSize;
    private boolean isOver=false;

    public Button(int x, int y, int width, int height, Color color, String text, Font font) {
        super(x, y, width, height);
        this.color = color;
        this.text = text;
        this.font = font;
        this.borderSize = 4;

        initialize(font, true);

    }

    public Button(int x, int y, int width, int height, int borderSize, Color color, String text, Font font) {
        super(x, y, width, height);
        this.color = color;
        this.text = text;
        this.font = font;
        this.borderSize = borderSize;

        initialize(font, true);

    }


    public Button(int x, int y, int width, int height, Color color, String text, Font font, boolean autoSize) {
        super(x, y, width, height);
        this.color = color;
        this.text = text;
        this.font = font;
        this.borderSize = 4;

        initialize(font, autoSize);

    }

    private void initialize(Font font, boolean autoSize){

        if(autoSize) this.font = new Font(font.getName(), font.getStyle(),
                (int) ( width/ (text.length()/2.05)-(text.length() > 6 ? (text.length()-6)/3.0 * (width/90) : 0 ))-
                        (font.getStyle() == Font.BOLD ? 5 : 0)-(width > 300 ? width/100 : 0) );
        startX = x+2;

    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        if(!isOver) g.setColor(color);
        else g.setColor(color.darker());
        g2.setStroke(new BasicStroke(borderSize));

        g2.drawRect(x,y,width,height);

        if(font != null) g2.setFont(font);

        g2.drawString(text, x+2, (int)(y+(height/1.5)));
    }

    public void setIsOver(boolean isOver){
        this.isOver = isOver;
    }

    public boolean getIsOver(){
        return isOver;
    }

}
