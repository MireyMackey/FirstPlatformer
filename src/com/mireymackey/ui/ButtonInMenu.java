package com.mireymackey.ui;

import com.mireymackey.gamestates.Gamestate;
import static com.mireymackey.utils.Constants.UIConstants.*;
import static com.mireymackey.utils.Constants.UIConstants.Buttons.*;

import com.mireymackey.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonInMenu {
    private final int xPos, yPos, buttonIndex;
    private final Gamestate gamestate;
    private final Rectangle bounds;
    private BufferedImage[] images;
    private int buttonState;
    private boolean mouseHover;


    public ButtonInMenu(int xPos, int yPos, int buttonIndex, Gamestate gamestate){
        this.xPos = xPos;
        this.yPos = yPos;
        this.buttonIndex = buttonIndex;
        this.gamestate = gamestate;
        this.bounds = new Rectangle(
                xPos - BUTTON_BOUND_WIDTH / 2, (int)(yPos + (BUTTON_HEIGHT - BUTTON_BOUND_HEIGHT) / 2.0),
                BUTTON_BOUND_WIDTH, BUTTON_BOUND_HEIGHT);
        loadImages();
        reset();
    }

    public void update(){
        buttonState = 0;
        if(mouseHover) buttonState = 1;
    }

    public void draw(Graphics g){
        g.drawImage(images[buttonState], xPos - BUTTON_WIDTH / 2, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, null);
    }

    private void loadImages() {
        images = new BufferedImage[2];
        BufferedImage tempImg = LoadSave.getSpriteImage(getUIPath(BUTTONS));
        for (int i = 0; i<images.length; i++){
            images[i] = tempImg.getSubimage(i * BUTTON_DEFAULT_WIDTH, buttonIndex * BUTTON_DEFAULT_HEIGHT,
                    BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }

    public void applyGameState(){
        Gamestate.gamestate = gamestate;
    }
    public void reset(){
        mouseHover = false;
    }

    public boolean isMouseHover() {
        return mouseHover;
    }
    public void setMouseHover(boolean mouseHover) {
        this.mouseHover = mouseHover;
    }
    public Rectangle getBounds() {
        return bounds;
    }
}
