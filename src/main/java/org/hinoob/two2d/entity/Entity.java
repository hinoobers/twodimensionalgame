package org.hinoob.two2d.entity;

import lombok.Getter;
import org.hinoob.two2d.XYBoundingBox;
import org.hinoob.two2d.block.Block;

import java.awt.*;

public class Entity {

    public int entityId;
    public XYBoundingBox boundingBox;
    protected int posX = 15, posY = 15;
    public int motionX, motionY;
    public int width = 3, height = 3;
    public boolean hasGravity = true, onGround;
    public int section = 0;

    public void onDraw(Graphics graphics) {
        // Not implemented
    }

    public void moveX(int x) {
        this.posX = x;
    }

    public void onTick() {
        if(hasGravity && !onGround) {
            this.motionY = -1;
        }

        this.posX += motionX;
        this.posY += (-motionY); // reverse for the sake of it
        // Initial position is set, let's do the bb
        this.boundingBox = new XYBoundingBox(posX - (width / 2), posY, posX + (width / 2), posY + height);
        this.onGround = motionY == 0;
    }

    public void endOfTick() {
        motionY = Math.max(0, motionY - 1);
        motionX = Math.max(0, motionX - 1);
    }

    public void onCollide(Block block) {
        if(hasGravity && motionY < 0){
            motionY = 0;
            onGround = true;
        }
    }

    public int getPosX() {
        if(section == 0)
            return posX;
        return (section * 600) + posX;
    }

    public int getPosY() {
        if(section == 0)
            return posY;
        return (section * 600) + posY;
    }

    public int getRawX() {
        return posX;
    }

    public int getRawY() {
        return posY;
    }
}
