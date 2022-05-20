package com.mireymackey.main;

import com.mireymackey.gamestates.Gamestate;
import com.mireymackey.gamestates.Menu;
import com.mireymackey.gamestates.Playing;

import java.awt.*;

public class Game implements Runnable{
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread gameThread;
    private int MAX_FPS = 120;
    private int MAX_UPS = 200;

    private Playing playing;
    private Menu menu;

    public static final int TILES_DEFAULT_SIZE = 8;
    public static final float SCALE = 6f;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int TILES_IN_WIDTH = 28;
    public static final int TILES_IN_HEIGHT = 16;



    public Game(){
        initClasses();

        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.setBackground(new Color(34, 34, 34));

        startGameLoop();
    }

    public static int getGameWidth(){
        return TILES_IN_WIDTH * TILES_SIZE;
    }
    public static int getGameHeight(){
        return TILES_IN_HEIGHT * TILES_SIZE;
    }
    public static int getTilesInWidth(){
        return TILES_IN_WIDTH;
    }
    public static int getTilesInHeight(){
        return TILES_IN_HEIGHT;
    }
    public static int getTilesSize(){
        return TILES_SIZE;
    }
    public static float getScale(){
        return SCALE;
    }



    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch (Gamestate.state){
            case PLAYING -> {
                playing.update();
            }
            case MENU -> {
                menu.update();
            }
        }
    }
    public void render(Graphics g){
        switch (Gamestate.state){
            case PLAYING -> {
                playing.draw(g);
            }
            case MENU -> {
                menu.draw(g);
            }
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / MAX_FPS;
        double timePerUpdate = 1000000000.0 / MAX_UPS;
        long previousTime = System.nanoTime();
        long lastFrameTime = previousTime;

        int framesCounter = 0;
        int updatesCounter = 0;

        double deltaU = 0;
        double deltaF = 0;

        while (true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updatesCounter++;
                deltaU --;
            }
            if (deltaF >= 1){
                gamePanel.repaint();
                deltaF --;
                framesCounter++;
            }
            if (currentTime - lastFrameTime >= 1000000000){
                lastFrameTime = currentTime;
                System.out.println("FPS: " + framesCounter + " | UPS: " + updatesCounter);
                framesCounter = 0;
                updatesCounter = 0;
            }
        }
    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }

    public void windowFocusLost() {
        if(Gamestate.state == Gamestate.PLAYING)
            playing.getPlayer().resetDirectionBooleans();
    }
}
