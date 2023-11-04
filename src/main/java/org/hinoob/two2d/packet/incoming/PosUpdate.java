package org.hinoob.two2d.packet.incoming;

import org.hinoob.loom.ByteReader;
import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.entity.Entity;
import org.hinoob.two2d.packet.Packet;
import org.hinoob.two2d.packet.PacketReader;
import org.hinoob.two2d.packet.PacketWriter;

public class PosUpdate extends Packet {

    @Override
    public void handle(ByteReader reader) {
        int entityId = reader.readInt();
        int posX = reader.readInt();
        int posY = reader.readInt();

        Entity entity = TwodimensionalGame.getInstance().getEntityManager().getEntityById(entityId);
        if(entity == null) return;
        entity.moveX(posX);
        entity.moveY(posY);
    }
}
