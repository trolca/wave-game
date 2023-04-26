package me.trolca.main.abstarcts;

import me.trolca.main.enums.ID;

public abstract class Enemy extends GameObject{
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    public abstract int getDamage();
}
