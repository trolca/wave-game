package me.trolca.main.menus;

import com.sun.tools.javac.Main;
import me.trolca.main.Location;
import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Menu;
import me.trolca.main.animations.CountTextAnimation;
import me.trolca.main.animations.EasingAnimation;
import me.trolca.main.animations.TextScaleAnimation;
import me.trolca.main.enums.EasingType;
import me.trolca.main.enums.GameState;
import me.trolca.main.handlers.AnimationHandler;
import me.trolca.main.handlers.LevelHandler;
import me.trolca.main.menuObjects.Button;
import me.trolca.main.menuObjects.Text;

import java.awt.*;
import java.awt.event.MouseEvent;

public class FinishMenu extends Menu {

    private LevelHandler levelHandler;

    public FinishMenu(AnimationHandler animationHandler, LevelHandler levelHandler){
        this.levelHandler = levelHandler;
        menuObjects.add(new Text(110, 0, "Finished level "+ LevelHandler.getCurrLevel().getLevelNum() + "!",
                new Font("Roboto", Font.BOLD, 70), Color.YELLOW));


        menuObjects.add(new Button(MainGame.WIDTH+275, 200, 275, 115,5, Color.WHITE, "Continue",
            new Font("Roboto", Font.PLAIN, 0)));

        menuObjects.add(new Text(200, 155, "Score: 0", new Font("Roboto", Font.BOLD, 0), Color.WHITE));


        new EasingAnimation(animationHandler, 0, menuObjects.get(0), new Location(110, 0), new Location(110, 100), 75, EasingType.EASE_OUT_BOUNCE);
        new EasingAnimation(animationHandler, 125, menuObjects.get(1), new Location(MainGame.WIDTH+275, 200), new Location(230, 200), 40, EasingType.EASE_OUT_BACK);
        new TextScaleAnimation(animationHandler, (Text) menuObjects.get(2), 30, 40, 0, 60);
        new CountTextAnimation(animationHandler, (Text) menuObjects.get(2), 135,120, LevelHandler.getCurrLevel().getScoreObjective(), "Score: ");
    }

    @Override
    public void render(Graphics g) {


    }

    public void mouseMoved(MouseEvent e){

        if(MainGame.getGameState() != GameState.FINISHED) return;
        int mx = e.getX();
        int my = e.getY();

        for(int i=0; i < menuObjects.size(); i++) {
            if (menuObjects.get(i) instanceof Button button) {

                button.setIsOver(mouseOver(mx, my, button.getX(), button.getY(), button.getWidth(), button.getHeight()));

            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(MainGame.getGameState() != GameState.FINISHED) return;
        for(int i=0; i < menuObjects.size(); i++) {
            if(menuObjects.get(i) instanceof Button button){
                if(button.getIsOver()){
                    MainGame.setGameState(GameState.LEVELS);
                    button.setIsOver(false);
                    levelHandler.getLevels().get(LevelHandler.getCurrLevel().getLevelNum()).setLocked(false);
                    menuObjects.clear();
                    return;
                }
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
