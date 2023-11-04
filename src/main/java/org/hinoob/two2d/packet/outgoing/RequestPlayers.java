package org.hinoob.two2d.packet.outgoing;

import org.hinoob.loom.ByteReader;
import org.hinoob.two2d.packet.Packet;
import org.hinoob.two2d.packet.PacketReader;
import org.hinoob.two2d.packet.PacketWriter;

public class RequestPlayers extends Packet {


    @Override
    public int getID() {
        return 1;
    }
}
