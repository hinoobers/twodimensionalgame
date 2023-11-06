package org.hinoob.twodimensionalgame.client.manager;


import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.world.World;

public class WorldManager
{

    private World activeWorld;

    public void loadWorld(World world) {
        activeWorld = world;
        TwodimensionalGame.getInstance().entityManager.getEntities().forEach(e -> e.world = activeWorld);
    }

    public World getActiveWorld() {
        return activeWorld;
    }
}
