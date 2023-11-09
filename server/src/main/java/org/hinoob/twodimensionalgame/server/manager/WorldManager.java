package org.hinoob.twodimensionalgame.server.manager;

import org.hinoob.twodimensionalgame.server.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WorldManager {

    private final List<World> worlds = new ArrayList<>();

    public WorldManager() {
        if(worlds.isEmpty()) {
            // test world
            worlds.add(new World());
        }
    }

    public Collection<World> getWorlds() {
        return Collections.unmodifiableCollection(this.worlds);
    }

    public void doWorldGeneration() {
        worlds.get(0).doWorldGeneration();
    }
}
