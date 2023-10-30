package org.hinoob.two2d.entity;

public class Player extends LivingEntity{

    protected final int width = 15;
    protected final int height = 15;

    private boolean jumped = false;
    public void jump() {
        if(jumped) {
            // debounce
            return;
        }
        this.motionY = 2;
        jumped = true;
    }
}
