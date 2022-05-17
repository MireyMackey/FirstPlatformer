package com.mireymackey.levels;

import com.mireymackey.main.Game;
import com.mireymackey.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mireymackey.utils.LoadSave.getSpriteAtlases;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level level1;

    public LevelManager(Game game){
        this.game = game;
        importOutsideArray();
        level1 = new Level(LoadSave.getLevelData());
    }

    private void importOutsideArray() {
        BufferedImage img = getSpriteAtlases(LoadSave.LEVEL_ATLAS)[0];
        levelSprite = new BufferedImage[64];
        for (int atlasRow = 0; atlasRow < 8; atlasRow++)
            for (int atlasColumn = 0; atlasColumn < 8; atlasColumn++){
                levelSprite[atlasRow * 8 + atlasColumn] = img.getSubimage(atlasColumn*8, atlasRow*8, 8, 8);
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
