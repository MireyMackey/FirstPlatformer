package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;
import com.mireymackey.main.Game;

import java.awt.*;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.Constants.EntityConstants.PortalConstants.*;


public class Portal extends AnimatedEntity {

    public Portal(float x, float y){
        super (x, y, PORTAL_WIDTH, PORTAL_HEIGHT, PORTAL);
        initHitbox(x, y, (int) (8 * Game.getScale()), (int) (16 * Game.getScale()));
        entityState = PORTAL_INACTIVE;
    }
}
