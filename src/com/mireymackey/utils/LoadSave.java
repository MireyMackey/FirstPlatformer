package com.mireymackey.utils;

import com.mireymackey.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.*;
import java.util.ArrayList;

import static com.mireymackey.utils.Constants.EntityConstants.*;

public class LoadSave {
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
                Color color = new Color(img.getRGB(i, j), true);
                int value = color.getRed();
                if (value < 0 || value > 63 || color.getAlpha() == 0.0)
                    //in case of wrong id or transparent pixel we replace it with standard one (63)
                    value = 63;
                else if (color.getAlpha() == 100){
                    System.out.println(j + ' ' + i);
                    value += 64;
                }
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
        return new int[]{-1, -1};
    }

    public static int[][] getEntityCoordsArray(int greenCode){
        BufferedImage levelImg = getSpriteImageArray(LEVEL_ONE_DATA)[0];
        ArrayList<int []> coords = new ArrayList<>();
        for (int j = 0; j < levelImg.getHeight(); j++)
            for (int i = 0; i < levelImg.getWidth(); i++){
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getGreen();
                if (value == greenCode)
                    coords.add(new int[] {i * Game.getTilesSize(), j * Game.getTilesSize()});
            }
        return coords.toArray(new int[coords.size()][2]);
    }

    public static BufferedImage[][] loadAnimation(int entityType) {
        BufferedImage[] img = LoadSave.getSpriteImageArray(getEntitySpritePath(entityType));
        BufferedImage[][] animations = new BufferedImage[img.length][];
        ArrayList<BufferedImage> currentAnimation = new ArrayList<>();
        for (int animationType = 0; animationType < img.length; animationType++) {
            for (int animationFrame = 0; animationFrame < (img[animationType].getWidth() / getEntityDefaultWidth(entityType)); animationFrame++)
                try {
                    currentAnimation.add(img[animationType].getSubimage(
                            animationFrame * getEntityDefaultWidth(entityType), 0,
                            getEntityDefaultWidth(entityType), getEntityDefaultHeight(entityType)));
                } catch (RasterFormatException e){
                    break;
                }
            animations[animationType] = currentAnimation.toArray(new BufferedImage[0]);
            currentAnimation.clear();
        }
        return animations;
    }

    public static void registerFont(String fontPath, float fontSize){
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
