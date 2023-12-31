package org.hinoob.twodimensionalgame.client.world;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.block.BlockType;
import org.hinoob.twodimensionalgame.client.block.Block;
import org.hinoob.twodimensionalgame.client.world.generator.GeneratorProvider;
import org.hinoob.twodimensionalgame.client.TwodimensionalGame;

import java.util.*;
import java.util.List;

public class WorldSection {

    private final List<Block> blocks = new ArrayList<>();

    public WorldSection() {

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
        TwodimensionalGame.getInstance().blockManager.removeBlock(block);
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
        TwodimensionalGame.getInstance().blockManager.addBlock(block);
    }

    public void loadDataFrom(ModifiedBuf buf) {
        this.blocks.clear();
        int i = buf.readInt();
        System.out.println("Blocks=" + i);
        for(int z = 0; z < i; z++) {
            BlockType type = BlockType.values()[buf.readInt()];
            Block block = Block.fromType(type);
            block.move(buf.readInt(), buf.readInt());
            addBlock(block);
        }
    }

    public Collection<Block> getBlocks() {
        return Collections.unmodifiableCollection(this.blocks);
    }
}
