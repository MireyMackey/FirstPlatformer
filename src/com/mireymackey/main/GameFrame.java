package com.mireymackey.main;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame(GamePanel gamePanel){
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
}
