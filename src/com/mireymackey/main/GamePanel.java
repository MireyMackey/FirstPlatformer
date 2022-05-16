package com.mireymackey.main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    float xCurrent = 100, yCurrent = 100;

    public GamePanel(){
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        MouseInputs mouseInputs = new MouseInputs(this);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setPanelSize();
    }

    public void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void changeXDelta (int value){
        this.xCurrent += value;
    }
    public void changeYDelta (int value){
        this.yCurrent += value;
    }
    public void setRectPosition (int x, int y){
        this.xCurrent = x;
        this.yCurrent = y;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
