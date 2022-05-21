package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.LoadSave.getLevelPortal;
import static com.mireymackey.utils.LoadSave.loadAnimation;

public class PortalManager {
    private Playing playing;

    private Portal portal;
    private BufferedImage[][] portalAnimations;

    public PortalManager(Playing playing){
        this.playing = playing;
        portalAnimations = new BufferedImage[1][9];
        portalAnimations = loadAnimation(PORTAL);
        addPortal();
    }

    public void update(){
        portal.update();
    }

    public void draw(Graphics g){
         g.drawImage(portalAnimations[portal.getEntityState()][portal.getAnimationFrameIndex()],
                 (int)portal.hitbox.x, (int)portal.hitbox.y,
                 PortalConstants.PORTAL_WIDTH, PortalConstants.PORTAL_HEIGHT, null);
    }

    private void addPortal(){
        portal = getLevelPortal();
    }
}
