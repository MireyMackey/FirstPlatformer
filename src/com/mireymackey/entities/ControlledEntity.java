package com.mireymackey.entities;

import com.mireymackey.main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.Constants.EntityConstants.*;
import static com.mireymackey.utils.Constants.PlayerAction.*;
import static com.mireymackey.utils.HelpMethods.flipImage;

public abstract class ControlledEntity extends AnimatedEntity{

    protected boolean directionIsRight = true;
    protected boolean moving = false;
    private final float speed = 2.00f;
    protected float currentXSpeed = 0;


    //controls
    private boolean left;
    private boolean right;
    private boolean down;
    private boolean jump;

    //jumping and gravity
    protected float airSpeed = 0;
    protected final float gravity = 0.02f * Game.getScale();
    protected final float jumpSpeed = -1f * Game.getScale();
    protected final float fallSpeedAfterCollision = 0.1f * Game.getScale();
    protected boolean inAir = false;

    protected int[][] levelData;

    public boolean soulCheck = false;

    public ControlledEntity(float x, float y, int width, int height, int entityType, int[][] levelData) {
        super(x, y, width, height, entityType);
        this.levelData = levelData;

        if (!isEntityOnFloor(hitbox, levelData, soulCheck))
            inAir = true;
    }

    @Override
    public void update(){
        super.update();
        updateDirection();
        updatePosition();
        setAnimation();
    }

    @Override
    public void draw(Graphics g){
        BufferedImage img;
        if (directionIsRight) img = animations[entityState][getAnimationFrameIndex()];
        else img = flipImage(animations[entityState][getAnimationFrameIndex()]);
        g.drawImage(img,(int)hitbox.x - getEntityOffsetX(entityType),(int)hitbox.y - getEntityOffsetY(entityType),
                getEntityWidth(entityType), getEntityHeight(entityType), null);
    }

    private void updateDirection(){
        if (right && !left){
            directionIsRight = true;
        }
        else if (left && !right){
            directionIsRight = false;
        }
    }

    private void updatePosition(){
        moving = false;
        currentXSpeed = 0;

        if (jump)
            jump();
        if (!inAir)
            if (left == right)
                return;

        if (left)
            currentXSpeed -= speed;
        if (right)
            currentXSpeed += speed;

        if (!inAir)
            if (!isEntityOnFloor(hitbox, levelData, soulCheck))
                inAir = true;

        if (inAir) updateYPos();
        updateXPos();
        moving = true;
    }

    protected void updateXPos() {
        if (canMoveHere(hitbox.x + currentXSpeed, hitbox.y, hitbox.width, hitbox.height, levelData, soulCheck)){
            hitbox.x += currentXSpeed;
        }else {
            hitbox.x = getEntityXPosNextToWall(hitbox, currentXSpeed);
        }
    }

