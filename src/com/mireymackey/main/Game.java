package com.mireymackey.main;

import com.mireymackey.entities.Player;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Game implements Runnable{
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread gameThread;
    private int MAX_FPS = 60;
    private int MAX_UPS = 200;

    Player player;

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

    private void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    private void initClasses() {
        player = new Player(200, 200);
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
    }
    public void render(Graphics g){
        player.render(g);
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
