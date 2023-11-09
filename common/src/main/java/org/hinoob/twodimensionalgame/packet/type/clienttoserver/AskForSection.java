package org.hinoob.twodimensionalgame.packet.type.clienttoserver;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.client.world.WorldSection;
import org.hinoob.twodimensionalgame.packet.Packet;

public class AskForSection extends Packet {

    public int section;

    public AskForSection(int section) {
        this.section = section;
    }

    public AskForSection() {

    }

    @Override
    public void read(ModifiedBuf buf) {
        this.section = buf.readInt();
    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeInt(this.section);
    }

    @Override
    public int getID() {
        return 2;
    }
}
