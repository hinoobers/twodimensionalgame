package org.hinoob.two2d.manager;

import org.hinoob.two2d.entity.Entity;
import org.hinoob.two2d.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EntityManager {

    private final List<Entity> entities = new ArrayList<>();

    public Entity addEntity(Entity entity, World world) {
        entity.entityId = entities.size() + 1;
        entity.world = world;
        entities.add(entity);
        return entity;
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public Collection<Entity> getEntities() {
        return Collections.unmodifiableCollection(this.entities);
    }
}
