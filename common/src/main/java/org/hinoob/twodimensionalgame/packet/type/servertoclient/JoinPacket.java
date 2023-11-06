package org.hinoob.twodimensionalgame.packet.type.servertoclient;

import lombok.Getter;
import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;

// This is only for the player that is spawning
public class JoinPacket extends Packet {

    @Getter private int entityId;
    @Getter private String displayName;

    public JoinPacket(int entityID, String displayName) {
        this.entityId = entityID;
        this.displayName = displayName;
    }

    public JoinPacket() {

    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeString(this.displayName);
    }

    @Override
    public void read(ModifiedBuf buf) {
        this.entityId = buf.readInt();
        this.displayName = buf.readString();
    }

    @Override
    public int getID() {
        return 1;
    }
}
