package com.mireymackey.main;

import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(GamePanel gamePanel){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.setUndecorated(true);
        makeFullScreen();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void makeFullScreen() {
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = genv.getDefaultScreenDevice();
         if (gd.isFullScreenSupported()){
             gd.setFullScreenWindow(this);
         }
    }
}
