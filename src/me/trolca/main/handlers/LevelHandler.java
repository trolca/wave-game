package me.trolca.main.handlers;

import me.trolca.main.HUD;
import me.trolca.main.abstarcts.Level;
import me.trolca.main.levels.Level1;
import me.trolca.main.levels.Level2;
import me.trolca.main.levels.Level3;
import me.trolca.main.levels.Level4;

import java.util.ArrayList;

public class LevelHandler {

    private ArrayList<Level> levels;
    private static Level currLevel;

    public static Level getCurrLevel(){
        return currLevel;
    }

    public static void setCurrLevel(Level level){
        currLevel = level;
    }

    public LevelHandler(GameHandler gameHandler, HUD hud){

        levels = new ArrayList<>();

        levels.add(new Level1(gameHandler, hud));
        levels.add(new Level2(gameHandler, hud));
        levels.add(new Level3(gameHandler, hud));
        levels.add(new Level4(gameHandler, hud));

        currLevel = levels.get(0);


    }

    public ArrayList<Level> getLevels(){
        return levels;
    }
}
