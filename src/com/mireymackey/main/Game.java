package com.mireymackey.main;

public class Game implements Runnable{
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread gameThread;
    private int MAX_FPS = 120;

    public Game(){
        gamePanel = new GamePanel();
        gameFrame = new GameFrame(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / MAX_FPS;
        long currentTime = System.nanoTime();
        long lastFrameTime = currentTime;
        int framesCounter = 0;
        long lastCheckTime = currentTime;
        while (true){
            currentTime = System.nanoTime();
            if (currentTime - lastFrameTime >= timePerFrame){
                gamePanel.repaint();
                lastFrameTime = currentTime;
                framesCounter++;
            }

            if (currentTime - lastCheckTime >= 1000000000){
                lastCheckTime = currentTime;
//                System.out.println("FPS: " + framesCounter);
                framesCounter = 0;
            }
        }
    }
}
