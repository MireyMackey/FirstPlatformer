package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.LoadSave.loadAnimation;

public abstract class InteractiveEntities extends Entity{
    protected final Playing playing;
    protected int entityState = 0;
    protected final int entityType;
    protected BufferedImage[][] animations;
    protected int animationFrameIndex;
    protected int animationTick;
    protected int animationSpeed = 15;

    public InteractiveEntities(float x, float y, int width, int height, int entityType, Playing playing){
        super(x, y, width, height);
        this.playing = playing;

        this.entityType = entityType;
        animations = new BufferedImage[getEntityAnimationsAmount(entityType)][9];
        animations = loadAnimation(entityType);

        initHitbox(x, y, width, height);
    }

    protected void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationFrameIndex++;
            if (animationFrameIndex  >= getEntityFrameAmount(entityType)) {
                animationFrameIndex = 0;
            }
        }
    }
    public void draw(Graphics g){
        g.drawImage(animations[getEntityState()][getAnimationFrameIndex()],
                (int)hitbox.x - getEntityOffsetX(entityType), (int)hitbox.y - getEntityOffsetY(entityType),
                getEntityWidth(entityType), getEntityHeight(entityType), null);
        drawHitbox(g);
    }

    public void update(){
        updateAnimationTick();

    }

    public int getAnimationFrameIndex(){
        return animationFrameIndex;
    }

    public int getEntityState(){
        return entityState;
    }
}
