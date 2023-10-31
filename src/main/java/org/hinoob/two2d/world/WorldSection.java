package org.hinoob.two2d.world;

import lombok.Getter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.block.type.Dirt;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldSection {

    @Getter private final List<Block> blocks = new ArrayList<>();

    public WorldSection() {
        generateDefault();
    }

    public void generateDefault() {
        int color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)).getRGB();
        for(int x = 0; x < 600; x+=20) {
            for(int y = 0; y < 200; y+=20) {
                Dirt block = new Dirt();
                block.move(x, 800-y);
                block.color = new Color(color);
                TwodimensionalGame.getInstance().getBlockManager().addBlock(block);

                this.blocks.add(block);
            }
        }
    }
}
