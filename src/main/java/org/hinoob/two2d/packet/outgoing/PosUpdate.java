package org.hinoob.two2d.packet.outgoing;

import org.hinoob.loom.ByteReader;
import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.entity.type.ClientPlayer;
import org.hinoob.two2d.packet.Packet;

public class PosUpdate extends Packet {

    private ClientPlayer player;

    public PosUpdate(ClientPlayer player) {
        this.player = player;
    }


    @Override
    public void write(ByteWriter writer) {
        writer.writeInt(getOurID());
        writer.writeInt(player.getPosX());
        writer.writeInt(player.getPosY());
        writer.writeInt(player.section);
    }
}
