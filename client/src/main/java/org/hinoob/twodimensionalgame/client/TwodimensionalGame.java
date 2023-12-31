package org.hinoob.twodimensionalgame.client;

import org.hinoob.twodimensionalgame.client.entity.Entity;
import org.hinoob.twodimensionalgame.client.entity.type.ClientPlayer;
import org.hinoob.twodimensionalgame.client.manager.BlockManager;
import org.hinoob.twodimensionalgame.client.manager.EntityManager;
import org.hinoob.twodimensionalgame.client.manager.WorldManager;
import org.hinoob.twodimensionalgame.client.network.PacketHandler;
import org.hinoob.twodimensionalgame.client.swing.WindowFrame;
import org.hinoob.twodimensionalgame.client.swing.WindowPanel;
import org.hinoob.twodimensionalgame.client.swing.maingui.MainGuiFrame;
import org.hinoob.twodimensionalgame.client.world.World;
import org.hinoob.twodimensionalgame.client.network.NetworkHandler;
import org.hinoob.twodimensionalgame.server.Server;

import javax.swing.*;
import java.util.Arrays;
import java.util.logging.Logger;

public class TwodimensionalGame {

    private static TwodimensionalGame instance = new TwodimensionalGame();

    private static Logger logger = Logger.getLogger("Game");

    public final EntityManager entityManager = new EntityManager();
    public final BlockManager blockManager = new BlockManager();
    public final WorldManager worldManager = new WorldManager();

    private ClientPlayer player;

    public static final int SCREEN_WIDTH = 600;

    private NetworkHandler client;
    public final PacketHandler packetHandler = new PacketHandler();

    public final WindowFrame window = new WindowFrame();
    public final MainGuiFrame authGui = new MainGuiFrame();

    public void start(){
        this.client = new NetworkHandler();
        try {
            SwingUtilities.invokeLater(() -> {
                window.add(authGui);
                authGui.requestFocus();

                window.setResizable(false);
                window.revalidate();
                window.repaint();
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            });
            client.connect();
        } catch(Exception e){
            logger.info("Failed");
        }
    }

    public void handleLoginFailed() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Failed to login");
        });
    }

    public void handleLoginSuccess(int entityId, String displayName) {
        SwingUtilities.invokeLater(() -> {
            window.remove(authGui); // Remove all components from the window's content pane
            window.revalidate();
            window.repaint();

            // Create a new WindowPanel
            WindowPanel p = new WindowPanel(window, entityId, displayName);
            p.setVisible(true);

            // Add the new WindowPanel to the window's content pane
            window.add(p);

            window.revalidate();
            window.repaint();
            window.pack();
            p.requestFocus();
        });
    }

    public void setPlayer(Entity entity) {
        this.player = (ClientPlayer) entity;
    }

    public ClientPlayer getPlayer() {
        return player;
    }

    public NetworkHandler getClient() {
        return client;
    }

    public static TwodimensionalGame getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return logger;
    }


    public static void main(String[] args) {
        instance.start();
    }
}
