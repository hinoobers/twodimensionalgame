package org.hinoob.two2d;

import lombok.Getter;
import lombok.Setter;
import org.hinoob.two2d.entity.Entity;
import org.hinoob.two2d.entity.type.ClientPlayer;
import org.hinoob.two2d.manager.BlockManager;
import org.hinoob.two2d.manager.EntityManager;
import org.hinoob.two2d.manager.KeyManager;
import org.hinoob.two2d.swing.WindowFrame;
import org.hinoob.two2d.swing.WindowPanel;

import java.util.logging.Logger;

public class TwodimensionalGame {

    @Getter private static TwodimensionalGame instance = new TwodimensionalGame();

    private Logger logger = Logger.getLogger("Game");
    private KeyManager keyManager = new KeyManager();
    @Getter private EntityManager entityManager = new EntityManager();
    @Getter private BlockManager blockManager = new BlockManager();

    @Setter @Getter private ClientPlayer player;

    public void start(){
        WindowFrame window = new WindowFrame();
        WindowPanel panel = new WindowPanel();
        window.add(panel);

        window.addKeyListener(keyManager);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void setPlayer(Entity entity) {
        this.player = (ClientPlayer) entity;
    }

    public static void main(String[] args) {
        instance.start();
    }
}
