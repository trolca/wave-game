package me.trolca.main.objects.enemies;

import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Enemy;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.objects.Trail;

import java.awt.*;

public class BulletShooterEnemy extends Enemy {


    public BulletShooterEnemy(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id, gameHandler);
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(x < 0) velX *= -1;
        if(y < 0) velY *= -1;
        if(x > MainGame.WIDTH-35) velX *= -1;
        if(y > MainGame.HEIGHT-59) velY *= -1;

        checkWallColisions();

        gameHandler.getParticleHandler().addParticle(new Trail(x, y, 20, 20, 20, ID.TRAIL, Color.RED, gameHandler));

        if(getBounds().intersects(gameHandler.getPlayer().getBounds())){
            gameHandler.getPlayer().dealDamage(getDamage());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(98, 1, 1));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
