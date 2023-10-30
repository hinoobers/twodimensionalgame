package org.hinoob.two2d.entity;

import lombok.Getter;
import org.hinoob.two2d.block.Block;

import java.awt.*;

public class Entity {

    public int entityId;
    public int posX = 15, posY = 15;

    public void onDraw(Graphics graphics) {
        // Not implemented
    }

    public void onTick() {

    }

    public void onCollide(Block block) {

    }
}
