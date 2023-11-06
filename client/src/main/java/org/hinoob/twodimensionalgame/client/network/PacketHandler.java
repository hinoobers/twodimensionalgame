package org.hinoob.twodimensionalgame.client.network;

import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.AuthResponse;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.JoinPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.WorldPacket;

public class PacketHandler {

    public void handlePacket(Packet packet) {
        if(packet instanceof AuthResponse) {
            handleAuth((AuthResponse) packet);
        } else if(packet instanceof JoinPacket) {
            handleJoin((JoinPacket) packet);
        } else if(packet instanceof WorldPacket) {
            handleWorld((WorldPacket) packet);
        }
    }

    private void handleAuth(AuthResponse packet) {
        System.out.println("Received auth response! response=" + packet.response);
        if(packet.response == AuthResponse.Response.OK) {
            // This is just an indicator, we are now waiting for the player join packet
        } else {
            TwodimensionalGame.getInstance().handleLoginFailed();
        }
    }

    private void handleJoin(JoinPacket packet) {
        System.out.println("Received join packet!");
        TwodimensionalGame.getInstance().handleLoginSuccess(packet.entityId, packet.displayName);
    }

    private void handleWorld(WorldPacket packet) {
        System.out.println("Received world packet!");
        TwodimensionalGame.getInstance().worldManager.loadWorld(packet.clientWorld);
    }
}