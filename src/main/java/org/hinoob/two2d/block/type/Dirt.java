package org.hinoob.two2d.block.type;

import org.hinoob.two2d.block.Block;

import java.awt.*;

public class Dirt extends Block {

    @Override
    public void onDraw(Graphics graphics) {
        graphics.setColor(new Color(150, 75, 0));
        super.onDraw(graphics);
        graphics.setColor(new Color(150, 75, 0).darker());
        graphics.drawRect(this.x, this.y, 20, 20);
    }
}
