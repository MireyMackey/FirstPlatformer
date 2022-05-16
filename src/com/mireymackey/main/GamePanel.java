package com.mireymackey.main;

import com.mireymackey.inputs.KeyboardInputs;
import com.mireymackey.inputs.MouseInputs;

import static com.mireymackey.utils.Constants.Directions.*;
import static com.mireymackey.utils.Constants.PlayerCondition.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GamePanel extends JPanel {
    private float xCurrent = 100, yCurrent = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationFrameIndex, animationSpeed = 20;
    private int playerCondition = IDLE;
    private int playerDirection;
    private boolean moving = false;

    public GamePanel(){
        MouseInputs mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimation();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimation() {
        animations = new BufferedImage[9][6];
        for (int aniType = 0; aniType < animations.length; aniType++) {
            for (int aniFrame=0; aniFrame < animations[aniType].length; aniFrame++){
                animations[aniType][aniFrame] = img.getSubimage(aniFrame*64, aniType*40, 64, 40);
            }
        }
    }

    private void importImg() {
        try(InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png")) {
            img = ImageIO.read(Objects.requireNonNull(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setDirection(int direction){
        playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateAnimationTick();
        setAnimation();
        updatePosition();

        g.drawImage(animations[playerCondition][animationFrameIndex], (int)xCurrent, (int)yCurrent,128, 80, null);
    }

    private void setAnimation() {
        if (moving) playerCondition = RUNNING;
        else playerCondition = IDLE;
    }

    private void updatePosition(){
        if (moving){
            switch (playerDirection){
                case LEFT -> xCurrent -= 5;
                case UP -> yCurrent -= 5;
                case RIGHT -> xCurrent += 5;
                case DOWN -> yCurrent += 5;
            }
        }
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationFrameIndex++;
            if (animationFrameIndex  >= getFrameAmount(playerCondition)) animationFrameIndex = 0;
        }
    }
}
