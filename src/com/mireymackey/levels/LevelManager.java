package com.mireymackey.levels;

import com.mireymackey.main.Game;
import com.mireymackey.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;

    public LevelManager(Game game){
        this.game = game;
        importOutsideArray();
    }

    private void importOutsideArray() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int atlasRow = 0; atlasRow < 4; atlasRow++)
            for (int atlasColumn = 0; atlasColumn < 12; atlasColumn++){
                int index = atlasRow * 12 + atlasColumn;
                levelSprite[index] = img.getSubimage(atlasColumn*32, atlasRow*32, 32, 32);
            }
    }

    public void draw(Graphics g){
        g.drawImage(levelSprite[2], 0, 0, null);

    }
    public void update(){

    }
}
