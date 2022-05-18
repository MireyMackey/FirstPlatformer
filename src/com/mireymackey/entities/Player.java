package com.mireymackey.entities;

import com.mireymackey.main.Game;
import com.mireymackey.utils.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import static com.mireymackey.utils.Constants.PlayerCondition.*;
import static com.mireymackey.utils.HelpMethods.canMoveHere;


public class Player extends Entity{
    private BufferedImage[][] animations;
    private int animationTick, animationFrameIndex, animationSpeed = 20;
    private int playerAction = IDLE;
    private int playerDirection;
    private boolean moving = false;
    private boolean left, up, right, down;
    private float playerSpeed = 3.00f;
    private int[][] levelData;
    private float xDrawOffset = 5 * Game.getScale();
    private float yDrawOffset = 4 * Game.getScale();

    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimation();
        initHitbox(x, y, 6 * Game.getScale(), 12 * Game.getScale());
    }

    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationFrameIndex],
                (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        drawHitbox(g);
    }
    private void loadAnimation() {
        BufferedImage[] img = LoadSave.getSpriteAtlases(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[6][10];
        for (int aniType = 0; aniType < animations.length; aniType++)
            for (int aniFrame = 0; aniFrame < animations[aniType].length; aniFrame++)
                try {
                    animations[aniType][aniFrame] = img[aniType].getSubimage(aniFrame * 16, 0, 16, 16);
                } catch (RasterFormatException e){
                    break;
                }
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
    }

    private boolean transitionAnimationBegin = false;
    private void setAnimation() {
        int previousAnimation = playerAction;

        if ((previousAnimation == STOP_RUNNING || previousAnimation == GROUND_HIT)
                && (animationFrameIndex != 0 || transitionAnimationBegin)){
            if (animationFrameIndex == 1){
                transitionAnimationBegin = false;
            }
            return;
        }

        if (moving) {
            playerAction = RUNNING;
        } else playerAction = IDLE;

        if (previousAnimation != playerAction) {
            resetAnimationTick();
            if (previousAnimation == RUNNING && playerAction == IDLE) {
                playerAction = STOP_RUNNING;
                transitionAnimationBegin = true;
            }
        }
    }
    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationFrameIndex++;
            if (animationFrameIndex  >= getFrameAmount(playerAction)) {
                animationFrameIndex = 0;
            }
        }
    }
    private void resetAnimationTick() {
        animationTick = 0;
        animationFrameIndex = 0;
    }

    private void updatePosition(){
        moving = false;
        if (!left && !right && !up && ! down)
            return;

        float xSpeed = 0, ySpeed = 0;

        if (left && !right) xSpeed = -playerSpeed;
        else if (right && !left) xSpeed = playerSpeed;

        if (up && !down) ySpeed = -playerSpeed;
        else if (down && !up) ySpeed = playerSpeed;

        if (canMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, levelData)){
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
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

    public void resetDirectionBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
