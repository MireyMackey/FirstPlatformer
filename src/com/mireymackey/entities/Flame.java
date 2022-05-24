package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;
import com.mireymackey.main.Game;

import java.awt.*;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.Constants.EntityConstants.FlameConstants.*;

public class Flame extends AnimatedEntity{

    public Flame(float x, float y) {
        super(x, y, FLAME_WIDTH, FLAME_HEIGHT, FLAME);
        initHitbox(x, y, (int) (6 * Game.getScale()), (int) (6 * Game.getScale()));
        entityState = FLAME_ACTIVE;
    }

    @Override
    public void draw(Graphics g){
        if (entityState == FLAME_ACTIVE){
            super.draw(g);
        }
    }

    @Override
    public void update(){
        if (entityState != FLAME_INACTIVE)
            super.update();
    }
}
