package me.trolca.main.abstarcts.animations;

import me.trolca.main.handlers.AnimationHandler;

public abstract class Animation {

    protected AnimationHandler animationHandler;

    protected final int startTick;

    public Animation(AnimationHandler animationHandler, int startTick){
        this.animationHandler = animationHandler;
        this.startTick = startTick;
        animationHandler.addAnimation(this);
    }

    public abstract void tick();

}
