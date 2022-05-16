package com.mireymackey.main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    int xDelta = 100, yDelta = 100;

    public GamePanel(){
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        MouseInputs mouseInputs = new MouseInputs(this);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public void changeXDelta (int value){
        this.xDelta += value;
        repaint();
    }
    public void changeYDelta (int value){
        this.yDelta += value;
        repaint();
    }
    public void setRectPosition (int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.fillRect(xDelta, yDelta, 200, 50);
    }
}
