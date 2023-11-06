package two2d.world.generator;

import two2d.block.Block;
import two2d.block.type.Dirt;
import two2d.block.type.Grass;

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
