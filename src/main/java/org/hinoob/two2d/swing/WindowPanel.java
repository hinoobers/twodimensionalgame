package org.hinoob.two2d.swing;

import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.block.type.Dirt;
import org.hinoob.two2d.entity.Entity;
import org.hinoob.two2d.entity.type.ClientPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowPanel extends JPanel {

    public WindowPanel() {
        setPreferredSize(new Dimension(600, 800));


        // Initialize start stuff
        for(int x = 0; x < 600; x+=20) {
            for(int y = 0; y < 200; y+=20) {
                Block block = new Dirt();
                block.x = x;
                block.y = 800 - y;
                TwodimensionalGame.getInstance().getBlockManager().addBlock(block);
            }
        }
        TwodimensionalGame.getInstance().setPlayer(TwodimensionalGame.getInstance().getEntityManager().addEntity(new ClientPlayer()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,600,800);
        g.setColor(color);

        for(Entity entity : TwodimensionalGame.getInstance().getEntityManager().getEntities()) {
            entity.onTick();
        }

        for(Block block : TwodimensionalGame.getInstance().getBlockManager().getBlocks()) {
            block.onDraw(g);
        }

        for(Block block : TwodimensionalGame.getInstance().getBlockManager().getBlocks()) {
            for(Entity entity : TwodimensionalGame.getInstance().getEntityManager().getEntities()) {
               if(block.collides(entity)) {
                   entity.onCollide(block);
                   block.onCollide(entity);
               }
            }
        }

        // Draw everything here
        for(Entity entity : TwodimensionalGame.getInstance().getEntityManager().getEntities()) {
            entity.onDraw(g);
        }
        Toolkit.getDefaultToolkit().sync();
        try {
            // 15ms per tick
            Thread.sleep(15L);
            repaint();
        } catch(Exception ignored){

        }
    }

}
