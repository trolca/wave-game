package me.trolca.main.objects;

import me.trolca.main.handlers.GameHandler;
import me.trolca.main.enums.ID;
import me.trolca.main.abstarcts.Particle;

import java.awt.*;

public class Trail extends Particle {

    private int width;
    private int height;

    public Trail(int x, int y,int lifeTime, int width, int height, ID id, Color color, GameHandler gameHandler) {
        super(x, y,lifeTime, id,color, gameHandler);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);
        g2d.setComposite(AlphaComposite.SrcOver.derive(( (float) life/startLife)));
        g2d.fillRect(x, y, width, height);
        g2d.setComposite(AlphaComposite.SrcOver);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
