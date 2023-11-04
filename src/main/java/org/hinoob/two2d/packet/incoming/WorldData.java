package org.hinoob.two2d.packet.incoming;

import org.hinoob.loom.ByteReader;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.packet.Packet;
import org.hinoob.two2d.world.World;

public class WorldData extends Packet {

    @Override
    public void handle(ByteReader reader) {
        TwodimensionalGame.getInstance().setWorld(World.deserialize(reader));
        TwodimensionalGame.getInstance().getEntityManager().getEntities().forEach(entity -> entity.world = TwodimensionalGame.getInstance().getWorld()); // temp fix, update world instance
    }
}
