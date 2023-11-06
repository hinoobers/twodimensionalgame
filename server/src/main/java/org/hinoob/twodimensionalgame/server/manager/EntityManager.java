package org.hinoob.twodimensionalgame.server.manager;

import org.hinoob.twodimensionalgame.server.entity.Entity;

import java.util.HashMap;
import java.util.Map;

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
}
