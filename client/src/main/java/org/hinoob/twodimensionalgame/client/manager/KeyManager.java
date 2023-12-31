package org.hinoob.twodimensionalgame.client.manager;

import org.hinoob.twodimensionalgame.client.TwodimensionalGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

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
        if(e.getKeyCode() == KeyEvent.VK_A) {
            TwodimensionalGame.getInstance().getPlayer().motionX = 0;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            TwodimensionalGame.getInstance().getPlayer().motionX = 0;
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            TwodimensionalGame.getInstance().getPlayer().jump();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
