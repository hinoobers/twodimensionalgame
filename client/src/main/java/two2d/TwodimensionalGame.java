package two2d;

import lombok.Getter;
import lombok.Setter;
import two2d.entity.Entity;
import two2d.entity.type.ClientPlayer;
import two2d.manager.BlockManager;
import two2d.manager.EntityManager;
import two2d.network.NetworkHandler;
import two2d.swing.WindowFrame;
import two2d.swing.WindowPanel;
import two2d.swing.maingui.MainGuiFrame;
import two2d.world.World;

import javax.swing.*;
import java.util.logging.Logger;

public class TwodimensionalGame {

    @Getter private static TwodimensionalGame instance = new TwodimensionalGame();

    private Logger logger = Logger.getLogger("Game");

    @Getter private EntityManager entityManager = new EntityManager();
    @Getter private BlockManager blockManager = new BlockManager();
    @Getter private World world;

    @Setter @Getter private ClientPlayer player;

    public static final int SCREEN_WIDTH = 600;

    @Getter private NetworkHandler client;

    private WindowFrame window = new WindowFrame();
    private MainGuiFrame mainGuiFrame = new MainGuiFrame();

    public void start(){
        this.client = new NetworkHandler();
        try {
            client.connect();
            window.add(mainGuiFrame);
        } catch(Exception e){
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
