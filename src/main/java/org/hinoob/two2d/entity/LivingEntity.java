package org.hinoob.two2d.entity;

public class LivingEntity extends Entity{

    public double health, maxHealth;
    public int motionX, motionY;
    public LivingEntity() {
        this.health = maxHealth;
    }

    @Override
    public void onTick() {
        this.posX += motionX;
        this.posY += (-motionY); // reverse for the sake of it
        motionY = Math.max(0, motionY - 1);
        motionX = Math.max(0, motionX - 1);
    }
}
