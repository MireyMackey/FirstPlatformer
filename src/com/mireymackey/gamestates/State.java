package com.mireymackey.gamestates;

import com.mireymackey.main.Game;

public class State {

    protected Game game;

    public State(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}
