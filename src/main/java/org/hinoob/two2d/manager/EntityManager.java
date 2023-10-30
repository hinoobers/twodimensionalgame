package org.hinoob.two2d.manager;

import org.hinoob.two2d.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EntityManager {

    private final List<Entity> entities = new ArrayList<>();

    public Entity addEntity(Entity entity) {
        entity.entityId = entities.size() + 1;
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
