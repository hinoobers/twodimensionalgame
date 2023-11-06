package org.hinoob.twodimensionalgame.client.block;

import org.hinoob.twodimensionalgame.block.BlockType;
import org.hinoob.twodimensionalgame.client.XYBoundingBox;
import org.hinoob.twodimensionalgame.client.block.type.Dirt;
import org.hinoob.twodimensionalgame.client.block.type.Grass;
import org.hinoob.twodimensionalgame.client.entity.Entity;

import java.awt.*;

// default blocks are 20x20
public class Block {

    public int x, y;
    protected int width = 20, height = 20;
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

    public static Block fromType(BlockType type) {
        switch (type) {
            case DIRT:
                return new Dirt();
            case GRASS:
                return new Grass();
        }

        return null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
