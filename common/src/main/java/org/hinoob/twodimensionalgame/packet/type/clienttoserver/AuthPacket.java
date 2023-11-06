package org.hinoob.twodimensionalgame.packet.type.clienttoserver;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;

import java.lang.annotation.Target;

public class AuthPacket extends Packet {

    public String username, password;

    public AuthPacket(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthPacket() {

    }

    @Override
    public void read(ModifiedBuf buf) {
        this.username = buf.readString();
        this.password = buf.readString();
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeString(this.username);
        buf.writeString(this.password);
    }
}
