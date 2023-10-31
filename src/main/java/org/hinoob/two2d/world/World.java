package org.hinoob.two2d.world;

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
        int screenWidth = 600;

        int sectionWidth = screenWidth; // Each section is as wide as the screen

        if (posX < 0) {
            // Calculate the negative section index
            int sectionIndex = (posX - sectionWidth) / sectionWidth;
            return sectionIndex;
        } else {
            // Calculate the positive section index
            int sectionIndex = posX / sectionWidth;
            System.out.println(posX);
            return sectionIndex;
        }
    }
    public Collection<Block> getBlocksFor(int section) {
        if(!sectionMap.containsKey(section)) {
            sectionMap.put(section, new WorldSection());
        }
        return sectionMap.get(section).getBlocks();
    }
}
