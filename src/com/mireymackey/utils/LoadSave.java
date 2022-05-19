package com.mireymackey.utils;

import com.mireymackey.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player";
    public static final String LEVEL_ATLAS = "leveTiles";
    public static final String LEVEL_ONE_DATA = "level_one_data";

    public static BufferedImage[] getSpriteImageArray(String dirName){
        BufferedImage[] img;
        File[] fileList = new File("res\\" + dirName).listFiles();
        assert fileList != null;
        img = new BufferedImage[fileList.length];
        for (int i = 0; i < fileList.length; i++){
            img[i] = getSpriteImage(fileList[i].getPath());
        }
        return img;
    }

    public static BufferedImage getSpriteImage(String fileName){
        BufferedImage img = null;
        try {
             img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static int[][] getLevelData(){
        int[][] levelData = new int[Game.getTilesInHeight()][Game.getTilesInWidth()];
        BufferedImage img = getSpriteImageArray(LEVEL_ONE_DATA)[0];

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
