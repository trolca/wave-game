package me.trolca.main.animations;

import me.trolca.main.Location;
import me.trolca.main.abstarcts.animations.MoveAnimation;
import me.trolca.main.abstarcts.MenuObject;
import me.trolca.main.handlers.AnimationHandler;

public class BasicMoveAnimation extends MoveAnimation {

    public BasicMoveAnimation(MenuObject menuObject, Location fromWhere, Location toWhere,int startTick,  int howMuchTicks, AnimationHandler animationHandler) {
        super(animationHandler,menuObject, fromWhere, toWhere,startTick, howMuchTicks);
    }
}
