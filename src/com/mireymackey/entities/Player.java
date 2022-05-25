package com.mireymackey.entities;

import static com.mireymackey.utils.Constants.EntityConstants.*;


public class Player extends ControlledEntity{

    boolean isHorizontalSoulCollision = false;
    boolean isVerticalSoulCollision = false;

    public Player(float x, float y, int width, int height, int[][] levelData){
        super(x, y, width, height, PLAYER, levelData);
    }

    @Override
    protected void updateXPos() {
        if (canMoveHere(hitbox.x + currentXSpeed, hitbox.y, hitbox.width, hitbox.height, levelData, true)) {
            hitbox.x += currentXSpeed;
        }
        else if (canMoveHere(hitbox.x + currentXSpeed, hitbox.y, hitbox.width, hitbox.height, levelData, false)){
            hitbox.x += currentXSpeed;
            isHorizontalSoulCollision = true;
        } else
            hitbox.x = getEntityXPosNextToWall(hitbox, currentXSpeed);
    }

    @Override
    protected void updateYPos(){
        if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData, true)) {
            hitbox.y += airSpeed;
            airSpeed += gravity;
        } else if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData, false)) {
            hitbox.y += airSpeed;
            airSpeed += gravity;
            isVerticalSoulCollision = true;
        } else {
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
            if (airSpeed > 0){
                resetInAir();}
            else
                airSpeed = fallSpeedAfterCollision;
        }
    }

    @Override
    public void reset(){
        super.reset();
        isVerticalSoulCollision = false;
        isHorizontalSoulCollision = false;
    }
}
