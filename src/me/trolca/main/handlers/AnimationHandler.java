package me.trolca.main.handlers;

import me.trolca.main.abstarcts.animations.Animation;
import me.trolca.main.abstarcts.animations.MoveAnimation;

import java.util.ArrayList;

public class AnimationHandler {

    private ArrayList<Animation> animations = new ArrayList<>();

    public void tick(){

        for(int i = 0; i < animations.size(); i++){
            animations.get(i).tick();
        }

    }

    public ArrayList<Animation> getAnimations(){
        return animations;
    }

    public void addAnimation(Animation animation){
        animations.add(animation);
    }


    public void removeAnimation(Animation animation){
        animations.remove(animation);
    }


}
