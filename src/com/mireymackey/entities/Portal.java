package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;
import com.mireymackey.main.Game;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.Constants.EntityConstants.PortalConstants.*;


public class Portal extends InteractiveEntities {

    public Portal(float x, float y, Playing playing){
        super (x, y, PORTAL_WIDTH, PORTAL_HEIGHT, PORTAL, playing);
        initHitbox(x, y, (int) (8 * Game.getScale()), (int) (16 * Game.getScale()));
    }
}
