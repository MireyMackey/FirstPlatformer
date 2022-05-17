package com.mireymackey.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";

    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;
        try(InputStream inputStream = LoadSave.class.getResourceAsStream("/" + fileName)) {
            img = ImageIO.read(Objects.requireNonNull(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
