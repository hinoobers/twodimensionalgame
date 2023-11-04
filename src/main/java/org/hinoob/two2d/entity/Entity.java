package org.hinoob.two2d.entity;

import lombok.Getter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.XYBoundingBox;
import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.world.World;

import java.awt.*;

public class Entity {

    public int entityId;
    public World world;
    public XYBoundingBox boundingBox;
    @Getter
    protected int posX = 15, posY = 15;
    public int motionX, motionY;
    public int width = 3, height = 3;
    public boolean hasGravity = true, onGround;
    public int section = 0;
    public int tickSpeed = 15;

    public Entity() {
        this.boundingBox = new XYBoundingBox(0, 0, 0, 0);
    }

    public void onDraw(Graphics graphics) {
        // Not implemented
    }

    public void moveX(int x) {
        this.posX = x;
    }

    public void moveY(int y) {
        this.posY = y;
    }

    public void onTick() {
        if(hasGravity && !onGround) {
            this.motionY -= 1;
        }

        doCollisions();
        updatePosition();
        // Initial position is set, let's do the bb
        this.boundingBox = new XYBoundingBox(posX - (width / 2), posY, posX + (width / 2), posY + height);
    }

    public void updatePosition() {
        int speed = Math.min(motionX, tickSpeed);
        int vSpeed = Math.min(motionY, tickSpeed);

        this.posX += speed;
        this.posY -= vSpeed; // reverse for the sake of it
    }

    private void doCollisions() {
        int deltaX = Math.min(motionX, tickSpeed);
        int deltaY = Math.min(motionY, tickSpeed);

        this.onGround = false;
        for(Block block : world.getBlocksFor(section)) {
            XYBoundingBox bb = this.boundingBox.clone();
            bb.minY -= 1;
            bb.maxY += 1;
            if(block.boundingBox.intersects(bb)) {
                this.onGround = true;
                break;
            }
        }

        if (deltaX == 0 && deltaY == 0) {
            // No motion, no need to check for collisions
            return;
        }

        int futureX = posX + deltaX;
        int futureY = posY -+deltaY;

        XYBoundingBox tempHBB = new XYBoundingBox(futureX - (width / 2), posY, futureX + (width/2), posY + height);
        XYBoundingBox tempVBB = new XYBoundingBox(posX - (width / 2), futureY, posX + (width / 2), futureY + height);

        for (Block block : world.getBlocksFor(section)) {
            if (block.boundingBox.intersects(tempHBB)) {
                motionX = 0;
            }
            if (block.boundingBox.intersects(tempVBB)) {
                motionY = 0;
            }

            // If both horizontal and vertical motions are canceled, exit the loop
            if (motionX == 0 && motionY == 0) {
                break;
            }
        }
    }

    public void endOfTick() {

    }

    public void onCollide(Block block) {
        if(hasGravity && motionY < 0 && onGround){
            motionY = 0;
        }
    }

    public int getPosX() {
        if(section == 0)
            return posX;
        return (section * 600) + posX;
    }

    public int getRawX() {
        return posX;
    }

}
