package org.hinoob.twodimensionalgame.server.world;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.block.BlockType;
import org.hinoob.twodimensionalgame.server.block.Block;
import org.hinoob.twodimensionalgame.server.world.generator.GeneratorProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WorldSection {

    private final List<Block> blocks = new ArrayList<>();

    public WorldSection() {

    }


    public void generate(GeneratorProvider provider) {
        for(int x = 0; x < 600; x+=20) {
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
//        TwodimensionalGame.getInstance().getBlockManager().removeBlock(block);
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
//        TwodimensionalGame.getInstance().getBlockManager().addBlock(block);
    }

    public void writeTo(ModifiedBuf buf) {
        buf.writeInt(this.blocks.size());
        for(Block block : this.blocks) {
            buf.writeInt(block.type.ordinal());
            buf.writeInt(block.posX);
            buf.writeInt(block.posY);
        }
    }

    public void loadDataFrom(ModifiedBuf buf) {
        this.blocks.clear();
        int i = buf.readInt();
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
