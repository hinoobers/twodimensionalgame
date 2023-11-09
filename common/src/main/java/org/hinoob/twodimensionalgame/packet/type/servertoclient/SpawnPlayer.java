package org.hinoob.twodimensionalgame.packet.type.servertoclient;

import org.hinoob.twodimensionalgame.EntityType;
import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;

// used to spawn others, for us and vise-versa
public class SpawnPlayer extends Packet {

    public int entityId;
    public int posX, posY;
    public String displayName;
    //public int section; Server handles our visibility

    public SpawnPlayer(int entityId, int posX, int posY, String displayName){
        this.entityId = entityId;
        this.posX = posX;
        this.posY = posY;
        this.displayName = displayName;
    }

    public SpawnPlayer() {

    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeInt(this.posX);
        buf.writeInt(this.posY);
        buf.writeString(this.displayName);
    }

    @Override
    public void read(ModifiedBuf buf) {
        this.entityId = buf.readInt();
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.displayName = buf.readString();
    }

    @Override
    public int getID() {
        return 4;
    }
}
