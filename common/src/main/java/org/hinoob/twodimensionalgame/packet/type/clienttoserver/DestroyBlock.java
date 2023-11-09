package org.hinoob.twodimensionalgame.packet.type.clienttoserver;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;

public class DestroyBlock extends Packet {

    public int x, y;

    public DestroyBlock(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public DestroyBlock() {

    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
    }

    @Override
    public void read(ModifiedBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
    }

    @Override
    public int getID() {
        return 3;
    }
}
