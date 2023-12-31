package org.hinoob.twodimensionalgame.client.block.type;

import org.hinoob.twodimensionalgame.block.BlockType;
import org.hinoob.twodimensionalgame.client.block.Block;

import java.awt.*;

public class Grass extends Block {

    public Color color = Color.GREEN;

    public Grass() {
        this.type = BlockType.GRASS;
    }
    @Override
    public void onDraw(Graphics graphics) {
        graphics.setColor(color);
        super.onDraw(graphics);
        graphics.setColor(color.darker());
        graphics.drawRect(this.x, this.y, 20, 20);
    }
}
