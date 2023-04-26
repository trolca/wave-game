package me.trolca.main.animations;

import me.trolca.main.Location;
import me.trolca.main.abstarcts.MenuObject;
import me.trolca.main.enums.EasingType;
import me.trolca.main.handlers.AnimationHandler;

public class EasingAnimation extends me.trolca.main.abstarcts.animations.EasingAnimation {
    public EasingAnimation(AnimationHandler animationHandler, int startTick, MenuObject menuObject, Location startLoc, Location endLoc, int howMuchTicks, EasingType easingType) {
        super(animationHandler, startTick, menuObject, startLoc, endLoc, howMuchTicks, easingType);
    }
}
