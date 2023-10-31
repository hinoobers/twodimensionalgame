package org.hinoob.two2d.manager;

import org.hinoob.two2d.TwodimensionalGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
            TwodimensionalGame.getInstance().getPlayer().motionX = -10;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            TwodimensionalGame.getInstance().getPlayer().motionX = 10;
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            TwodimensionalGame.getInstance().getPlayer().jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
            TwodimensionalGame.getInstance().getPlayer().motionX = 0;
        }
    }
}
