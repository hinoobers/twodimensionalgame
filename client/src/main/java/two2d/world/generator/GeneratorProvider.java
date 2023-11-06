package two2d.world.generator;

import two2d.block.Block;

public interface GeneratorProvider {

    Block provide(int x, int y);
}
