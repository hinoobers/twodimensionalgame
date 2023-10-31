package org.hinoob.two2d.world.generator;

import org.hinoob.two2d.block.Block;

public interface GeneratorProvider {

    Block provide(int x, int y);
}
