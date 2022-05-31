package com.mireymackey.ui;

import com.mireymackey.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.LoadSave.getSpriteImage;
import static com.mireymackey.utils.HelpMethods.flipImage;

public class AnimatedLogo {
    private final int xPos, yPos;
    private int animationFrameIndex = 0, animationTick = 0;
    private final int animationSpeed = 13;
    private final BufferedImage[] playerAnimation = new BufferedImage[6];
    private final BufferedImage[] soulAnimation = new BufferedImage[6];
    private final int scale = 4;

    public AnimatedLogo(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;

        loadAnimations();
    }

    public void draw(Graphics g){
        g.drawImage(playerAnimation[animationFrameIndex], xPos, yPos,
                getEntityWidth(PLAYER) * scale, getEntityHeight(PLAYER) * scale, null);
        g.drawImage(soulAnimation[(animationFrameIndex + 3) % 6],
                xPos + (int)(8 * Game.getScale() * scale), yPos + (int)(3 * Game.getScale() * scale),
                getEntityWidth(PLAYER) * scale, getEntityHeight(PLAYER) * scale, null);
    }

    public void update(){
        updateAnimationTick();
    }

    protected void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationFrameIndex++;
            if (animationFrameIndex  >= playerAnimation.length) {
                animationFrameIndex = 0;
            }
        }
    }

    private void loadAnimations() {
        BufferedImage playerGoingDownSprite = getSpriteImage("res/entities/player/going_down.png"),
                soulGoingDownSprite = getSpriteImage("res/entities/playerSoul/going_down.png");
        for (int animationFrame = 0; animationFrame < 6; animationFrame++){
            playerAnimation[animationFrame] = flipImage(playerGoingDownSprite.getSubimage(
                    animationFrame * getEntityDefaultWidth(PLAYER), 0,
                    getEntityDefaultWidth(PLAYER), getEntityDefaultHeight(PLAYER)));
            soulAnimation[animationFrame] = soulGoingDownSprite.getSubimage(
                    animationFrame * getEntityDefaultWidth(PLAYER), 0,
                    getEntityDefaultWidth(PLAYER), getEntityDefaultHeight(PLAYER));
        }
    }


}
