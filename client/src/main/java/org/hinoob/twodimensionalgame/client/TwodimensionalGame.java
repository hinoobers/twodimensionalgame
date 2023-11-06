package org.hinoob.twodimensionalgame.client;

import lombok.Getter;
import lombok.Setter;
import org.hinoob.twodimensionalgame.client.entity.Entity;
import org.hinoob.twodimensionalgame.client.entity.type.ClientPlayer;
import org.hinoob.twodimensionalgame.client.manager.BlockManager;
import org.hinoob.twodimensionalgame.client.manager.EntityManager;
import org.hinoob.twodimensionalgame.client.network.PacketHandler;
import org.hinoob.twodimensionalgame.client.swing.WindowFrame;
import org.hinoob.twodimensionalgame.client.swing.WindowPanel;
import org.hinoob.twodimensionalgame.client.swing.maingui.MainGuiFrame;
import org.hinoob.twodimensionalgame.client.world.World;
import org.hinoob.twodimensionalgame.client.network.NetworkHandler;

import javax.swing.*;
import java.util.logging.Logger;

public class TwodimensionalGame {

    @Getter private static TwodimensionalGame instance = new TwodimensionalGame();

    @Getter private static Logger logger = Logger.getLogger("Game");

    @Getter private EntityManager entityManager = new EntityManager();
    @Getter private BlockManager blockManager = new BlockManager();
    @Getter private World world;

    @Setter @Getter private ClientPlayer player;

    public static final int SCREEN_WIDTH = 600;

    @Getter private NetworkHandler client;
    @Getter private PacketHandler packetHandler = new PacketHandler();

    @Getter private WindowFrame window = new WindowFrame();
    @Getter private MainGuiFrame authGui = new MainGuiFrame();

    public void start(){
        this.client = new NetworkHandler();
        try {
            client.connect();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handleLoginFailed() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Failed to login");
        });
    }

    public void handleLoginSuccess(int entityId, String displayName) {
        SwingUtilities.invokeLater(() -> {

            WindowPanel p = new WindowPanel(window, entityId, displayName);
            p.setVisible(true);
            p.requestFocus();

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

    public void setWorld(World world) {
        this.world = world;
    }

    public static void main(String[] args) {
        instance.start();
    }
}
