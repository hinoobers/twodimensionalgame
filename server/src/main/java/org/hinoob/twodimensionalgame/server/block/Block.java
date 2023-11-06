package org.hinoob.twodimensionalgame.server.block;

import org.hinoob.twodimensionalgame.block.BlockType;
import org.hinoob.twodimensionalgame.server.block.type.Dirt;
import org.hinoob.twodimensionalgame.server.block.type.Grass;

public class Block {

    public int posX, posY;
    public BlockType type;

    public void move(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public static Block fromType(BlockType type) {
        switch (type) {
            case DIRT:
                return new Dirt();
            case GRASS:
                return new Grass();
        }

        return null;
    }
}
