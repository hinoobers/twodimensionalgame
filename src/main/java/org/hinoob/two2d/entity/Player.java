package org.hinoob.two2d.entity;

public class Player extends LivingEntity{

    private boolean jumped = false;

    public Player() {
        this.width = 15;
        this.height = 15;
    }
    public void jump() {
        if(jumped) {
            // debounce
            return;
        }
        this.motionY = 35;
        jumped = true;
    }

    @Override
    public void onTick() {
        super.onTick();

        if(onGround) {
            jumped = false;
        }
    }
}
