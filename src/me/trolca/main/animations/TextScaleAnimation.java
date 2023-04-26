package me.trolca.main.animations;

import me.trolca.main.abstarcts.animations.TextAnimation;
import me.trolca.main.handlers.AnimationHandler;
import me.trolca.main.menuObjects.Text;

import java.awt.*;

public class TextScaleAnimation extends TextAnimation {

    private final int differenceSize;
    private int startTicks=0;
    private int currTick=0;
    private final int startSize;
    private final int finalSize;

    public TextScaleAnimation(AnimationHandler animationHandler, Text textObject, int startTick, int howMuchTicks, int startSize, int finalSize) {
        super(animationHandler, textObject, startTick, howMuchTicks);
        this.finalSize = finalSize;
        this.startSize = startSize;
        differenceSize = finalSize-startSize;
    }

    @Override
    public void tick() {

        if(startTicks < startTick){
            startTicks++;
            return;
        }

        currTick++;

       Font font = new Font(textObject.getFont().getFontName(), textObject.getFont().getStyle(), (int) (startSize+ ( differenceSize*cubicEasing( (float) currTick/howMuchTicks) )));
       textObject.setFont(font);

       if(currTick >= howMuchTicks){
           animationHandler.removeAnimation(this);
       }

    }

    public double cubicEasing(double x){
        return 1 - (1 - x) * (1 - x);
    }
}
 