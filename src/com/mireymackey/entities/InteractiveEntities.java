package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.LoadSave.loadAnimation;

public abstract class InteractiveEntities extends Entity{
    protected final Playing playing;
    public int entityState;
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
        g.drawImage(animations[entityState][getAnimationFrameIndex()],
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

    public int getAnimationFrameIndex(){
        return animationFrameIndex;
    }
}
