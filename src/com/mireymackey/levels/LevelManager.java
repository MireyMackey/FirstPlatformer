package com.mireymackey.levels;

import com.mireymackey.main.Game;
import com.mireymackey.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.LoadSave.*;
import static com.mireymackey.utils.Constants.LevelResources.*;


public class LevelManager {

    private BufferedImage[] levelSprite;
    private Level level;

    public LevelManager(int currentLevelNum){
        importLevelSprite();
        loadNewLevel(currentLevelNum);
    }

    public void loadNewLevel(int currentLevelNum){
        level = new Level(LoadSave.getLevelData(currentLevelNum));
    }

    private void importLevelSprite() {
        BufferedImage img = getSpriteImage(LEVEL_TILES);
        BufferedImage soul_img = getSpriteImage(SOUL_LEVEL_TILES);
        levelSprite = new BufferedImage[128];
        for (int atlasRow = 0; atlasRow < 8; atlasRow++)
            for (int atlasColumn = 0; atlasColumn < 8; atlasColumn++){
                levelSprite[atlasRow * 8 + atlasColumn] = img.getSubimage(
                        atlasColumn*8, atlasRow*8, 8, 8);
                levelSprite[atlasRow * 8 + atlasColumn + 64] = soul_img.getSubimage(
                        atlasColumn*8, atlasRow*8, 8, 8);
            }
    }
    public void draw(Graphics g){
        for (int j = 0; j < Game.getTilesInHeight(); j++)
            for (int i = 0; i < Game.getTilesInWidth(); i++){
                int index = level.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i * Game.getTilesSize(), j * Game.getTilesSize(),
                        Game.getTilesSize(), Game.getTilesSize(), null);
            }
    }

    public Level getCurrentLevel() {
        return level;
    }
}
