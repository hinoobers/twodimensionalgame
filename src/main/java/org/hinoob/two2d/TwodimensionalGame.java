package org.hinoob.two2d;

import lombok.Getter;
import lombok.Setter;
import org.hinoob.loom.ByteReader;
import org.hinoob.loom.LoomClient;
import org.hinoob.two2d.entity.Entity;
import org.hinoob.two2d.entity.EntityType;
import org.hinoob.two2d.entity.type.ClientPlayer;
import org.hinoob.two2d.entity.type.ServerPlayer;
import org.hinoob.two2d.manager.BlockManager;
import org.hinoob.two2d.manager.EntityManager;
import org.hinoob.two2d.packet.PacketReader;
import org.hinoob.two2d.packet.PacketWriter;
import org.hinoob.two2d.swing.WindowFrame;
import org.hinoob.two2d.swing.WindowPanel;
import org.hinoob.two2d.swing.maingui.MainGuiFrame;
import org.hinoob.two2d.world.World;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Logger;

public class TwodimensionalGame {

    @Getter private static TwodimensionalGame instance = new TwodimensionalGame();

    private Logger logger = Logger.getLogger("Game");

    @Getter private EntityManager entityManager = new EntityManager();
    @Getter private BlockManager blockManager = new BlockManager();
    @Getter private World world;

    @Setter @Getter private ClientPlayer player;

    public static final int SCREEN_WIDTH = 600;

    @Getter private LoomClient client;
    @Getter private PacketWriter packetWriter;
    @Getter private PacketReader packetReader;

    private WindowFrame window = new WindowFrame();
    private MainGuiFrame mainGuiFrame = new MainGuiFrame();

    public void start(){
        this.client = new LoomClient("localhost", 6969, 999999999);

        this.packetWriter = new PacketWriter(client);
        this.packetReader = new PacketReader(client);

        this.client.setCallback(bytes -> {
            ByteReader reader = new ByteReader(bytes);
            if (!reader.canRead()) return;

            packetReader.handlePacket(bytes);
        });

        try {
            client.connect();
            window.add(mainGuiFrame);
        } catch(IOException e){
            // Offline play
            logger.info("Initiating offline play");
            WindowPanel panel = new WindowPanel(window);
            window.add(panel);
        }
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void handleLoginFailed() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Failed to login");
        });
    }

    public void handleLoginSuccess(int entityId, String displayName) {
        SwingUtilities.invokeLater(() -> {
            window.remove(mainGuiFrame);

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
