package com.mireymackey.entities;

import static com.mireymackey.utils.Constants.EntityConstants.PLAYER_SOUL;


public class PlayerSoul extends ControlledEntity{

    public PlayerSoul(float x, float y, int width, int height, int[][] levelData) {
        super(x, y, width, height, PLAYER_SOUL, levelData);
    }
}
