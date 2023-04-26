package me.trolca.main.objects.enemies;

import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.enums.ID;
import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Enemy;
import me.trolca.main.objects.Trail;
import me.trolca.main.objects.Wall;

import java.awt.*;

public class BasicEnemy extends Enemy {

    private GameHandler gameHandler;
    private final int width;
    private final int height;
    private int addX;
    private int addY;

    public BasicEnemy(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id);
        this.gameHandler = gameHandler;
        this.width = MainGame.WIDTH;
        this.height = MainGame.HEIGHT;
        addX = 0;
        addY = 0;
        velX = 5;
        velY = 5;
    }

    public BasicEnemy(int x, int y,int width, int height,int addX, int addY, ID id, GameHandler gameHandler) {
        super(x+addX, y+addY, id);
        this.addX = addX;
        this.addY = addY;
        this.gameHandler = gameHandler;
        this.width = width;
        this.height = height;
        this.x += addX;
        this.y += addY;
        velX = 5;
        velY = 6;
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        if(x < addX) velX *= -1;
        if(y < addY) velY *= -1;
        if(x > width+addX-39) velX *= -1;
        if(y > height+addY-60) velY *= -1;


        gameHandler.getParticleHandler().addParticle(new Trail(x, y, 20, 20, 20, ID.TRAIL, Color.RED, gameHandler));

        if(getBounds().intersects(gameHandler.getPlayer().getBounds())){
            gameHandler.getPlayer().dealDamage(getDamage());
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 20, 20);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

    @Override
    public int getDamage() {
        return 3;
    }

    public void setAddX(int addX) {
        this.addX = addX;
    }

    public void setAddY(int addY) {
        this.addY = addY;
    }
}
