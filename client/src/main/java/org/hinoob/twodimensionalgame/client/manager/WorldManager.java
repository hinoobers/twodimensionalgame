package org.hinoob.twodimensionalgame.client.manager;


import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.world.World;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.AskForSection;

public class WorldManager
{

    private World activeWorld;

    public void loadWorld(World world) {
        activeWorld = world;
        TwodimensionalGame.getInstance().entityManager.getEntities().forEach(e -> e.world = activeWorld);
        TwodimensionalGame.getInstance().getClient().sendPacket(new AskForSection(0));
    }

    public World getActiveWorld() {
        return activeWorld;
    }
}
