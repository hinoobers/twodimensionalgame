package org.hinoob.twodimensionalgame.packet.type.servertoclient;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;

public class AuthResponse extends Packet {

    public Response response;

    public AuthResponse(Response response) {
        this.response = response;
    }

    public AuthResponse() {

    }

    @Override
    public void write(ModifiedBuf buf) {
        buf.writeInt(response.ordinal());
    }

    @Override
    public void read(ModifiedBuf buf) {
        this.response = Response.values()[buf.readInt()];
    }

    @Override
    public int getID() {
        return 0;
    }

    public enum Response {
        OK,
        FAILED;
    }
}
