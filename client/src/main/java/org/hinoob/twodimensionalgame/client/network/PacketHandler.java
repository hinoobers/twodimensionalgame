package org.hinoob.twodimensionalgame.client.network;

import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.entity.Entity;
import org.hinoob.twodimensionalgame.client.entity.type.ServerPlayer;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.*;

public class PacketHandler {

    public void handlePacket(Packet packet) {
        if(packet instanceof AuthResponse) {
            handleAuth((AuthResponse) packet);
        } else if(packet instanceof JoinPacket) {
            handleJoin((JoinPacket) packet);
        } else if(packet instanceof WorldPacket) {
            handleWorld((WorldPacket) packet);
        } else if(packet instanceof UpdatePosition) {
            handleUpdatePos((UpdatePosition) packet);
        } else if(packet instanceof SpawnPlayer) {
            handleSpawn((SpawnPlayer)packet);
        } else if(packet instanceof DestroyEntity) {
            handleDestroy((DestroyEntity) packet);
        } else if(packet instanceof SectionResponse) {
            handleSectionResponse((SectionResponse) packet);
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

    private void handleUpdatePos(UpdatePosition packet) {
        Entity entity = TwodimensionalGame.getInstance().entityManager.getEntityById(packet.entityId);
        if(entity == null) return;

        entity.moveX(packet.posX);
        entity.moveY(packet.posY);
        entity.section = packet.section;
    }

    private void handleSpawn(SpawnPlayer packet) {
        if(packet.entityId == TwodimensionalGame.getInstance().getPlayer().entityId && TwodimensionalGame.getInstance().entityManager.doesEntityExist(packet.entityId)) {
            // Client should ignore spawning an entity with the same id as theirs
            return;
        }
        if(TwodimensionalGame.getInstance().entityManager.doesEntityExist(packet.entityId)) {
            // THIS IS A BUG
            System.out.println("Tried to spawn a entity that already exists, overwriting!");
            TwodimensionalGame.getInstance().entityManager.removeEntity(packet.entityId);
        }
        System.out.println("Spawning an external player");
        ServerPlayer player = new ServerPlayer();
        player.entityId = packet.entityId;
        player.section = TwodimensionalGame.getInstance().getPlayer().section; // they must be in the same section, since otherwise server wouldn't send it to us
        player.world = TwodimensionalGame.getInstance().getPlayer().world;
        player.displayName = packet.displayName;
        player.moveX(packet.posX);
        player.moveY(packet.posY);

        TwodimensionalGame.getInstance().entityManager.addEntity(player, TwodimensionalGame.getInstance().getPlayer().world);
    }

    private void handleDestroy(DestroyEntity packet) {
        System.out.println("Destroyed");
        TwodimensionalGame.getLogger().info("Destroyed entity");
        TwodimensionalGame.getInstance().entityManager.removeEntity(packet.entityId);
    }

    private void handleSectionResponse(SectionResponse packet) {
        System.out.println("REceived section data for =" + TwodimensionalGame.getInstance().getPlayer().section);
        TwodimensionalGame.getInstance().worldManager.getActiveWorld().updateSection(TwodimensionalGame.getInstance().getPlayer().section, packet.clientSection);
    }
}
