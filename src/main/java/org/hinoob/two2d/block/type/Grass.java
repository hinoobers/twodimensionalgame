package org.hinoob.two2d.block.type;

import org.hinoob.two2d.block.Block;

import java.awt.*;

public class Grass extends Block {

    public Color color = Color.GREEN;
    @Override
    public void onDraw(Graphics graphics) {
        graphics.setColor(color);
        super.onDraw(graphics);
        graphics.setColor(color.darker());
        graphics.drawRect(this.x, this.y, 20, 20);
    }
}
