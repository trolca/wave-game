package me.trolca.main.abstarcts;

import me.trolca.main.enums.Face;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.objects.Wall;

import java.awt.*;

public abstract class Enemy extends GameObject{

    protected int waitTick;
    protected GameHandler gameHandler;

    public Enemy(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id);
        this.gameHandler = gameHandler;
    }

    public void checkWallColisions(){
        if(waitTick <= 0) {
            for (Wall wall : gameHandler.getWalls()) {

                if (this.getBounds().intersects(wall.getBounds())) {
                    Rectangle intersection = getBounds().intersection(wall.getBounds());

                    if (intersection.height < intersection.width) {
                        velY *= -1;
                    } else {
                        velX *= -1;
                    }

                    waitTick = 5;

                }
            }
        }else waitTick--;
    }


    public abstract int getDamage();
}
