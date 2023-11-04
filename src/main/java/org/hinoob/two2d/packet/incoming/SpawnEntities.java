package org.hinoob.two2d.packet.incoming;

import org.hinoob.loom.ByteReader;
import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.entity.EntityType;
import org.hinoob.two2d.entity.type.ServerPlayer;
import org.hinoob.two2d.packet.Packet;
import org.hinoob.two2d.packet.PacketReader;
import org.hinoob.two2d.packet.PacketWriter;

public class SpawnEntities extends Packet {


    @Override
    public void handle(ByteReader reader) {
        int size = reader.readInt();
        for(int i = 0; i < size; i++) {
            int entityId = reader.readInt();
            if(player != null && player.entityId == entityId) continue; // No.

            EntityType type = EntityType.values()[reader.readInt()];
            int section = reader.readInt();
            if(TwodimensionalGame.getInstance().getEntityManager().doesEntityExist(entityId) && player != null && section != player.section) {
                TwodimensionalGame.getInstance().getEntityManager().removeEntity(TwodimensionalGame.getInstance().getEntityManager().getEntityById(entityId));
                continue;
            } else if(TwodimensionalGame.getInstance().getEntityManager().doesEntityExist(entityId)) {
                continue;
            }

            int posX = reader.readInt();
            int posY = reader.readInt();
            String displayName = reader.readBoolean() ? reader.readString() : null;

            if(type == EntityType.PLAYER) {
                ServerPlayer player = (ServerPlayer) TwodimensionalGame.getInstance().getEntityManager().addEntityWithRandomEntityID(new ServerPlayer(), getDefaultWorld());
                player.entityId = entityId;
                player.section = section;
                player.displayName = displayName;
                player.moveX(posX);
                player.moveY(posY);
            }
        }
    }
}
