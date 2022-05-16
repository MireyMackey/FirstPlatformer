package com.mireymackey.main;

public class Game {
    GameFrame gameFrame;
    GamePanel gamePanel;

    public Game(){
        gamePanel = new GamePanel();
        gameFrame = new GameFrame(gamePanel);
        gamePanel.requestFocus();
    }
}
