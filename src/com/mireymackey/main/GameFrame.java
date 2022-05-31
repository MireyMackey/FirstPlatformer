package com.mireymackey.main;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(GamePanel gamePanel){
        this.setTitle("You are (not) alone");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.setUndecorated(true);
        makeFullScreen();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon("res/ui/logo.png").getImage());

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
