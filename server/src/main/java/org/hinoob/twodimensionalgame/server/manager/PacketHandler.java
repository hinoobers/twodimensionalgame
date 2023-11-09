package org.hinoob.twodimensionalgame.server.manager;

import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AskForSection;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AuthPacket;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.DestroyBlock;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.MovePacket;
import org.hinoob.twodimensionalgame.packet.type.servertoclient.*;
import org.hinoob.twodimensionalgame.server.Server;
import org.hinoob.twodimensionalgame.server.entity.Player;
import org.hinoob.twodimensionalgame.server.world.World;

public class PacketHandler {

    public void handlePacket(Player player, Packet packet) {
        if(packet instanceof AuthPacket) {
            handleAuth(player, (AuthPacket) packet);
        } else if(packet instanceof MovePacket) {
            handleMove(player, (MovePacket) packet);
        } else if(packet instanceof AskForSection) {
            handleSectionAsk(player, (AskForSection) packet);
        } else if(packet instanceof DestroyBlock) {
            handleDestroyBlock(player, (DestroyBlock) packet);
        }
    }

    private void handleDestroyBlock(Player player, DestroyBlock packet) {
        System.out.println("Modified section " + player.section);
        if(player.world.getSection(player.section).removeBlockByCoordinates(packet.x, packet.y)){
            System.out.println("Removed");
        }

        // Send section updates to everyone (probably not the best for memory)
        int i = 3;
        while(i > 0) {
            // stupid fix ^
            i--;
            for(Player other : Server.getInstance().entityManager.getPlayers()) {
                // Tehnically, should only do for those who are affected, but it doesn't hurt to send a update to everyone/everybody
                other.sendPacket(new SectionResponse(other.world.getSection(other.section)));
            }
        }
    }

    private void handleAuth(Player player, AuthPacket packet) {
        if (Server.getInstance().databaseManager.doesUserExist(packet.username, packet.password)) {
            player.sendPacket(new AuthResponse(AuthResponse.Response.OK));
            player.sendPacket(new JoinPacket(player.entityId, packet.username));

            World world = Server.getInstance().worldManager.getWorlds().stream().toList().get(0);
            player.sendPacket(new WorldPacket(world));
            player.world = world;
            player.displayName = packet.username;
            player.authenticated = true;

            // Spawn others for us
            for(Player other : Server.getInstance().entityManager.getPlayers()) {
                if(other.section != player.section) continue;
                if(!other.equals(player)) {
                    if(!other.canSee.contains(player)) {
                        other.sendPacket(new SpawnPlayer(player.entityId, player.posX, player.posY, player.displayName));
                        other.canSee.add(player);
                    }

                    if(!player.canSee.contains(other)) {
                        player.sendPacket(new SpawnPlayer(other.entityId, other.posX, other.posY, other.displayName));
                        player.canSee.add(other);
                    }
                }
            }
        } else {
            player.sendPacket(new AuthResponse(AuthResponse.Response.FAILED));
        }
    }

    private void handleMove(Player player, MovePacket packet) {
        int distanceMoved = Math.abs(player.posX - packet.posX) + Math.abs(player.posY - packet.posY);
        if((distanceMoved > 35 && player.section == packet.section) || Math.abs(player.section - packet.section) > 1) {
            player.sendPacket(new UpdatePosition(player.entityId, player.posX, player.posY, player.section));
            return;
        }

        player.posX = packet.posX;
        player.posY = packet.posY;
        player.section = packet.section;

        // Clear players when they switched sections
        for(Player other : Server.getInstance().entityManager.getPlayers()) {
            if(other.section == player.section) continue;

            if(other.canSee.contains(player)) {
                other.sendPacket(new DestroyEntity(player.entityId));
                other.canSee.remove(player);
            }

            if(player.canSee.contains(other)) {
                player.sendPacket(new DestroyEntity(other.entityId));
                player.canSee.remove(other);
            }
        }

        // Spawn new players
        for(Player other : Server.getInstance().entityManager.getPlayers()) {
            if(other.section == player.section) {
                if(!other.canSee.contains(player)) {
                    other.sendPacket(new SpawnPlayer(player.entityId, player.posX, player.posY, player.displayName));
                    other.canSee.add(player);
                }

                if(!player.canSee.contains(other)) {
                    player.sendPacket(new SpawnPlayer(other.entityId, other.posX, other.posY, other.displayName));
                    player.canSee.add(other);
                }
            }
        }

        for(Player other : Server.getInstance().entityManager.getPlayers()) {
            if(other.section == player.section && !other.equals(player)) {
                other.sendPacket(new UpdatePosition(player.entityId, player.posX, player.posY, player.section));
            }
        }
    }

    private void handleSectionAsk(Player player, AskForSection packet) {
        System.out.println("Sent response for section=" + player.section);
        if(Math.abs(packet.section - player.section) > 2) return; // Refuse
        player.sendPacket(new SectionResponse(player.world.getSection(packet.section)));
    }
}
