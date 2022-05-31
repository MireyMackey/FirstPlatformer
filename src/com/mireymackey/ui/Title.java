package com.mireymackey.ui;

import com.mireymackey.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.UIConstants.Buttons.BUTTON_HEIGHT;
import static com.mireymackey.utils.Constants.UIConstants.Buttons.BUTTON_WIDTH;
import static com.mireymackey.utils.LoadSave.getSpriteImage;

public class Title {
    private BufferedImage img = getSpriteImage("res/ui/title2.png");
    private final int xPos, yPos;
    public Title(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw(Graphics g){
        g.drawImage(img, xPos, yPos, (int)(img.getWidth() * Game.getScale() / 1.1),
                (int)(img.getHeight() * Game.getScale() / 1.1), null);
    }
}
