package org.hinoob.two2d.packet.outgoing;

import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.packet.Packet;

public class LoginAttempt extends Packet {

    private String username, password;

    public LoginAttempt(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void write(ByteWriter writer) {
        writer.writeString(username);
        writer.writeString(password);
    }

    @Override
    public int getID() {
        return 2;
    }
}
