package com.mireymackey.main;

import com.mireymackey.inputs.KeyboardInputs;
import com.mireymackey.inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Game game){
        MouseInputs mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }
    public void updateGame(){

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame(){
        return game;
    }
}
