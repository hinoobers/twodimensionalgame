package org.hinoob.two2d.entity.type;

import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.entity.LivingEntity;
import org.hinoob.two2d.entity.Player;

import java.awt.*;

public class ClientPlayer extends Player {


    @Override
    public void onDraw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(this.posX, this.posY, width, height);
    }

    @Override
    public void onCollide(Block block) {
        super.onCollide(block);
    }

}