    protected void updateYPos(){
        if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData, soulCheck)) {
            hitbox.y += airSpeed;
            airSpeed += gravity;
        } else {
            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
            if (airSpeed > 0){
                resetInAir();}
            else
                airSpeed = fallSpeedAfterCollision;
        }
    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    protected void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    public boolean isPlayerFell(){
        return (hitbox.y + hitbox.height >= Game.getGameHeight() - 1);
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right){
        this.right = right;
    }
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }
    public boolean isDown() {
        return down;
    }

    public void resetDirectionBooleans() {
        left = false;
        right = false;
        down = false;
    }

    @Override
    public void reset(){
        super.reset();
        resetInAir();
        moving = false;
        hitbox.x = x;
        hitbox.y = y;
        entityState = getDefaultEntityState(entityType);

        if (!isEntityOnFloor(hitbox, levelData, soulCheck))
            inAir = true;
    }


    protected static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData, boolean isSoulCheck){
        if (!isSolid(x, y, levelData, isSoulCheck) //top left
                && !isSolid(x + width, y + height, levelData, isSoulCheck) //bottom right
                && !isSolid(x + width, y, levelData, isSoulCheck) //top right
                && !isSolid(x, y + height, levelData, isSoulCheck) //bottom left
                && !isSolid(x, y + height / 2, levelData, isSoulCheck) //left center
                && !isSolid(x + width, y + height / 2, levelData, isSoulCheck) //right center
                && !isSolid(x + width / 2, y, levelData, isSoulCheck) //top center
                && !isSolid(x + width / 2, y + height, levelData, isSoulCheck)) //bottom center
            return true;
        return false;
    }

    private static boolean isSolidTile(int tileIndex, boolean isSoulCheck){
        int tileIndexOffset;
        if (isSoulCheck) tileIndexOffset = 64;
        else tileIndexOffset = 0;

        if ((tileIndex >= 0 + tileIndexOffset && tileIndex <= 16 + tileIndexOffset) ||
                (tileIndex >= 23 + tileIndexOffset && tileIndex <= 31 + tileIndexOffset) ||
                (tileIndex >= 33 + tileIndexOffset && tileIndex <= 36 + tileIndexOffset) ||
                (tileIndex >= 39 + tileIndexOffset && tileIndex <= 49 + tileIndexOffset) ||
                (tileIndex >= 56 + tileIndexOffset && tileIndex <= 57 + tileIndexOffset))
            return true;
        return false;
    }

    protected static boolean isSolid(float x, float y, int[][] levelData, boolean isSoulCheck){
        float xIndex = x / Game.getTilesSize();
        float yIndex = y / Game.getTilesSize();

        int value;
        try {
            value = levelData[(int) yIndex][(int) xIndex];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        if (isSoulCheck) {
            if (isSolidTile(value, true) || isSolidTile(value, false))
                return true;
        } else
            if (isSolidTile(value, false))
                return true;
        return false;
    }

    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int)(hitbox.x / Game.getTilesSize());
        if(xSpeed > 0){
            //right
            int tileX = currentTile * Game.getTilesSize();
            int xOffset = (int) (Game.getTilesSize() - hitbox.width);
            return tileX + xOffset -1;
        }else {
            //left
            return currentTile * Game.getTilesSize();
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        if (airSpeed > 0){
            //falling, so touching floor
            int currentTile = (int) ((hitbox.y + hitbox.height) / Game.getTilesSize());
            int tilePos = currentTile * Game.getTilesSize();
            int yOffset = (int) (Game.getTilesSize() - hitbox.height);
            return tilePos + yOffset - 1;
        } else {
            //jumping, so touching the roof
            int currentTile = (int) ((hitbox.y) / Game.getTilesSize());
            int tilePos = currentTile * Game.getTilesSize();
            return tilePos + 1;
        }
    }

    private static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData, boolean isSoulCheck) {
        // Check if the pixels below bottom left and bottom right is solid
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData, isSoulCheck))
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData, isSoulCheck))
                return false;
        return true;
    }

    private boolean transitionAnimationBegin = false;
    private void setAnimation() {
        int previousAnimation = entityState;

        if ((previousAnimation == STOP_RUNNING || previousAnimation == GROUND_HIT)
                && (animationFrameIndex != 0 || transitionAnimationBegin)){
            if (animationFrameIndex == 1){
                transitionAnimationBegin = false;
            }
            return;
        }

        if (moving) {
            entityState = RUNNING;
        } else entityState = IDLE;

        if (inAir){
            if (getAirSpeed() > 0)
                entityState = GOING_DOWN;
            if (getAirSpeed() < 0)
                entityState = GOING_UP;
        }

        if (previousAnimation != entityState) {
            resetAnimationTick();
            if (previousAnimation == RUNNING && entityState == IDLE) {
                entityState = STOP_RUNNING;
                transitionAnimationBegin = true;
            }
        }
    }

    public float getAirSpeed() {
        return airSpeed;
    }

    public float getXSpeed() {
        return currentXSpeed;
    }
}
