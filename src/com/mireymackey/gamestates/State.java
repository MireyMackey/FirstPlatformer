package com.mireymackey.gamestates;

import com.mireymackey.main.Game;
import com.mireymackey.ui.ButtonInMenu;

import java.awt.event.MouseEvent;

public class State {

    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public boolean isInButtonBounds(MouseEvent e, ButtonInMenu button){
        return button.getBounds().contains(e.getX(), e.getY());
    }
    public Game getGame(){
        return game;
    }
}
