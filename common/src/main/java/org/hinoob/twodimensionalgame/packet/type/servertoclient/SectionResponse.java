package org.hinoob.twodimensionalgame.packet.type.servertoclient;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.server.world.WorldSection;

public class SectionResponse extends Packet {

    private WorldSection serverSection;
    public org.hinoob.twodimensionalgame.client.world.WorldSection clientSection;

    public SectionResponse(WorldSection serverSection) {
        this.serverSection = serverSection;
    }

    public SectionResponse() {

    }


    @Override
    public void read(ModifiedBuf buf) {
        clientSection = new org.hinoob.twodimensionalgame.client.world.WorldSection();
        clientSection.loadDataFrom(buf);
    }

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void write(ModifiedBuf buf) {
        serverSection.writeTo(buf);
    }
}
