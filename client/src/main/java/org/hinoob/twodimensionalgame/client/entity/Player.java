package org.hinoob.twodimensionalgame.client.entity;

public class Player extends LivingEntity{

    private boolean jumped = false;

    public Player() {
        this.width = 15;
        this.height = 15;
    }


    public void jump() {
        if(jumped || !onGround) {
            // debounce
            return;
        }
        this.motionY = 15;
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
