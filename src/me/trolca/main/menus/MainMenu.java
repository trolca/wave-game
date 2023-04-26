package me.trolca.main.menus;

import com.sun.tools.javac.Main;
import me.trolca.main.MainGame;
import me.trolca.main.abstarcts.Menu;
import me.trolca.main.enums.GameState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainMenu extends Menu implements MouseMotionListener {

    private boolean[] isOn = new boolean[2];


    @Override
    public void render(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);

        g2.setFont(new Font("Roboto", Font.BOLD, 80));

        g2.drawString("Game", 245, 80);

        g2.setStroke(new BasicStroke(4));

        if(isOn[0]) g2.setColor(new Color(11, 102, 162));
        else g2.setColor(Color.WHITE);

        g2.drawRect(180, 130, 350, 110); //button play

        g2.setFont(new Font("Roboto", Font.BOLD, 60));

        g2.drawString("Play", 290, 205);

        if(isOn[1]) g2.setColor(new Color(197, 8, 8));
        else g2.setColor(Color.WHITE);

        g2.drawRect(180, 280, 350, 110); //quit button

        g2.drawString("Quit", 285, 350);


    }

    public void mouseMoved(MouseEvent e){

        if(MainGame.getGameState() != GameState.MENU) return;

        int x = e.getX();
        int y = e.getY();



        isOn[0] = mouseOver(x, y, 180, 130, 350, 110);
        isOn[1] = mouseOver(x, y, 180, 280, 350, 110);

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e){

        if(MainGame.getGameState() != GameState.MENU) return;

        if(isOn[0]){
            MainGame.setGameState(GameState.LEVELS);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        if(isOn[1]){
            System.exit(0);
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
