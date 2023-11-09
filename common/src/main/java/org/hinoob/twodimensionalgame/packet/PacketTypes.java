package org.hinoob.twodimensionalgame.packet;

import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AskForSection;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.DestroyBlock;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.MovePacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.*;

public class PacketTypes {

    public static Packet clientToServer(int id) {
        return switch (id) {
            case 0 -> new AuthPacket();
            case 1 -> new MovePacket();
            case 2 -> new AskForSection();
            case 3 -> new DestroyBlock();
            default -> null;
        };
    }

    public static Packet serverToClient(int id) {
        return switch (id) {
            case 0 -> new AuthResponse();
            case 1 -> new JoinPacket();
            case 2 -> new WorldPacket();
            case 3 -> new UpdatePosition();
            case 4 -> new SpawnPlayer();
            case 5 -> new DestroyEntity();
            case 6 -> new SectionResponse();
            default -> null;
        };

    }
}
