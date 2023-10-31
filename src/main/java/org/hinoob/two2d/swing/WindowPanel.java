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


        ClientPlayer player = new ClientPlayer();
        TwodimensionalGame.getInstance().setPlayer(TwodimensionalGame.getInstance().getEntityManager().addEntity(player));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,600,800);
        g.setColor(color);

        int preSection = TwodimensionalGame.getInstance().getWorld().calculateSectionFor(TwodimensionalGame.getInstance().getPlayer());
        for(Entity entity : TwodimensionalGame.getInstance().getEntityManager().getEntities()) {
            entity.onTick();
        }
        int postSection = TwodimensionalGame.getInstance().getWorld().calculateSectionFor(TwodimensionalGame.getInstance().getPlayer());

        if(preSection != postSection){
            // they switched
            System.out.println("Switched, newSection = " + postSection);
            int x = TwodimensionalGame.getInstance().getPlayer().getRawX();
            TwodimensionalGame.getInstance().getPlayer().section = postSection;
            if(x > 0) {
                TwodimensionalGame.getInstance().getPlayer().moveX(5);
            } else {
                TwodimensionalGame.getInstance().getPlayer().moveX(600 - 5);
            }
        }
        for(Block block : TwodimensionalGame.getInstance().getWorld().getBlocksFor(postSection)) {
            block.onDraw(g);
        }

        for(Block block : TwodimensionalGame.getInstance().getBlockManager().getBlocks()) {
            for(Entity entity : TwodimensionalGame.getInstance().getEntityManager().getEntities()) {
                if(block.boundingBox != null && entity.boundingBox != null && block.boundingBox.intersects(entity.boundingBox)) {
                    entity.onCollide(block);
                    block.onCollide(entity);
                }
            }
        }

        // Draw everything here
        for(Entity entity : TwodimensionalGame.getInstance().getEntityManager().getEntities()) {
            entity.onDraw(g);
        }

        for(Entity entity : TwodimensionalGame.getInstance().getEntityManager().getEntities()) {
            entity.endOfTick();
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
