package me.trolca.main;

import me.trolca.main.abstarcts.Menu;
import me.trolca.main.enums.GameState;
import me.trolca.main.enums.ID;
import me.trolca.main.handlers.AnimationHandler;
import me.trolca.main.handlers.GameHandler;
import me.trolca.main.handlers.LevelHandler;
import me.trolca.main.handlers.ParticleHandler;
import me.trolca.main.menus.DeathMenu;
import me.trolca.main.menus.FinishMenu;
import me.trolca.main.menus.LevelsMenu;
import me.trolca.main.menus.MainMenu;
import me.trolca.main.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class MainGame extends Canvas implements Runnable{

    public static final int WIDTH = 750, HEIGHT = 500;

    public double amountOfTicks = 50.0D;
    private double ns = 1.0E9D / amountOfTicks;
    private Frame frame;
    public boolean running = false;
    private static GameState gameState;
    private GameState oldGameState;
    private Thread thread;
    private ParticleHandler particleHandler;
    private GameHandler gameHandler;
    private InputManager inputManager;
    private LevelHandler levelHandler;
    private AnimationHandler animationHandler;
    private me.trolca.main.abstarcts.Menu currMenu;

    private HUD hud;

    public MainGame(){
        particleHandler = new ParticleHandler();
        gameHandler = new GameHandler(particleHandler);
        gameHandler.addGameObject(new Player(250, 250, ID.PLAYER, gameHandler));
        hud = new HUD(gameHandler);
        animationHandler = new AnimationHandler();


        levelHandler = new LevelHandler(gameHandler, hud);

        inputManager = new InputManager(gameHandler, hud, this);
        frame = new Frame(WIDTH, HEIGHT, "GAMEE", this);
        gameState = GameState.LEVELS;
        checkMenuFromState();
        this.addKeyListener(inputManager);
    }



    public synchronized void start(){
        this.thread = new Thread(this);
        running = true;
        this.thread.start();
    }

    public synchronized void stop()  {
            this.running = false;
            //this.thread.join();

    }

    public void tick(){
        checkMenuFromState();

        switch(gameState){
            case RUNNING -> {
                gameHandler.getParticleHandler().tick();
                gameHandler.tick();
                hud.tick();
                if(LevelHandler.getCurrLevel() != null)
                    LevelHandler.getCurrLevel().levelTick();
            }
            case DEAD, FINISHED -> gameHandler.getParticleHandler().tick();


        }

        animationHandler.tick();
    }

    private void checkMenuFromState(){

        if(oldGameState == gameState) return;

        switch (gameState){
            case MENU -> {
                currMenu = new MainMenu();
            }

            case LEVELS -> {
                currMenu = new LevelsMenu(levelHandler);
            }

            case DEAD -> {
                currMenu = new DeathMenu(gameHandler, hud);
            }

            case FINISHED -> {
                currMenu = new FinishMenu(animationHandler, levelHandler);
            }

            default -> currMenu = null;
        }

        if(currMenu != null){
            this.addMouseListener(currMenu);
            this.addMouseMotionListener(currMenu);
            if(currMenu instanceof LevelsMenu levelsMenu) this.addMouseWheelListener(levelsMenu);
        }

        oldGameState = gameState;


    }

    public void render(){

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            bs = this.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);

        g.fillRect(0, 0, WIDTH ,HEIGHT);

        if(currMenu != null){
            currMenu.render(g);

            for(int i=0; i < currMenu.getMenuObjects().size(); i++){
                currMenu.getMenuObjects().get(i).render(g);
            }
        }



        switch (gameState){
            case RUNNING -> {
                gameHandler.render(g);
                gameHandler.getParticleHandler().render(g);
                hud.render(g);
            }

            case DEAD, FINISHED ->{
                gameHandler.getParticleHandler().render(g);
            }

        }

        g.dispose();
        bs.show();

    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double delta = 0.0D;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(this.running) {
            long now = System.nanoTime();
            delta += (double)(now - lastTime) / ns;

            for(lastTime = now; delta >= 1.0D; --delta) {
                this.tick();
            }

            if (this.running) {
                this.render();
            }

            frames++;
            if (System.currentTimeMillis() - timer > 1000L) {
                timer += 1000L;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        this.stop();
    }

    public static int clamp(int var, int min, int max){
        if(var >= max){
            return max;
        }else{
            return Math.max(var, min);
        }
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        MainGame.gameState = gameState;


    }

    public  Menu getCurrMenu() {
        return currMenu;
    }

    public  void setCurrMenu(Menu currMenu) {
        this.currMenu = currMenu;
    }

    public static void main(String[] args){
        new MainGame();
    }

    public void setAmountOfTicks(double amountOfTicks) {
        this.amountOfTicks = amountOfTicks;
        this.ns = 1.0E9D / amountOfTicks;;
    }

}
