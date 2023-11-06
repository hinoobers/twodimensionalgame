package org.hinoob.twodimensionalgame.server.world.generator;

import org.hinoob.twodimensionalgame.server.block.Block;
import org.hinoob.twodimensionalgame.server.block.type.Dirt;
import org.hinoob.twodimensionalgame.server.block.type.Grass;

public class DefaultGenerator implements GeneratorProvider{

    @Override
    public Block provide(int x, int y) {
        // 150-200 grass layer
        if(y > 200) return null;

        if(y >= 150 && y <= 200) {
            return new Grass();
        } else {
            return new Dirt();
        }
    }
}
