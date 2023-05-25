package me.trolca.main.objects.enemies;

import me.trolca.main.handlers.GameHandler;
import me.trolca.main.enums.ID;
import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Enemy;
import me.trolca.main.objects.Player;
import me.trolca.main.objects.Trail;

import java.awt.*;

public class SpeedEnemy extends Enemy {


    public SpeedEnemy(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id, gameHandler);
        velX = 5;
        velY = 8;
    }

    @Override
    public int getDamage() {
        return 3;
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

        gameHandler.getParticleHandler().addParticle(new Trail(x, y, 25, 15, 15, ID.TRAIL, Color.CYAN, gameHandler));

        Player player = gameHandler.getPlayer();
        if(player.getBounds().intersects(getBounds())){
            player.dealDamage(getDamage());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 15, 15);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 15, 15);
    }
}
