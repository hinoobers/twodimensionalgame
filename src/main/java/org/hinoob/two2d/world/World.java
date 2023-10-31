package org.hinoob.two2d.world;

import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.block.Block;
import org.hinoob.two2d.entity.Entity;
import org.hinoob.two2d.entity.type.ClientPlayer;

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

    public void removeBlock(Block block) {
        for(WorldSection section : sectionMap.values()) {
            if(section.getBlocks().contains(block)) {
                section.removeBlock(block);
            }
        }
    }
}
