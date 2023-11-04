package org.hinoob.two2d.packet;

import org.hinoob.loom.ByteReader;
import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.entity.type.ClientPlayer;
import org.hinoob.two2d.world.World;

public class Packet {

    protected PacketReader reader;
    protected PacketWriter writer;

    protected ClientPlayer player;


    // outgoing
    public Packet() {
        this.reader = TwodimensionalGame.getInstance().getPacketReader();
        this.writer = TwodimensionalGame.getInstance().getPacketWriter();
        this.player = TwodimensionalGame.getInstance().getPlayer();
    }

    public void handle(ByteReader reader) {

    }

    public void write(ByteWriter writer) {

    }

    public World getDefaultWorld() {
        return TwodimensionalGame.getInstance().getWorld();
    }

    public int getID() {
        return -1;
    }

    public int getOurID() {
        return TwodimensionalGame.getInstance().getClient().getClientID();
    }
}
