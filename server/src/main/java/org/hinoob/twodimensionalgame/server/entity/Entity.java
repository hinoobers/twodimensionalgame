package org.hinoob.twodimensionalgame.server.entity;

import org.hinoob.twodimensionalgame.EntityType;
import org.hinoob.twodimensionalgame.server.world.World;

public class Entity {

    public int entityId;
    public EntityType type;
    public int posX, posY;
    public int section;
    public World world;

    public void tick() {

    }
}
