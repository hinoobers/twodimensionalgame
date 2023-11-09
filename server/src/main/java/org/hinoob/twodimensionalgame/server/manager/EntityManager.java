package org.hinoob.twodimensionalgame.server.manager;

import org.hinoob.twodimensionalgame.EntityType;
import org.hinoob.twodimensionalgame.server.entity.Entity;
import org.hinoob.twodimensionalgame.server.entity.Player;

import java.util.*;

public class EntityManager {

    private final Map<Integer, Entity> entityMap = new HashMap<>();

    public Entity addEntity(Entity entity) {
        int entityId = entityMap.size() + 1;
        entity.entityId = entityId;
        entityMap.put(entity.entityId, entity);
        return entity;
    }

    public void removeEntity(int entityId) {
        entityMap.remove(entityId);
    }

    public Collection<Player> getPlayers() {
        return entityMap.values().stream().filter(p -> p.type == EntityType.PLAYER && ((Player)p).authenticated).map(d -> (Player) d).toList();
    }
}
