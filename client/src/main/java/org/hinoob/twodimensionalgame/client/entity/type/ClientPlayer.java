package org.hinoob.twodimensionalgame.client.entity.type;

import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.block.Block;
import org.hinoob.twodimensionalgame.client.entity.Player;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AskForSection;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.MovePacket;

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
        int posX = this.posX;
        int posY = this.posY;
        int section = this.section;
        super.updatePosition();

        if(posX == this.posX && posY == this.posY && section == this.section) return;
        TwodimensionalGame.getInstance().getClient().sendPacket(new MovePacket(this.entityId, this.posX, this.posY, this.section));
    }

    @Override
    public void onCollide(Block block) {
        super.onCollide(block);
    }

}
