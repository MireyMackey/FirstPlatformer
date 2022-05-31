package com.mireymackey.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.Constants.EntityConstants.getEntityHeight;
import static com.mireymackey.utils.LoadSave.loadAnimation;

public abstract class AnimatedEntity extends Entity{
    public int entityState;

    protected BufferedImage[][] animations;
    protected int animationFrameIndex;
    protected int animationTick;
    private int animationSpeed = 15;

    public AnimatedEntity(float x, float y, int width, int height, int entityType) {
        super(x, y, width, height, entityType);

        animations = new BufferedImage[getEntityAnimationsAmount(entityType)][10];
        animations = loadAnimation(entityType);

        entityState = getDefaultEntityState(entityType);
    }

    protected void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationFrameIndex++;
            if (animationFrameIndex  >= animations[entityState].length) {
                animationFrameIndex = 0;
            }
        }
    }
    public void draw(Graphics g){
        g.drawImage(animations[entityState][animationFrameIndex],
                (int)hitbox.x - getEntityOffsetX(entityType), (int)hitbox.y - getEntityOffsetY(entityType),
                getEntityWidth(entityType), getEntityHeight(entityType), null);
//        drawHitbox(g);
    }

    public void update(){
        updateAnimationTick();
    }

    public void reset(){
        hitbox.x = x;
        hitbox.y = y;
        animationFrameIndex = 0;
        animationTick = 0;
        entityState = getDefaultEntityState(entityType);
    }

    protected void resetAnimationTick() {
        animationTick = 0;
        animationFrameIndex = 0;
    }

    public int getAnimationFrameIndex(){
        return animationFrameIndex;
    }
}
