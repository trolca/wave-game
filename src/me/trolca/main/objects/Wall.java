package me.trolca.main.objects;

import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;

import java.awt.*;

public class Wall extends GameObject {

    private int width;
    private int height;
    private Color color;
    private GameHandler gameHandler;

    public Wall(int x, int y,int width, int height, Color color, ID id, GameHandler gameHandler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.color = color;
        this.gameHandler = gameHandler;

    }

    @Override
    public void tick() {

        if(getBounds().intersects(gameHandler.getPlayer().getBounds())){
            Player player = gameHandler.getPlayer();

            player.setVelX(0);
            player.setVelY(0);
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
