package com.mireymackey.main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    float xCurrent = 100, yCurrent = 100;
    float xDelta = 1f, yDelta = 1f;

    Color rectColor;
    Random random;

    public GamePanel(){
        random = new Random();
        KeyboardInputs keyboardInputs = new KeyboardInputs(this);
        MouseInputs mouseInputs = new MouseInputs(this);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
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
        g.setColor(rectColor);
        g.fillRect((int)xCurrent, (int)yCurrent, 200, 50);
        updateRect();


    }

    private void updateRect() {
        xCurrent += xDelta;
        if (xCurrent > this.getWidth() || xCurrent < 0){
            xDelta *= -1;
            rectColor = getRandColor();
        }
        yCurrent += yDelta;
        if (yCurrent > this.getHeight() || yCurrent < 0){
            yDelta *= -1;
            rectColor = getRandColor();
        }
    }

    private Color getRandColor() {
        return new Color(random.nextInt(0, 255), random.nextInt(0, 255), random.nextInt(0, 255));
    }
}
