package org.hinoob.two2d.entity.type;

import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.entity.LivingEntity;
import org.hinoob.two2d.entity.Player;
import org.hinoob.two2d.packet.outgoing.PosUpdate;

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

        TwodimensionalGame.getInstance().getPacketWriter().sendPacket(new PosUpdate(this));
    }

    @Override
    public void onCollide(Block block) {
        super.onCollide(block);
    }

}
