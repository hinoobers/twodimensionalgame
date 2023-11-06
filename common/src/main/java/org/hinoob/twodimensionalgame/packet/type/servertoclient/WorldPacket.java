package org.hinoob.twodimensionalgame.packet.type.servertoclient;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.server.world.World;

public class WorldPacket extends Packet {

    private World world;
    public org.hinoob.twodimensionalgame.client.world.World clientWorld;

    public WorldPacket(World world) {
        this.world = world;
    }

    public WorldPacket() {

    }

    @Override
    public void read(ModifiedBuf buf) {
        this.clientWorld = new org.hinoob.twodimensionalgame.client.world.World();
        this.clientWorld.loadDataFrom(buf);
    }

    @Override
    public void write(ModifiedBuf buf) {
        world.writeTo(buf);
    }

    @Override
    public int getID() {
        return 2;
    }
}
