package me.trolca.main.handlers;

import me.trolca.main.abstarcts.Enemy;
import me.trolca.main.enums.ID;
import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.objects.Player;
import me.trolca.main.objects.Wall;

import java.awt.*;
import java.util.ArrayList;

public class GameHandler {

    private final ArrayList<GameObject> gameObjects;

    private final ArrayList<Wall> walls;

    private final ParticleHandler particleHandler;

    private Player player;

    public GameHandler(ParticleHandler particleHandler){
        gameObjects = new ArrayList<>();
        walls = new ArrayList<>();
        this.particleHandler = particleHandler;
    }

    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
        if(gameObject instanceof Wall wall) walls.add(wall);
        if(gameObject.getId() == ID.PLAYER) player = (Player) gameObject;
    }

    public void removeGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public void clearEnemys(){
        gameObjects.removeIf(gameObject -> gameObject instanceof Enemy);
    }

    public ParticleHandler getParticleHandler() {
        return particleHandler;
    }

    public Player getPlayer(){
        return player;
    }

    public void tick(){

        for(int i=0; i < gameObjects.size(); i++){
            gameObjects.get(i).tick();
        }

    }

    public void render(Graphics g){

        for(int i=0; i < gameObjects.size(); i++){
            gameObjects.get(i).render(g);
        }

    }
    public ArrayList<Wall> getWalls() {
        return walls;
    }



}
