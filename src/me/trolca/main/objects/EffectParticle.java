package me.trolca.main.objects;

import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Particle;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;

import java.awt.*;

public class EffectParticle extends Particle {

    private int tickTimer=0;

    public EffectParticle(int x, int y, int lifeTime, ID id, Color color, GameHandler gameHandler) {
        super(x, y, lifeTime, id, color, gameHandler);
    }

    @Override
    public void tick(){

        if(tickTimer <= 10) tickTimer++;
        else {

            life--;

            if (life == 0) gameHandler.getParticleHandler().removeParticle(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);
        g2d.setComposite(AlphaComposite.SrcOver.derive(( (float) life/startLife)));
        g2d.fillRect(x, y, MainGame.WIDTH, MainGame.HEIGHT);
        g2d.setComposite(AlphaComposite.SrcOver);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
