package com.mireymackey.utils;

import com.mireymackey.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player";
    public static final String LEVEL_ATLAS = "leveTiles";
    public static final String LEVEL_ONE_DATA = "level_one_data";

    public static BufferedImage[] getSpriteAtlases(String dirName){
        BufferedImage[] img;
        File[] fileList = new File("res\\" + dirName).listFiles();
        assert fileList != null;
        img = new BufferedImage[fileList.length];
        try {
            for (int i = 0; i < img.length; i++) {
                try (InputStream inputStream = LoadSave.class.getResourceAsStream("/"+dirName+'/'+fileList[i].getName())) {
                    img[i] = ImageIO.read(Objects.requireNonNull(inputStream));
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return img;
    }
    public static int[][] getLevelData(){
        int[][] levelData = new int[Game.getTilesInHeight()][Game.getTilesInWidth()];
        BufferedImage img = getSpriteAtlases(LEVEL_ONE_DATA)[0];

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++){
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 64)
                    //in case of wrong id we replace it with standard one (0)
                    value = 0;
                levelData[j][i] = value;
            }
        return levelData;
    }
}
