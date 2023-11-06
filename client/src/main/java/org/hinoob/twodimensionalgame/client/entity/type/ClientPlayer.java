package org.hinoob.twodimensionalgame.client.entity.type;

import org.hinoob.twodimensionalgame.client.block.Block;
import org.hinoob.twodimensionalgame.client.entity.Player;

import java.awt.*;

public class ClientPlayer extends Player {

    public String displayName;
    @Override
    public void onDraw(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        graphics.drawString(displayName, posX, posY - 15);
        graphics.setColor(Color.RED);
        graphics.fillRect(this.posX, this.posY, width, height);
    }

    @Override
    public void updatePosition() {
        super.updatePosition();


    }

    @Override
    public void onCollide(Block block) {
        super.onCollide(block);
    }

}
