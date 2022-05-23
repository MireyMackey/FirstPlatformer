package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;
import com.mireymackey.main.Game;
import com.mireymackey.utils.Constants;

import java.awt.*;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.LoadSave.getEntityCoords;

public class EntityManager {
    Playing playing;
    Portal portal;
    Flame flame;
    Player player;
    PlayerSoul playerSoul;


    public EntityManager(Playing playing){
        this.playing = playing;

        int[] portalXY = getEntityCoords(getEntityGreenCode(PORTAL));
        portal = new Portal(portalXY[0], portalXY[1], playing);

        int[] flameXY = getEntityCoords(getEntityGreenCode(FLAME));
        flame = new Flame(flameXY[0], flameXY[1], playing);

        player = new Player(200, 300, (int)(16 * Game.getScale()), (int)(16 * Game.getScale()), playing);
        player.loadLevelData(playing.getLevelManager().getCurrentLevel().getLevelData());

        playerSoul = new PlayerSoul(200, 300, (int)(16 * Game.getScale()), (int)(16 * Game.getScale()), playing);
        playerSoul.loadLevelData(playing.getLevelManager().getCurrentLevel().getLevelData());
    }

    public void update(){
        playerDeadCheck();
        portalCheck();
        flameCheck();

        player.update();
        playerSoul.update();
        portal.update();
        flame.update();
    }

    public void draw(Graphics g){
        playerSoul.draw(g);
        portal.draw(g);
        player.draw(g);
        flame.draw(g);
    }

    private void flameCheck() {
        if (playerSoul.getHitbox().intersects(flame.getHitbox())){
            flame.entityState = Constants.EntityConstants.FlameConstants.FLAME_INACTIVE;
            portal.entityState = Constants.EntityConstants.PortalConstants.PORTAL_ACTIVE;
        }
    }

    private void portalCheck(){
        if (portal.entityState == PortalConstants.PORTAL_ACTIVE && player.getHitbox().intersects(portal.getHitbox())) {
//            playing.getGame().gamestate = Gamestate.WIN;
            resetEntities();
        }
    }

    private void playerDeadCheck(){
        if (player.isPlayerFell() || playerSoul.isPlayerFell()){
            resetEntities();
        }
    }

    public void resetEntities(){
        player.reset();
        playerSoul.reset();
        portal.reset();
        flame.reset();
    }

    public Playing getPlaying() {
        return playing;
    }
    public Player getPlayer() {
        return player;
    }
    public PlayerSoul getPlayerSoul() {
        return playerSoul;
    }
}
