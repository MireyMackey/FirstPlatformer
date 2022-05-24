package com.mireymackey.utils;

import com.mireymackey.main.Game;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class HelpMethods {

    public static BufferedImage flipImage(BufferedImage img){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);
        return img;
    }

//    public static void gameDrawImage(java.awt.Image img, int x, int y, int width, int height, Graphics g){
//        g.drawImage(img, x + (GamePanel.getPanelWidth() - Game.getGameWidth()) / 2,
//                y + (GamePanel.getPanelHeight() - Game.getGameHeight()) / 2, width, height, null);
//    }
}
