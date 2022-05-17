package com.mireymackey.entities;

import com.mireymackey.utils.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.PlayerCondition.*;
import com.mireymackey.utils.LoadSave.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int animationTick, animationFrameIndex, animationSpeed = 20;
    private int playerAction = IDLE;
    private int playerDirection;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 3.00f;

    public Player(float x, float y){
        super(x, y);
        loadAnimation();
    }

    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationFrameIndex], (int)x, (int)y,64 * 3, 40 * 3, null);
    }
    private void loadAnimation() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];
        for (int aniType = 0; aniType < animations.length; aniType++)
            for (int aniFrame = 0; aniFrame < animations[aniType].length; aniFrame++)
                animations[aniType][aniFrame] = img.getSubimage(aniFrame * 64, aniType * 40, 64, 40);
    }
    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving) playerAction = RUNNING;
        else playerAction = IDLE;

        if (attacking) playerAction = ATTACK_1;

        if (startAnimation != playerAction) resetAnimationTick();
    }

    private void resetAnimationTick() {
        animationTick = 0;
        animationFrameIndex = 0;
    }

    private void updatePosition(){
        moving = false;

        if (left && !right){
            x -= playerSpeed;
            moving = true;
        } else if (right && !left){
            x += playerSpeed;
            moving = true;
        }

        if (up && !down){
            y -= playerSpeed;
            moving = true;
        } else if (down && !up){
            y += playerSpeed;
            moving = true;
        }
    }
    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationFrameIndex++;
            if (animationFrameIndex  >= getFrameAmount(playerAction)) {
                animationFrameIndex = 0;
                attacking = false;
            }
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    public void resetDirectionBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
