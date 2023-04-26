package me.trolca.main.animations;

import me.trolca.main.Location;
import me.trolca.main.abstarcts.animations.TextAnimation;
import me.trolca.main.handlers.AnimationHandler;
import me.trolca.main.menuObjects.Text;

public class CountTextAnimation extends TextAnimation {

    private final int numToEnd;
    private final int numAddEveryTick;
    private final float numDecimalAddEveryTick;
    private float currDecim;
    private int currValue;
    private int currTick;
    private String perfix;


    public CountTextAnimation(AnimationHandler animationHandler, Text textObject,int startTick, int howMuchTicks, int numToEnd, String perfix) {
        super(animationHandler, textObject, startTick ,  howMuchTicks);
        this.numToEnd = numToEnd;
        this.perfix = perfix;

        numAddEveryTick = Math.floorDiv(numToEnd, howMuchTicks);
        numDecimalAddEveryTick =  ( (float) numToEnd/howMuchTicks)-numAddEveryTick;


        animationHandler.addAnimation(this);
    }

    @Override
    public void tick() {

        currTick++;
        if(currTick <= startTick) return;
        currValue += numAddEveryTick;
        currDecim += numDecimalAddEveryTick;

        if(currDecim >= 1){
            currValue += (int) currDecim;
            currDecim = currDecim - (int) currDecim;
        }

        textObject.setText(perfix + " " + currValue);


        if(currTick-startTick >= howMuchTicks){
            currValue =  numToEnd;

            textObject.setText(perfix + " " + currValue);

            animationHandler.removeAnimation(this);
        }

    }
}
