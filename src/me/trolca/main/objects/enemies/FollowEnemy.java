package me.trolca.main.objects.enemies;

import me.trolca.main.abstarcts.Enemy;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.objects.Player;
import me.trolca.main.objects.Trail;

import java.awt.*;

public class FollowEnemy extends Enemy {


    private float xFloat=0;
    private float yFloat=0;
    private final float speed;

    public FollowEnemy(int x, int y, ID id, GameHandler gameHandler) {
        super(x, y, id, gameHandler);
        this.gameHandler = gameHandler;
        speed = (float) 5;
    }

    @Override
    public int getDamage() {
        return 2;
    }

    @Override
    public void tick() {
        Player player = gameHandler.getPlayer();

        int diffX = player.getX()-this.x;
        int diffY = player.getY()-this.y;
        int totalDistance = Math.abs(diffX)+Math.abs(diffY);
        float xPercentage = (float) diffX/totalDistance;
        float yPercentage = (float) diffY/totalDistance;


        xFloat += speed*xPercentage;
        yFloat += speed*yPercentage;

        checkWallColisions();


        if(xFloat >= 1 || xFloat <= -1){
            int addX = (int) Math.floor(xFloat);
            this.x += addX;
            xFloat -= (float) addX;
        }

        if(yFloat >= 1 || yFloat <= -1){
            int addY = (int) Math.floor(yFloat);
            this.y += addY;
            yFloat -= (float) addY;
        }

        gameHandler.getParticleHandler().addParticle(new Trail(x, y, 30, 30, 30, ID.TRAIL, Color.GREEN, gameHandler));

        if(player.getBounds().intersects(getBounds())){
            player.dealDamage(getDamage());
        }
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.GREEN);
        g.fillRect(this.x, this.y, 30, 30);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y,30, 30);
    }
}
