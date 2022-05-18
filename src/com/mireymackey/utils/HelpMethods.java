package com.mireymackey.utils;

import com.mireymackey.main.Game;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData){
        if (!isSolid(x, y, levelData))
            if(!isSolid(x + width, y + height, levelData))
                if(!isSolid(x + width, y, levelData))
                    if(!isSolid(x, y + height, levelData))
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
        if (value >= 64 || value < 0 || value != 11) return true;
        return false;
    }
}
