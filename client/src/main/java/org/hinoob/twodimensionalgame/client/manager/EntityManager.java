package org.hinoob.twodimensionalgame.client.manager;

import org.hinoob.twodimensionalgame.client.entity.Entity;
import org.hinoob.twodimensionalgame.client.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EntityManager {

    private final List<Entity> entities = new ArrayList<>();

    public Entity addEntityWithRandomEntityID(Entity entity, World world) {
        entity.entityId = entities.size() + 1;
        entity.world = world;
        entities.add(entity);
        return entity;
    }

    public Entity addEntity(Entity entity, World world) {
        entity.world = world;
        entities.add(entity);
        return entity;
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public boolean doesEntityExist(int id) {
        for(Entity entity : entities) {
            if(entity.entityId == id) {
                return true;
            }
        }
        return false;
    }

    public Entity getEntityById(int id) {
        for(Entity entity : entities) {
            if(entity.entityId == id) {
                return entity;
            }
        }
        return null;
    }

    public Collection<Entity> getEntities() {
        return Collections.unmodifiableCollection(this.entities);
    }
}
