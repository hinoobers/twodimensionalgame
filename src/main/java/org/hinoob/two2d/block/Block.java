package org.hinoob.two2d.block;

import org.hinoob.two2d.entity.Entity;

import java.awt.*;

// default blocks are 20x20
public class Block {

    public int x, y;

    public void onDraw(Graphics graphics) {
        graphics.fillRect(x, y, 20, 20);
    }

    public void onCollide(Entity entity){

    }

    public boolean collides(Entity entity) {
        return false;
    }
}
