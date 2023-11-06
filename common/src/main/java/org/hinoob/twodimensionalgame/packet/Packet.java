package org.hinoob.twodimensionalgame.packet;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;

public abstract class Packet {

    public void write(ModifiedBuf buf) {

    }

    public void read(ModifiedBuf buf) {

    }


    public abstract int getID();
}
