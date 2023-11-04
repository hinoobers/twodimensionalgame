package org.hinoob.two2d.packet.incoming;

import org.hinoob.loom.ByteReader;
import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.packet.Packet;

public class LoginResponse extends Packet {


    @Override
    public void handle(ByteReader reader) {
        Response response = Response.valueOf(reader.readString());
        if(response == Response.FAILED) {
            TwodimensionalGame.getInstance().handleLoginFailed();
        } else {
            int entityId = reader.readInt();
            String displayName = reader.readString();
            TwodimensionalGame.getInstance().handleLoginSuccess(entityId, displayName);
        }
    }

    @Override
    public int getID() {
        return 3;
    }

    public enum Response {
        FAILED,
        SUCCESS;
    }
}
