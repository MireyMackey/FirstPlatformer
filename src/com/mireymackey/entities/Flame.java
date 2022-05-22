package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;
import com.mireymackey.main.Game;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.Constants.EntityConstants.FlameConstants.*;

public class Flame extends InteractiveEntities{

    public Flame(float x, float y, Playing playing) {
        super(x, y, FLAME_WIDTH, FLAME_HEIGHT, FLAME, playing);
        initHitbox(x, y, (int) (6 * Game.getScale()), (int) (6 * Game.getScale()));
    }


}
