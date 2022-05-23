package com.mireymackey.gamestates;

import com.mireymackey.entities.EntityManager;
import com.mireymackey.levels.LevelManager;
import com.mireymackey.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods{

    private LevelManager levelManager;

    private EntityManager entityManager;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        entityManager = new EntityManager(this);
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
    }

    public void resetAll(){
        entityManager.resetEntities();
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
            case KeyEvent.VK_A -> entityManager.getPlayer().setLeft(true);
            case KeyEvent.VK_LEFT -> entityManager.getPlayer().setLeft(true);

            case KeyEvent.VK_S -> entityManager.getPlayer().setDown(true);
            case KeyEvent.VK_DOWN -> entityManager.getPlayer().setDown(true);

            case KeyEvent.VK_D -> entityManager.getPlayer().setRight(true);
            case KeyEvent.VK_RIGHT -> entityManager.getPlayer().setRight(true);

            case KeyEvent.VK_W -> entityManager.getPlayer().setJump(true);
            case KeyEvent.VK_UP -> entityManager.getPlayer().setJump(true);
            case KeyEvent.VK_SPACE -> entityManager.getPlayer().setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A -> entityManager.getPlayer().setLeft(false);
            case KeyEvent.VK_LEFT -> entityManager.getPlayer().setLeft(false);

            case KeyEvent.VK_S -> entityManager.getPlayer().setDown(false);
            case KeyEvent.VK_DOWN -> entityManager.getPlayer().setDown(false);

            case KeyEvent.VK_D -> entityManager.getPlayer().setRight(false);
            case KeyEvent.VK_RIGHT -> entityManager.getPlayer().setRight(false);

            case KeyEvent.VK_W -> entityManager.getPlayer().setJump(false);
            case KeyEvent.VK_UP -> entityManager.getPlayer().setJump(false);
            case KeyEvent.VK_SPACE -> entityManager.getPlayer().setJump(false);
        }
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
