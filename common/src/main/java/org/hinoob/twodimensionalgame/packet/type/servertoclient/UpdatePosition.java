package org.hinoob.twodimensionalgame.packet.type.servertoclient;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;

public class UpdatePosition extends Packet {

    public int entityId;
    public int posX, posY, section;

    public UpdatePosition(int entityId, int posX, int posY, int section) {
        this.entityId = entityId;
        this.posX = posX;
        this.posY = posY;
        this.section = section;
    }

    public UpdatePosition() {

    }

    @Override
    public void read(ModifiedBuf buf) {
        this.entityId = buf.readInt();
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.section = buf.readInt();
    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeInt(this.posX);
        buf.writeInt(this.posY);
        buf.writeInt(this.section);
    }

    @Override
    public int getID() {
        return 3;
    }
}
