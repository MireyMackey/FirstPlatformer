package com.mireymackey.entities;

import com.mireymackey.gamestates.Playing;
import com.mireymackey.main.Game;
import com.mireymackey.utils.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.LoadSave.getEntityCoords;
import static com.mireymackey.utils.LoadSave.getEntityCoordsArray;

public class EntityManager {
    private Playing playing;
    private Portal portal;
    private Flame[] flames;
    private Player player;
    private PlayerSoul playerSoul;
    private int[][] levelData;

    private int collectedFlamesCounter = 0;
    private int currentLevelNum;

    public EntityManager(Playing playing, int currentLevelNum){
        this.playing = playing;
        this.currentLevelNum = currentLevelNum;
        loadData();
    }

    public void loadData(){
        levelData = playing.getLevelManager().getCurrentLevel().getLevelData();

        int[] xy;
        xy = getEntityCoords(getEntityGreenCode(PORTAL), currentLevelNum);
        portal = new Portal(xy[0], xy[1]);

        xy = getEntityCoords(getEntityGreenCode(PLAYER), currentLevelNum);
        xy[1] -= Game.getScale() * 4;
        player = new Player(xy[0], xy[1], (int)(16 * Game.getScale()), (int)(16 * Game.getScale()), levelData);
        playerSoul = new PlayerSoul(xy[0], xy[1], (int)(16 * Game.getScale()), (int)(16 * Game.getScale()), levelData);

        int[][] xyArray = getEntityCoordsArray(getEntityGreenCode(FLAME), currentLevelNum);
        flames = new Flame[xyArray.length];
        for(int i = 0; i < xyArray.length; i++)
            flames[i] = new Flame(xyArray[i][0], xyArray[i][1]);
    }

    public void update(){
        playerDeadCheck();
        portalCheck();
        flameCheck();

        player.update();
        soulCreator();
        playerSoul.update();
        portal.update();
        for (Flame flame : flames)
            flame.update();
    }

    public void draw(Graphics g){
        playerSoul.draw(g);
        portal.draw(g);
        player.draw(g);
        for (Flame flame : flames)
            flame.draw(g);
    }

    private void flameCheck() {
        for (Flame flame : flames)
            if (flame.entityState == FlameConstants.FLAME_ACTIVE &&
                    (playerSoul.getHitbox().intersects(flame.getHitbox()) ||
                     player.getHitbox().intersects(flame.getHitbox()))){
                flame.entityState = Constants.EntityConstants.FlameConstants.FLAME_INACTIVE;
                collectedFlamesCounter++;
            }
        if (collectedFlamesCounter == flames.length)
            portal.entityState = Constants.EntityConstants.PortalConstants.PORTAL_ACTIVE;
    }

    private void portalCheck(){
        if (portal.entityState == PortalConstants.PORTAL_ACTIVE && (player.getHitbox().intersects(portal.getHitbox()) ||
                playerSoul.getHitbox().intersects(portal.getHitbox()))) {
            setNewCurrentLevelNum();
            playing.getLevelManager().loadNewLevel(currentLevelNum);
            loadData();
            resetEntities();
        }
    }

    private void setNewCurrentLevelNum(){
        currentLevelNum++;
        if (currentLevelNum > Constants.LevelResources.LEVELS_AMOUNT){
            currentLevelNum = 1;
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
        for (Flame flame : flames)
            flame.reset();
        collectedFlamesCounter = 0;
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
    public int getCollectedFlamesCounter() {
        return collectedFlamesCounter;
    }

    public int getFlamesAmount() {
        return flames.length;
    }
}
