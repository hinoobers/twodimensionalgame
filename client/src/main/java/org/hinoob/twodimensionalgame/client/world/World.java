package org.hinoob.twodimensionalgame.client.world;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.block.Block;
import org.hinoob.twodimensionalgame.client.entity.Entity;
import org.hinoob.twodimensionalgame.packet.type.clienttoserver.DestroyBlock;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class World {

    private final Map<Integer, WorldSection> sectionMap = new HashMap<>();

    public int calculateSectionFor(Entity player) {
        int posX = player.getPosX();
        int screenWidth = TwodimensionalGame.SCREEN_WIDTH;

        int sectionWidth = screenWidth; // Each section is as wide as the screen

        if (posX < 0) {
            // Calculate the negative section index
            int sectionIndex = (posX - sectionWidth) / sectionWidth;
            return sectionIndex;
        } else {
            // Calculate the positive section index
            int sectionIndex = posX / sectionWidth;
            return sectionIndex;
        }
    }
    public Collection<Block> getBlocksFor(int section) {
        if(!sectionMap.containsKey(section)) {
            sectionMap.put(section, new WorldSection());
        }
        return sectionMap.get(section).getBlocks();
    }

    public void createSection(int section) {
        WorldSection worldSection = new WorldSection();
        sectionMap.put(section, worldSection);
    }

    public void destroyBlock(Block block) {
//        for(WorldSection section : sectionMap.values()) {
//            if(section.getBlocks().contains(block)) {
//                section.removeBlock(block);
//            }
//        }

        TwodimensionalGame.getInstance().getClient().sendPacket(new DestroyBlock(block.x, block.y));
    }

    public void loadDataFrom(ModifiedBuf buf) {
        sectionMap.clear();

        int sections = buf.readInt();
        for(int i = 0; i < sections; i++) {
            int sectionID = buf.readInt();
            System.out.println("Section with id " + sectionID);
            WorldSection section = new WorldSection();
            section.loadDataFrom(buf);

            sectionMap.put(sectionID, section);
        }
    }

    public void updateSection(int sectionId, WorldSection section) {
        sectionMap.put(sectionId, section);
    }

    public boolean sectionExists(int section) {
        return sectionMap.containsKey(section);
    }

    public WorldSection getSection(int section) {
        return sectionMap.get(section);
    }
}
