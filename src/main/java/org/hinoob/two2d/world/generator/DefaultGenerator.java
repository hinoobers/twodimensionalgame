package org.hinoob.two2d.world.generator;

import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.block.type.Dirt;
import org.hinoob.two2d.block.type.Grass;

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
