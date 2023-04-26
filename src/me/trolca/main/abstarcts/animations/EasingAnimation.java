package me.trolca.main.abstarcts.animations;

import me.trolca.main.Location;
import me.trolca.main.abstarcts.MenuObject;
import me.trolca.main.enums.EasingType;
import me.trolca.main.handlers.AnimationHandler;

public abstract class EasingAnimation extends Animation{

    /*

        currTick/howMuchTicks - x
        currLoc = (endLoc-startLoc)*sinFun(x)

     */

    private final Location distanceNeeded;
    private Location currLoc;
    private int currTickBefore = 0;
    private int currTick = 0;
    private final int howMuchTicks;
    private final MenuObject menuObject;
    private final Location endLoc;
    private final Location startLoc;
    private EasingType easingType;

    public EasingAnimation(AnimationHandler animationHandler, int startTick, MenuObject menuObject, Location startLoc, Location endLoc, int howMuchTicks, EasingType easingType) {
        super(animationHandler, startTick);
        distanceNeeded = new Location(endLoc.getX()-startLoc.getX(), endLoc.getY()-startLoc.getY());
        this.howMuchTicks = howMuchTicks;
        this.menuObject = menuObject;
        this.endLoc = endLoc;
        this.easingType = easingType;
        this.startLoc = startLoc;
    }

    @Override
    public void tick() {

        if( !(currTickBefore >= startTick)){
            currTickBefore++;
            return;
        }
        currTick++;

        double nowQuad;
        switch (easingType){
            case EASE_OUT_BOUNCE -> nowQuad = easeOutBounce((double) currTick/howMuchTicks);
            case EASE_IN_BOUNCE -> nowQuad = easeInBounce((double) currTick/howMuchTicks);
            case EASE_OUT_BACK -> nowQuad = easeOutBack((double) currTick/howMuchTicks);

            default -> nowQuad = easeCubic((double) currTick/howMuchTicks);
        }

        currLoc = new Location(distanceNeeded.getX() == 0 ? endLoc.getX() : (int) ( startLoc.getX() + (distanceNeeded.getX() * nowQuad)), distanceNeeded.getY() == 0 ? endLoc.getY() : (int) ( startLoc.getY() + (distanceNeeded.getY() * nowQuad)));

        menuObject.setLocation(currLoc);

        if(currTick >= howMuchTicks){
            this.animationHandler.removeAnimation(this);
        }
    }

    private double easeCubic(double x){
        return x*x;
    }
    private double easeInBounce(double x){
        return 1 - easeOutBounce(1 - x);
    }

    private double easeOutBounce(double x) {
        double n1 = 7.5625;
        double d1 = 2.75;

        if (x < 1 / d1) {
            return n1 * x * x;
        } else if (x < 2 / d1) {
            return n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            return n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            return n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
    }


    private double easeOutBack(double x) {
        double c1 = 1.70158;
        double c3 = c1 + 1;

        return 1 + c3 * Math.pow(x - 1, 3) + c1 * Math.pow(x - 1, 2);
    }
}
