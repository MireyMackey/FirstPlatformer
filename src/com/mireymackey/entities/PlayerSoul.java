package com.mireymackey.entities;

import java.awt.*;

import static com.mireymackey.utils.Constants.EntityConstants.PLAYER_SOUL;


public class PlayerSoul extends ControlledEntity{

    public boolean isActive;
    public PlayerSoul(float x, float y, int width, int height, int[][] levelData) {
        super(x, y, width, height, PLAYER_SOUL, levelData);
        soulCheck = true;
    }

    @Override
    public void draw(Graphics g){
        if (isActive)
            super.draw(g);
    }

    @Override
    public void update(){
        if (isActive)
            super.update();
    }

    @Override
    public void reset(){
        super.reset();
        isActive = false;

    }
}
