package me.trolca.main.abstarcts.animations;

import me.trolca.main.handlers.AnimationHandler;
import me.trolca.main.menuObjects.Text;

public abstract class TextAnimation extends Animation{

    protected final Text textObject;
    protected final int howMuchTicks;

    public TextAnimation(AnimationHandler animationHandler, Text textObject,int startTick, int howMuchTicks){
        super(animationHandler, startTick);
        this.textObject = textObject;
        this.howMuchTicks = howMuchTicks;
    }


}
