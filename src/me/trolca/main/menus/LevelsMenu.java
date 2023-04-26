package me.trolca.main.menus;

import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Level;
import me.trolca.main.abstarcts.Menu;
import me.trolca.main.enums.GameState;
import me.trolca.main.handlers.LevelHandler;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class LevelsMenu extends Menu implements MouseWheelListener {

    private LevelHandler levelHandler;
    private int startX = 10;

    private boolean isOverScrool=false;
    private int startY = 70;
    private final int maxX;
    private final int howMuchSpacing;
    private int start;
    private int howManyLoops;

    public LevelsMenu(LevelHandler levelHandler){
        this.levelHandler = levelHandler;
        howMuchSpacing = levelHandler.getLevels().get(0).getWidth()+30;
        maxX = (int) ((float) (levelHandler.getLevels().size()-2.8)*howMuchSpacing);
        int levelNum = LevelHandler.getCurrLevel() == null ? 1 : LevelHandler.getCurrLevel().getLevelNum()-1;
        System.out.println(startX);
        System.out.println(levelHandler.getLevels().size());
        startX += howMuchSpacing*(levelNum)*-1;
        if(levelNum >= levelHandler.getLevels().size()-2) startX = (maxX+13)*(-1);
    }

    @Override
    public void render(Graphics g) {

        int x = startX;
        int y = startY;

        int levelsSize = levelHandler.getLevels().size();

        start = Math.abs(x/howMuchSpacing);


        x += howMuchSpacing*start;


        howManyLoops = start+levelsSize-(levelsSize-4);
        if(howManyLoops > levelsSize) howManyLoops = levelsSize;

        for(int i=start; i < howManyLoops; i++){
            Level level = levelHandler.getLevels().get(i);

            level.setX(x);
            level.setY(y);

            level.render(g);
            x += level.getWidth()+25;
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.LIGHT_GRAY);

        g2.setComposite(AlphaComposite.SrcOver.derive(0.2f));

        g2.fillRect(10, 405, MainGame.WIDTH-41, 40);

        g2.setComposite(AlphaComposite.SrcOver);

        g2.setColor(Color.WHITE);

        g2.fillRect((int) (((605)*(Math.abs((float) startX/maxX)))+(levelHandler.getLevels().size() <= 4 ? -10 : 10)), 406, 100, 39);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(MainGame.getGameState() != GameState.LEVELS) return;

        int x = e.getX();
        int y = e.getY();

        if(mouseOver(x, y, 10, 405, MainGame.WIDTH-41, 40)){
            startX = (int)  ( (maxX) * (Math.abs( (float) x/605 ) )*-1)+(maxX/13);
            if(startX < -maxX-13) startX = -maxX-13;
            else if(startX > 10) startX = 10;
        }

        for(int i=start; i < howManyLoops; i++){
            Level level = levelHandler.getLevels().get(i);

            if(level.isOver() && !level.isLocked()){
                level.startLogic();
                LevelHandler.setCurrLevel(level);
                level.setOver(false);

                MainGame.setGameState(GameState.RUNNING);

            }
        }

    }

    public void mouseDragged(MouseEvent e){
        if(MainGame.getGameState() != GameState.LEVELS) return;

        int x = e.getX();
        int y = e.getY();

        if(x < 0) return;

        if(!isOverScrool && mouseOver(x, y, 10, 405, MainGame.WIDTH-41, 40)) isOverScrool = true;


        if(isOverScrool){
            startX = (int)  ( (maxX) * (Math.abs( (float) x/605 ) )*-1)+(maxX/13);
            if(startX < -maxX-13) startX = -maxX-13;
            else if(startX > -9) startX = 10;
        }
    }

    public void mouseMoved(MouseEvent e){

        if(MainGame.getGameState() != GameState.LEVELS) return;
        int x = e.getX();
        int y = e.getY();

        for(int i=start; i < howManyLoops; i++){
            Level level = levelHandler.getLevels().get(i);

            Rectangle bounds = level.getBounds();

            level.setOver(mouseOver(x, y, bounds.x, bounds.y, bounds.width, bounds.height));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isOverScrool = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(MainGame.getGameState() != GameState.LEVELS) return;
        if(startX >= 10 && e.getWheelRotation() < 0) return;
        if(Math.abs(startX) >= maxX+13 && e.getWheelRotation() > 0) return;
        startX -= e.getScrollAmount()*9*e.getWheelRotation();
        if(startX < -maxX-13) startX = -maxX-13;
        else if(startX > 10) startX = 10;
    }
}
