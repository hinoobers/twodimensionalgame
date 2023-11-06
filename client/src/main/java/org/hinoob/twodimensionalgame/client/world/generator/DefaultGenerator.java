package org.hinoob.twodimensionalgame.client.world.generator;

import org.hinoob.twodimensionalgame.client.block.Block;
import org.hinoob.twodimensionalgame.client.block.type.Dirt;
import org.hinoob.twodimensionalgame.client.block.type.Grass;

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
