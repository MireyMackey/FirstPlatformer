package com.mireymackey.main;

import com.mireymackey.inputs.KeyboardInputs;
import com.mireymackey.inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    private Game game;
    private static int panelWidth;
    private static int panelHeight;

    public GamePanel(Game game){
        MouseInputs mouseInputs = new MouseInputs(this);
        this.game = game;
        panelWidth = getWidth();
        panelHeight = getHeight();
//        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public void setPanelSize(){
        Dimension size = new Dimension(1920,1080);
        setPreferredSize(size);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame(){
        return game;
    }

    public static int getPanelWidth() {
        return panelWidth;
    }
    public static int getPanelHeight() {
        return panelHeight;
    }
}
