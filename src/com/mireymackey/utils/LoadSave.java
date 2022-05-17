package com.mireymackey.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Matcher;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player";
    public static final String LEVEL_ATLAS = "leveTiles";

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
}
