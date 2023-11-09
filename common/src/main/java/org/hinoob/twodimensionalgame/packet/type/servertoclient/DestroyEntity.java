package org.hinoob.twodimensionalgame.packet.type.servertoclient;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;

public class DestroyEntity extends Packet {

    public int entityId;

    public DestroyEntity(int id) {
        this.entityId = id;
    }

    public DestroyEntity() {

    }

    @Override
    public void read(ModifiedBuf buf) {
        this.entityId = buf.readInt();
    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeInt(this.entityId);
    }

    @Override
    public int getID() {
        return 5;
    }
}
