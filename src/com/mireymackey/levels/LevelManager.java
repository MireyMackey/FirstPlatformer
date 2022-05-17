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
        BufferedImage img = LoadSave.getSpriteAtlases(LoadSave.LEVEL_ATLAS)[0];
        levelSprite = new BufferedImage[64];
        for (int atlasRow = 0; atlasRow < 8; atlasRow++)
            for (int atlasColumn = 0; atlasColumn < 8; atlasColumn++){
                levelSprite[atlasRow * 8 + atlasColumn] = img.getSubimage(atlasColumn*8, atlasRow*8, 8, 8);
            }
    }

    public void draw(Graphics g){
        g.drawImage(levelSprite[2], 0, 0, null);

    }
    public void update(){

    }
}
