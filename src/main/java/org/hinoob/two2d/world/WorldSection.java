package org.hinoob.two2d.world;

import lombok.Getter;
import org.hinoob.loom.ByteReader;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.block.BlockType;
import org.hinoob.two2d.block.type.Dirt;
import org.hinoob.two2d.block.type.Grass;
import org.hinoob.two2d.world.generator.DefaultGenerator;
import org.hinoob.two2d.world.generator.GeneratorProvider;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WorldSection {

    private final List<Block> blocks = new ArrayList<>();

    public WorldSection() {

    }

    public WorldSection(ByteReader reader) {
        this.update(reader);
    }

    public void generate(GeneratorProvider provider) {
        for(int x = 0; x < TwodimensionalGame.SCREEN_WIDTH; x+=20) {
            for(int y = 0; y < 800; y+=20) {
                Block block = provider.provide(x, y);
                if(block == null) continue;
                block.move(x, 800-y);
                addBlock(block);
            }
        }
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
        TwodimensionalGame.getInstance().getBlockManager().removeBlock(block);
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
        TwodimensionalGame.getInstance().getBlockManager().addBlock(block);
    }

    public void update(ByteReader reader) {
        this.blocks.clear();

        int blockCount = reader.readInt();
        System.out.println("block count: " + blockCount);
        for(int i = 0; i < blockCount; i++) {
            int x = reader.readInt();
            int y = reader.readInt();
            BlockType type = BlockType.values()[reader.readInt()];

            Block block;
            if(type == BlockType.DIRT) {
                block = new Dirt();
            } else if(type == BlockType.GRASS) {
                block = new Grass();
            } else {
                continue;
            }

            block.move(x, y);
            addBlock(block);
        }
    }

    public Collection<Block> getBlocks() {
        return Collections.unmodifiableCollection(this.blocks);
    }
}
