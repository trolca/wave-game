package me.trolca.main.animations;

import me.trolca.main.Location;
import me.trolca.main.abstarcts.animations.MoveAnimation;
import me.trolca.main.abstarcts.MenuObject;
import me.trolca.main.handlers.AnimationHandler;
@Deprecated
public class BounceMoveAnimation extends MoveAnimation {

    private boolean bouncyStarted = false;
    private int bounceSmoothin=1;
    private int howMuchYTravel;
    private int halfTicks;
    private int eightTicks;

    public BounceMoveAnimation(MenuObject menuObject, Location fromWhere, Location toWhere,int startTick, int howMuchTicks, AnimationHandler animationHandler) {
        super(animationHandler,menuObject, fromWhere, toWhere,startTick, howMuchTicks);

        howMuchYTravel = Math.abs(fromWhere.getY() - toWhere.getY());

        halfTicks = howMuchTicks/2;
        eightTicks = halfTicks/8;


    }

    @Override
    public void tick(){

        if(currTick <= howMuchTicks) {

            if(currTick >= halfTicks) {

                int nowTicks = currTick-halfTicks;



                if(currTick >= halfTicks + (halfTicks/2)-50 && currTick <= halfTicks + halfTicks/2){

                    howMuchYMov =  0.9/bounceSmoothin;

                    bounceSmoothin++;
                }else if(currTick >= halfTicks + halfTicks/2) {
                    howMuchYMov = 0.6;
                }else{
                    howMuchYMov = -0.6;
                }



                bouncyStarted = true;
            }


            currX += howMuchXMov * (bouncyStarted ? 1 : 2);
            currY += howMuchYMov * (bouncyStarted ? 1 : 2);

            System.out.println(currTick + " " + howMuchYMov);

            if (currX >= 1 || currY <= -1) {
                int addX = (int) Math.floor(currX);
                location.setX(location.getX() + addX);
                currX -= addX;
            }

            if (currY >= 1 || currY <= -1) {
                int addY = (int) Math.floor(currY);
                location.setY(location.getY() + addY);
                currY -= addY;
            }
        }else{
            location = toWhere;
            this.animationHandler.removeAnimation(this);
        }


        menuObject.setLocation(location);

        currTick++;
    }

}
