package com.mireymackey.gamestates;

import com.mireymackey.main.Game;
import com.mireymackey.ui.AnimatedLogo;
import com.mireymackey.ui.ButtonInMenu;
import com.mireymackey.ui.Title;

import static com.mireymackey.utils.LoadSave.getSpriteImage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods{
    private final ButtonInMenu[] buttons = new ButtonInMenu[2];
    private final AnimatedLogo animatedLogo;
    private final Title title;
    public Menu(Game game) {
        super(game);

        buttons[0] = new ButtonInMenu(Game.getGameWidth() / 2, (int) (100 * Game.getScale()), 0, Gamestate.PLAYING);
        buttons[1] = new ButtonInMenu(Game.getGameWidth() / 2, (int) (148 * Game.getScale()), 1, Gamestate.QUIT);
        animatedLogo = new AnimatedLogo((int)(5 * Game.getScale()), (int)(10 * Game.getScale()));
        title = new Title((int)(80 * Game.getScale()), (int)(10 * Game.getScale()));
    }

    @Override
    public void update() {
        for(ButtonInMenu button : buttons){
            button.update();
        }
        animatedLogo.update();
    }

    @Override
    public void draw(Graphics g) {
        for(ButtonInMenu button : buttons){
            button.draw(g);
        }
        animatedLogo.draw(g);
        title.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (ButtonInMenu button : buttons){
            if(isInButtonBounds(e, button)) {
                button.applyGameState();
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(ButtonInMenu button : buttons)
            if(isInButtonBounds(e, button)) button.setMouseHover(true);
            else button.setMouseHover(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void resetButtons() {
        for (ButtonInMenu button : buttons)
            button.reset();
    }
}
