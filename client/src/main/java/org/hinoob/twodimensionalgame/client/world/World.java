package org.hinoob.twodimensionalgame.client.world;

import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.client.block.Block;
import org.hinoob.twodimensionalgame.client.entity.Entity;

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

    public void removeBlock(Block block) {
        for(WorldSection section : sectionMap.values()) {
            if(section.getBlocks().contains(block)) {
                section.removeBlock(block);
            }
        }
    }

//    public static World deserialize(ByteReader reader) {
//        World world = new World();
//
//        int sectionCount = reader.readInt();
//        for(int i = 0; i < sectionCount; i++) {
//            int section = reader.readInt();
//            WorldSection worldSection = new WorldSection(reader);
//            world.sectionMap.put(section, worldSection);
//        }
//
//        return world;
//    }

    public boolean sectionExists(int section) {
        return sectionMap.containsKey(section);
    }

    public WorldSection getSection(int section) {
        return sectionMap.get(section);
    }
}
