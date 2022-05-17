package com.mireymackey.main;

import com.mireymackey.entities.Player;
import com.mireymackey.levels.LevelManager;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Game implements Runnable{
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread gameThread;
    private int MAX_FPS = 60;
    private int MAX_UPS = 200;

    private static final int TILES_DEFAULT_SIZE = 32;
    private static final float SCALE = 1.5f;
    private static final int TILES_IN_WIDTH = 26;
    private static final int TILES_IN_HEIGHT = 14;

    private Player player;
    private LevelManager levelManager;

    public Game(){
        initClasses();

        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gameFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }

    public int getGameWidth(){
        return TILES_IN_WIDTH * (int)(TILES_DEFAULT_SIZE * SCALE);
    }
    public int getGameHeight(){
        return TILES_IN_HEIGHT * (int)(TILES_DEFAULT_SIZE * SCALE);
    }

    private void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    private void initClasses() {
        player = new Player(200, 200, (int)(64*SCALE), (int)(40*SCALE));
        levelManager = new LevelManager(this);
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
        levelManager.update();
    }
    public void render(Graphics g){
        player.render(g);
        levelManager.draw(g);
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

    public Player getPlayer(){
        return player;
    }
}
