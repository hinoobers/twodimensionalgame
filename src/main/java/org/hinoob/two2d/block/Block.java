package org.hinoob.two2d.block;

import lombok.Getter;
import org.hinoob.two2d.XYBoundingBox;
import org.hinoob.two2d.entity.Entity;

import java.awt.*;

// default blocks are 20x20
public class Block {

    @Getter
    protected int x, y;
    @Getter protected int width = 20, height = 20;
    public BlockType type;
    public XYBoundingBox boundingBox;

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        this.boundingBox = new XYBoundingBox(x - (width/2), y, x + (width/2), y + height);
    }

    public void onDraw(Graphics graphics) {
        graphics.fillRect(x, y, 20, 20);
    }

    public void onCollide(Entity entity){

    }
}
