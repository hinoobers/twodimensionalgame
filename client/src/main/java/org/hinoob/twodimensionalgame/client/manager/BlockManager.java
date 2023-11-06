package org.hinoob.twodimensionalgame.client.manager;

import org.hinoob.twodimensionalgame.client.block.Block;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlockManager {

    private final CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<>();

    public Block addBlock(Block entity) {
        blocks.add(entity);
        return entity;
    }

    public void removeBlock(Block entity) {
        blocks.remove(entity);
    }

    public Collection<Block> getBlocks() {
        return Collections.unmodifiableCollection(this.blocks);
    }
}
