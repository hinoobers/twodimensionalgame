package org.hinoob.twodimensionalgame.client.world.generator;

import org.hinoob.twodimensionalgame.client.block.Block;

public interface GeneratorProvider {

    Block provide(int x, int y);
}
