package com.mireymackey.entities;

import com.mireymackey.main.Game;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.Constants.EntityConstants.PortalConstants.*;


public class Portal extends InteractiveEntities {

    public Portal(float x, float y){
        super (x, y, PORTAL_WIDTH, PORTAL_HEIGHT, PORTAL);
        initHitbox(x, y, (int) (8 * Game.getScale()), (int) (16 * Game.getScale()));
    }

}
