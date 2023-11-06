package org.hinoob.twodimensionalgame.server.manager;

import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.AuthResponse;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.JoinPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.WorldPacket;
import org.hinoob.twodimensionalgame.server.Server;
import org.hinoob.twodimensionalgame.server.entity.Player;

public class PacketHandler {

    public void handlePacket(Player player, Packet packet) {
        if(packet instanceof AuthPacket) {
            handleAuth(player, (AuthPacket) packet);
        }
    }

    private void handleAuth(Player player, AuthPacket packet) {
        if (Server.getInstance().databaseManager.doesUserExist(packet.username, packet.password)) {
            player.sendPacket(new AuthResponse(AuthResponse.Response.OK));
            player.sendPacket(new JoinPacket(player.entityId, "Player #" + player.entityId));
            player.sendPacket(new WorldPacket(Server.getInstance().worldManager.getWorlds().stream().toList().get(0)));
        } else {
            player.sendPacket(new AuthResponse(AuthResponse.Response.FAILED));
        }
    }
}
