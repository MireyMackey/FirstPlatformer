package com.mireymackey.gamestates;

import com.mireymackey.entities.EntityManager;
import com.mireymackey.levels.LevelManager;
import com.mireymackey.main.Game;

import static com.mireymackey.utils.LoadSave.registerFont;
import static com.mireymackey.utils.Constants.FontConstants.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Playing extends State implements StateMethods{

    private LevelManager levelManager;
    private EntityManager entityManager;

    private Font pixelFont;
    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initFont(){
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH)).deriveFont(FONT_SIZE);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        entityManager = new EntityManager(this);
        initFont();
    }

    @Override
    public void update() {
        levelManager.update();
        entityManager.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        entityManager.draw(g);
        drawFlameCounter(g);
    }

    public void resetAll(){
        entityManager.resetEntities();
    }

    private void drawFlameCounter(Graphics g){
        g.setFont(pixelFont);
        g.drawString(entityManager.getCollectedFlamesCounter() + "/" + entityManager.getFlamesAmount(),
                (int)(12 * Game.getScale()), (int)(32 * Game.getScale()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                entityManager.getPlayer().setLeft(true);
                entityManager.getPlayerSoul().setRight(true);
            }
//            case KeyEvent.VK_DOWN -> {
//                entityManager.getPlayer().setDown(true);
//                entityManager.getPlayerSoul().setDown(true);
//            }
            case KeyEvent.VK_RIGHT -> {
                entityManager.getPlayer().setRight(true);
                entityManager.getPlayerSoul().setLeft(true);
            }
            case KeyEvent.VK_UP -> {
                entityManager.getPlayer().setJump(true);
                entityManager.getPlayerSoul().setJump(true);
            }
            case KeyEvent.VK_SPACE -> {
                entityManager.getPlayer().setJump(true);
                entityManager.getPlayerSoul().setJump(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT -> {
                entityManager.getPlayer().setLeft(false);
                entityManager.getPlayerSoul().setRight(false);
            }
//            case KeyEvent.VK_DOWN -> {
//                entityManager.getPlayer().setDown(false);
//                entityManager.getPlayerSoul().setDown(false);
//            }
            case KeyEvent.VK_RIGHT -> {
                entityManager.getPlayer().setRight(false);
                entityManager.getPlayerSoul().setLeft(false);
            }
            case KeyEvent.VK_UP -> {
                entityManager.getPlayer().setJump(false);
                entityManager.getPlayerSoul().setJump(false);
            }
            case KeyEvent.VK_SPACE -> {
                entityManager.getPlayer().setJump(false);
                entityManager.getPlayerSoul().setJump(false);
            }
        }
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
