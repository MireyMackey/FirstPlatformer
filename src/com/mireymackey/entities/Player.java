package com.mireymackey.entities;

import static com.mireymackey.utils.Constants.EntityConstants.*;


public class Player extends ControlledEntity{
    public Player(float x, float y, int width, int height, int[][] levelData){
        super(x, y, width, height, PLAYER, levelData);
    }
}
