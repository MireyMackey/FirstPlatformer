package com.mireymackey.entities;

import com.mireymackey.main.Game;
import com.mireymackey.utils.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import static com.mireymackey.utils.Constants.PlayerCondition.*;
import static com.mireymackey.utils.HelpMethods.*;


public class Player extends Entity{
    private BufferedImage[][] animations;
    private int animationTick, animationFrameIndex, animationSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection;
    private boolean moving = false;
    private float playerSpeed = 2.00f;

    //controls
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean jump;

    //jumping and gravity
    private float airSpeed = 0;
    private float gravity = 0.03f * Game.getScale();
    private float jumpSpeed = -1.2f * Game.getScale();
    private float fallSpeedAfterCollision = 0.5f * Game.getScale();
    private boolean inAir = false;

    private int[][] levelData;
    private float xDrawOffset = 5 * Game.getScale();
    private float yDrawOffset = 4 * Game.getScale();

    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimation();
        initHitbox(x, y, 5 * Game.getScale(), 11 * Game.getScale());
    }

    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationFrameIndex],
                (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
//        drawHitbox(g);
    }
    private void loadAnimation() {
        BufferedImage[] img = LoadSave.getSpriteImageArray(LoadSave.PLAYER_ATLAS);
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
        if (!isEntityOnFloor(hitbox, levelData))
            inAir = true;
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

        if (inAir){
            if (airSpeed > 0)
                playerAction = GOING_DOWN;
            if (airSpeed < 0)
                playerAction = GOING_UP;
        }

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

        if (jump)
            jump();
        if (!left && !right && !inAir)
            return;

        float xSpeed = 0;

        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;

        if (!inAir)
            if (!isEntityOnFloor(hitbox, levelData))
                inAir = true;

        if (inAir){
            if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
            }
        }
        updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)){
            hitbox.x += xSpeed;
        }else {
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right){
        this.right = right;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public boolean isUp() {
        return up;
    }
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }
    public boolean isDown() {
        return down;
    }

    public void resetDirectionBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
