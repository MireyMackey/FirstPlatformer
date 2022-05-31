package com.mireymackey.inputs;

import com.mireymackey.gamestates.Gamestate;
import com.mireymackey.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.gamestate){
            case PLAYING -> gamePanel.getGame().getPlaying().keyPressed(e);
            case MENU -> gamePanel.getGame().getMenu().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.gamestate){
            case PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);
            case MENU -> gamePanel.getGame().getMenu().keyReleased(e);
        }
    }
}
