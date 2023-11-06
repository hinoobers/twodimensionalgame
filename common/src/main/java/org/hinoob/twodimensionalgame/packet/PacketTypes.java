package org.hinoob.twodimensionalgame.packet;

import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.AuthResponse;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.JoinPacket;

public class PacketTypes {

    public static Packet clientToServer(int id) {
        switch (id) {
            case 0:
                return new AuthPacket();
        }

        return null;
    }

    public static Packet serverToClient(int id) {
        switch (id) {
            case 0:
                return new AuthResponse();
            case 1:
                return new JoinPacket();
        }

        return null;
    }
}
