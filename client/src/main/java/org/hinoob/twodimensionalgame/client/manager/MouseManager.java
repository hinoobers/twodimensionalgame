package org.hinoob.twodimensionalgame.client.manager;

import org.hinoob.twodimensionalgame.client.entity.type.ClientPlayer;
import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.block.Block;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager extends MouseAdapter {

    private Point point = new Point(); // Initialize the point variable

    @Override
    public void mousePressed(MouseEvent e) {
        ClientPlayer player = TwodimensionalGame.getInstance().getPlayer();

        for(Block block : player.world.getBlocksFor(player.section)) {
            int width = block.getWidth();
            int height = block.getHeight();

            if(isMouseInsideBlock(point.x, point.y, block.getX(), block.getY(), width, height)) {
                player.world.removeBlock(block);
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        point = e.getPoint(); // Update the point variable with the current mouse position
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        point = e.getPoint(); // Update the point variable with the current mouse position
    }

    private boolean isMouseInsideBlock(int mouseX, int mouseY, int blockX, int blockY, int width, int height) {
        return mouseX >= blockX && mouseX <= blockX + width && mouseY >= blockY && mouseY <= blockY + height;
    }
}
