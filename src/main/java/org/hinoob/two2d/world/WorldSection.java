package org.hinoob.two2d.world;

import lombok.Getter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.block.type.Dirt;
import org.hinoob.two2d.world.generator.DefaultGenerator;
import org.hinoob.two2d.world.generator.GeneratorProvider;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WorldSection {

    private final List<Block> blocks = new ArrayList<>();

    public WorldSection() {
        generate(new DefaultGenerator());
    }

    public void generate(GeneratorProvider provider) {
        for(int x = 0; x < TwodimensionalGame.SCREEN_WIDTH; x+=20) {
            for(int y = 0; y < 800; y+=20) {
                Block block = provider.provide(x, y);
                if(block == null) continue;
                block.move(x, 800-y);
                TwodimensionalGame.getInstance().getBlockManager().addBlock(block);

                this.blocks.add(block);
            }
        }
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
        TwodimensionalGame.getInstance().getBlockManager().removeBlock(block);
    }

    public Collection<Block> getBlocks() {
        return Collections.unmodifiableCollection(this.blocks);
    }
}
