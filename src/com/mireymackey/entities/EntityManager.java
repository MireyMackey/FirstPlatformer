package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;
import com.mireymackey.main.Game;
import com.mireymackey.utils.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.LoadSave.getEntityCoords;

public class EntityManager {
    private Playing playing;
    private Portal portal;
    private Flame flame;
    private Player player;
    private PlayerSoul playerSoul;
    private int[][] levelData;


    public EntityManager(Playing playing){
        this.playing = playing;
        levelData = playing.getLevelManager().getCurrentLevel().getLevelData();

        int[] xy;
        xy = getEntityCoords(getEntityGreenCode(PORTAL));
        portal = new Portal(xy[0], xy[1]);

        xy = getEntityCoords(getEntityGreenCode(FLAME));
        flame = new Flame(xy[0], xy[1]);

        xy = getEntityCoords(getEntityGreenCode(PLAYER));
        xy[1] -= Game.getScale() * 4;
        player = new Player(xy[0], xy[1], (int)(16 * Game.getScale()), (int)(16 * Game.getScale()), levelData);
        playerSoul = new PlayerSoul(xy[0], xy[1], (int)(16 * Game.getScale()), (int)(16 * Game.getScale()), levelData);
    }

    public void update(){
        playerDeadCheck();
        portalCheck();
        flameCheck();

        player.update();
        soulCreator();
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
        if (playerSoul.getHitbox().intersects(flame.getHitbox()) || player.getHitbox().intersects(flame.getHitbox())){
            flame.entityState = Constants.EntityConstants.FlameConstants.FLAME_INACTIVE;
            portal.entityState = Constants.EntityConstants.PortalConstants.PORTAL_ACTIVE;
        }
    }

    private void portalCheck(){
        if (portal.entityState == PortalConstants.PORTAL_ACTIVE && (player.getHitbox().intersects(portal.getHitbox()) ||
                playerSoul.getHitbox().intersects(portal.getHitbox()))) {
//            playing.getGame().gamestate = Gamestate.WIN;
            resetEntities();
        }
    }

    private void playerDeadCheck(){
        if (player.isPlayerFell() || playerSoul.isPlayerFell()){
            resetEntities();
        }
    }

    private void soulCreator(){
        if (!playerSoul.isActive){
            Rectangle2D.Float tempHitbox = new Rectangle2D.Float(player.hitbox.x, player.hitbox.y,
                    player.hitbox.width, player.hitbox.height);
            tempHitbox.x -= player.getXSpeed();
            tempHitbox.y -= player.getAirSpeed();
            if (player.isHorizontalSoulCollision) {
                playerSoul.hitbox.x = ControlledEntity.getEntityXPosNextToWall(tempHitbox, player.getXSpeed());
                playerSoul.isActive = true;
            }
            if (player.isVerticalSoulCollision) {
                playerSoul.hitbox.y = ControlledEntity.GetEntityYPosUnderRoofOrAboveFloor(tempHitbox, player.getAirSpeed()) -1;
                playerSoul.isActive = true;
            }
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
