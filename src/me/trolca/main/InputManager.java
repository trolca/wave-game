package me.trolca.main;

import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.enums.GameState;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.GameHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputManager extends KeyAdapter{

    private final GameHandler gameHandler;
    private final MainGame game;
    private final HUD hud;
    private final int speed = 7;
    private boolean[] isPressed = new boolean[4]; // 0 - left, 1 - up, 2 - right, 3 - down


    public InputManager(GameHandler gameHandler, HUD hud, MainGame game){
        this.gameHandler = gameHandler;
        this.hud = hud;
        this.game = game;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        GameObject player = null;

        for(GameObject gameObject : gameHandler.getGameObjects()){
            if(gameObject.getId() == ID.PLAYER) player = gameObject;
        }

        if(player == null) gameHandler.getGameObjects().get(1);

        switch (e.getKeyCode()) {
            case 87 ->{ player.setVelY(-speed); isPressed[3] = true; } //down
            case 65 ->{ player.setVelX(-speed); isPressed[0] = true; } //left
            case 83 ->{ player.setVelY(speed); isPressed[1] = true; } //up
            case 68 ->{ player.setVelX(speed); isPressed[2] = true; } //right
            case 27 ->{
                if(MainGame.getGameState() != GameState.RUNNING) return;
                hud.setScore(0);
                MainGame.setGameState(GameState.MENU);
            }
        }

    }

    public void keyReleased(KeyEvent e) {

        GameObject player = null;

        for(GameObject gameObject : gameHandler.getGameObjects()){
            if(gameObject.getId() == ID.PLAYER) player = gameObject;
        }

        if(player == null) gameHandler.getGameObjects().get(1);


        switch (e.getKeyCode()) {
            case 87 ->{ player.setVelY(0);
                isPressed[3] = false;
                if(isPressed[1]) player.setVelY(speed);
            } //down
            case 65 ->{ player.setVelX(0);
                isPressed[0] = false;
                if(isPressed[2]) player.setVelX(speed);
            } //left
            case 83 ->{ player.setVelY(0);
                isPressed[1] = false;
                if(isPressed[3]) player.setVelY(-speed);
            } //up
            case 68 ->{ player.setVelX(0);
                isPressed[2] = false;
                if(isPressed[0]){ player.setVelX(-speed); }

            } //right
        }

    }
}
