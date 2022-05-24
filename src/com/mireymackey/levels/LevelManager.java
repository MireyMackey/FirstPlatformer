package com.mireymackey.levels;

import com.mireymackey.main.Game;
import com.mireymackey.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.LoadSave.getSpriteImageArray;
import static com.mireymackey.utils.Constants.LevelResources.*;


public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level level1;

    public LevelManager(Game game){
        this.game = game;
        importOutsideArray();
        level1 = new Level(LoadSave.getLevelData());
    }

    public Level getCurrentLevel() {
        return level1;
    }

    private void importOutsideArray() {
        BufferedImage img = getSpriteImageArray(LEVEL_TILES)[0];
        BufferedImage soul_img = getSpriteImageArray(SOUL_LEVEL_TILES)[0];
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
                int index = level1.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i * Game.getTilesSize(), j * Game.getTilesSize(),
                        Game.getTilesSize(), Game.getTilesSize(), null);
            }
    }
    public void update(){

    }
}
