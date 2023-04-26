package me.trolca.main.abstarcts.animations;

import me.trolca.main.Location;
import me.trolca.main.abstarcts.MenuObject;
import me.trolca.main.handlers.AnimationHandler;

public abstract class MoveAnimation extends Animation{

    protected int currTick = 0;
    protected double currX=0, currY=0;
    protected double howMuchXMov,howMuchYMov;

    protected Location location;

    protected final MenuObject menuObject;
    protected final Location fromWhere;
    protected final Location toWhere;
    protected final int howMuchTicks;
    protected final AnimationHandler animationHandler;

    public MoveAnimation(AnimationHandler animationHandler, MenuObject menuObject, Location fromWhere, Location toWhere,int startTick, int howMuchTicks){
        super(animationHandler,startTick);

        this.menuObject = menuObject;
        this.fromWhere = fromWhere;
        this.toWhere = toWhere;
        this.howMuchTicks = howMuchTicks;
        this.animationHandler = animationHandler;

        location = fromWhere;

        int xDiff = Math.abs(fromWhere.getX()- toWhere.getX());
        int yDiff = Math.abs(fromWhere.getY() - toWhere.getY());

        menuObject.setLocation(fromWhere);

        howMuchXMov = (double) xDiff/howMuchTicks;
        howMuchYMov = (double) yDiff/howMuchTicks;


    }


    public void tick(){

        currTick++;

        if(currTick <= startTick) return;

        currX += howMuchXMov;
        currY += howMuchYMov;

        if(currX >= 1){
            int addX = (int) Math.floor(currX);
            location.setX(location.getX()+addX);
            currX -= addX;
        }

        if(currY >= 1){
            int addY = (int) Math.floor(currY);
            location.setY(location.getY()+addY);
            currY -= addY;
        }

        if(currTick-startTick >= howMuchTicks){
            location = toWhere;
            this.animationHandler.removeAnimation(this);
        }


        menuObject.setLocation(location);

    }





}
