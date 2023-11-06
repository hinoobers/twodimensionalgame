package org.hinoob.twodimensionalgame.server.world.generator;

import org.hinoob.twodimensionalgame.server.block.Block;

public interface GeneratorProvider {

    Block provide(int x, int y);
}
