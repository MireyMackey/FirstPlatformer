package com.mireymackey.utils;

import com.mireymackey.entities.Portal;
import com.mireymackey.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.*;

import static com.mireymackey.utils.Constants.EntityConstants.*;

public class LoadSave {
    public static final String PLAYER_ATLAS = "res\\player";
    public static final String LEVEL_ATLAS = "res\\leveTiles";
    public static final String LEVEL_ONE_DATA = "res\\level_one_data";

    public static BufferedImage[] getSpriteImageArray(String fileName){
        BufferedImage[] img;
        File file = new File(fileName);
        if (file.isFile()){
            return new BufferedImage[]{getSpriteImage(file.getPath())};
        }
        File[] fileList = file.listFiles();
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

    public static int[] getEntityCoords(int greenCode){
        BufferedImage levelImg = getSpriteImageArray(LEVEL_ONE_DATA)[0];
        for (int j = 0; j < levelImg.getHeight(); j++)
            for (int i = 0; i < levelImg.getWidth(); i++){
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getGreen();
                if (value == greenCode)
                    return new int[]{i * Game.getTilesSize(), j * Game.getTilesSize()};
             }
        return new int[]{0, 0};
    }

    public static BufferedImage[][] loadAnimation(int entityType) {
        BufferedImage[] img = LoadSave.getSpriteImageArray(getEntitySpritePath(entityType));
        BufferedImage[][] animations = new BufferedImage
                [getEntityAnimationsAmount(entityType)][getEntityFrameAmount(entityType)];
        for (int animationType = 0; animationType < animations.length; animationType++)
            for (int animationFrame = 0; animationFrame < animations[animationType].length; animationFrame++)
                try {
                    animations[animationType][animationFrame] = img[animationType].getSubimage(
                            animationFrame * getEntityDefaultWidth(entityType), 0,
                            getEntityDefaultWidth(entityType), getEntityDefaultHeight(entityType));
                } catch (RasterFormatException e){
                    break;
                }
        return animations;
    }
}
