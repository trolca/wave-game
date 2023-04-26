package me.trolca.main.abstarcts;

import me.trolca.main.handlers.GameHandler;
import me.trolca.main.enums.ID;

import java.awt.*;

public abstract class Particle extends GameObject {

    protected GameHandler gameHandler;
    protected int life;
    protected int startLife;
    protected Color color;

    public Particle(int x, int y,int lifeTime, ID id,Color color, GameHandler gameHandler) {
        super(x, y, id);
        this.life = lifeTime;
        startLife = life;
        this.color = color;
        this.gameHandler = gameHandler;
    }

    @Override
    public void tick(){

        life--;

        if(life == 0) gameHandler.getParticleHandler().removeParticle(this);

    }


}
