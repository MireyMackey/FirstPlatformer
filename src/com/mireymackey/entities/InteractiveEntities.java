package com.mireymackey.entities;

import static com.mireymackey.utils.Constants.EntityConstants.*;

public abstract class InteractiveEntities extends Entity{
    private int entityState, entityType;
    private int animationFrameIndex, animationTick, animationSpeed = 15;

    public InteractiveEntities(float x, float y, int width, int height, int entityType){
        super(x, y, width, height);
        this.entityType = entityType;
        initHitbox(x, y, width, height);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationFrameIndex++;
            if (animationFrameIndex  >= getEntityFrameAmount(PORTAL)) {
                animationFrameIndex = 0;
            }
        }
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
