package com.mireymackey.utils;

import com.mireymackey.main.Game;
import com.mireymackey.main.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData){
        if (!isSolid(x, y, levelData) //top left
                && !isSolid(x + width, y + height, levelData) //bottom right
                && !isSolid(x + width, y, levelData) //top right
                && !isSolid(x, y + height, levelData) //bottom left
                && !isSolid(x, y + height / 2, levelData) //left center
                && !isSolid(x + width, y + height / 2, levelData) //right center
                && !isSolid(x + width / 2, y, levelData) //top center
                && !isSolid(x + width / 2, y + height, levelData)) //bottom center
            return true;
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] levelData){
        if (x < 0 || x >= Game.getGameWidth()){
            return true;
        }
        if (y < 0 || y >= Game.getGameHeight()){
            return true;
        }

        float xIndex = x / Game.getTilesSize();
        float yIndex = y / Game.getTilesSize();

        int value = levelData[(int) yIndex][(int) xIndex];
        if (value != 63) return true;
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
        int currentTile = (int) ((hitbox.y + hitbox.height) / Game.getTilesSize());
        if (airSpeed > 0){
            //falling, so touching floor
            int tilePos = currentTile * Game.getTilesSize();
            int yOffset = (int) (Game.getTilesSize() - hitbox.height);
            return tilePos + yOffset - 1;
        } else {
            //jumping, so touching the roof
            return currentTile * Game.getTilesSize();
        }
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
        // Check if the pixels below bottom left and bottom right is solid
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData))
                return false;

        return true;
    }

    public static BufferedImage flipImage(BufferedImage img){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);
        return img;
    }

//    public static void gameDrawImage(java.awt.Image img, int x, int y, int width, int height, Graphics g){
//        g.drawImage(img, x + (GamePanel.getPanelWidth() - Game.getGameWidth()) / 2,
//                y + (GamePanel.getPanelHeight() - Game.getGameHeight()) / 2, width, height, null);
//    }
}
