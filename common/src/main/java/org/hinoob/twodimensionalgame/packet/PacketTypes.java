package org.hinoob.twodimensionalgame.packet;

import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.AuthResponse;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.JoinPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.WorldPacket;

public class PacketTypes {

    public static Packet clientToServer(int id) {
        switch (id) {
            case 0:
                return new AuthPacket();
        }

        return null;
    }

    public static Packet serverToClient(int id) {
        return switch (id) {
            case 0 -> new AuthResponse();
            case 1 -> new JoinPacket();
            case 2 -> new WorldPacket();
            default -> null;
        };

    }
}
