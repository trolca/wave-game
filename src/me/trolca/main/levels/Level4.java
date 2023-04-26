package me.trolca.main.levels;

import me.trolca.main.HUD;
import me.trolca.main.abstarcts.Level;
import me.trolca.main.handlers.GameHandler;

import java.awt.*;

public class Level4 extends Level {

    public Level4(GameHandler gameHandler, HUD hud) {
        super(gameHandler, hud);
    }

    @Override
    public void tick() {

    }

    @Override
    public int getScoreObjective() {
        return 0;
    }

    @Override
    public Color getNameColor() {
        return new Color(124, 62, 17);
    }

    @Override
    public void levelTick() {

    }

    @Override
    public String getName() {
        return "Whoa so many!";
    }

    @Override
    public int getLevelNum() {
        return 4;
    }

    @Override
    public void drawThubnail(Graphics g) {

    }

    @Override
    public void startLogic() {

    }
}
