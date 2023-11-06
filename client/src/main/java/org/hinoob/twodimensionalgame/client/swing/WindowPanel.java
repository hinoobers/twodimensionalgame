package org.hinoob.twodimensionalgame.client.swing;

import org.hinoob.twodimensionalgame.client.block.Block;
import org.hinoob.twodimensionalgame.client.entity.type.ClientPlayer;
import org.hinoob.twodimensionalgame.client.manager.KeyManager;
import org.hinoob.twodimensionalgame.client.manager.MouseManager;
import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.entity.Entity;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel{

    private final WindowFrame frame;

    public WindowPanel(WindowFrame frame) {
        this.frame = frame;
        setSize(new Dimension(TwodimensionalGame.SCREEN_WIDTH, 800));
        setPreferredSize(new Dimension(TwodimensionalGame.SCREEN_WIDTH, 800));

        MouseManager mouseManager = new MouseManager();
        addMouseListener(mouseManager);
        addMouseMotionListener(mouseManager);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        addKeyListener(new KeyManager());

        ClientPlayer player = new ClientPlayer();
        TwodimensionalGame.getInstance().setPlayer(TwodimensionalGame.getInstance().getEntityManager().addEntityWithRandomEntityID(player, TwodimensionalGame.getInstance().getWorld()));
    }

    public WindowPanel(WindowFrame frame, int entityId, String displayName) {
        this.frame = frame;
        setSize(new Dimension(TwodimensionalGame.SCREEN_WIDTH, 800));
        setPreferredSize(new Dimension(TwodimensionalGame.SCREEN_WIDTH, 800));


        MouseManager mouseManager = new MouseManager();
        addMouseListener(mouseManager);
        addMouseMotionListener(mouseManager);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        addKeyListener(new KeyManager());

        ClientPlayer player = new ClientPlayer();
        player.entityId = entityId;
        System.out.println("My entity id is " + player.entityId);
        player.displayName = displayName;
        TwodimensionalGame.getInstance().setPlayer(player);
        TwodimensionalGame.getInstance().getEntityManager().addEntity(player, TwodimensionalGame.getInstance().getWorld());

        //TwodimensionalGame.getInstance().getPacketWriter().sendPacket(new RequestPlayers());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,TwodimensionalGame.SCREEN_WIDTH,800);
        g.setColor(color);

        if(TwodimensionalGame.getInstance().getWorld() == null) {
            Toolkit.getDefaultToolkit().sync();
            try {
                // 15ms per tick
                Thread.sleep(15L);
                repaint();
            } catch(Exception ignored){

            }
            return;
        }

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
                TwodimensionalGame.getInstance().getPlayer().moveX(TwodimensionalGame.SCREEN_WIDTH - 5);
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
