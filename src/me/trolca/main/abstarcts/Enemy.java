package me.trolca.main.abstarcts;

import me.trolca.main.enums.Face;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.objects.Wall;

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
                    int distance = getDistanceFromObject(Face.NORTH, this.x, this.y, 20, 20, wall);
                    if(distance > 1 || distance == -1) distance = getDistanceFromObject(Face.SOUTH, this.x, this.y+20, 20, 20, wall);
                    System.out.println(distance);
                    if (distance <= 1 && distance != -1) {
                        System.out.println("epiccicici");
                        velY *= -1;
                    } else {
                        System.out.println("sus");
                        velX *= -1;
                    }

                    waitTick = 5;

                }
            }
        }else waitTick--;
    }

    public abstract int getDamage();
}
