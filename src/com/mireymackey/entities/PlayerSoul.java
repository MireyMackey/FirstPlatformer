package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;

import static com.mireymackey.utils.LoadSave.loadAnimation;
import static com.mireymackey.utils.Constants.EntityConstants.PLAYER_SOUL;


public class PlayerSoul extends Player{

    public PlayerSoul(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height, playing);
        animations = loadAnimation(PLAYER_SOUL);
    }


}
