package org.hinoob.twodimensionalgame.server.world;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.server.block.Block;
import org.hinoob.twodimensionalgame.server.world.generator.DefaultGenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class World {

    private final Map<Integer, WorldSection> sectionMap = new HashMap<>();

    public World() {
        createSection(-1);
        createSection(0);
        createSection(1);
        getSection(0).generate(new DefaultGenerator());
        getSection(1).generate(new DefaultGenerator());
        getSection(-1).generate(new DefaultGenerator());
    }

//    public int calculateSectionFor(Entity player) {
//        int posX = player.posX;
//        int screenWidth = TwodimensionalGame.SCREEN_WIDTH;
//
//        int sectionWidth = screenWidth; // Each section is as wide as the screen
//
//        if (posX < 0) {
//            // Calculate the negative section index
//            int sectionIndex = (posX - sectionWidth) / sectionWidth;
//            return sectionIndex;
//        } else {
//            // Calculate the positive section index
//            int sectionIndex = posX / sectionWidth;
//            return sectionIndex;
//        }
//    }
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

    public void writeTo(ModifiedBuf buf) {
        buf.writeInt(this.sectionMap.size());
        for(Map.Entry<Integer, WorldSection> data : sectionMap.entrySet()) {
            buf.writeInt(data.getKey());
            data.getValue().writeTo(buf);
            System.out.println("Blocks - " + data.getValue().getBlocks().size());
        }
    }

    public void loadDataFrom(ModifiedBuf buf) {
        sectionMap.clear();

        int sections = buf.readInt();
        for(int i = 0; i < sections; i++) {
            int sectionID = buf.readInt();

            WorldSection section = new WorldSection();
            section.loadDataFrom(buf);

            sectionMap.put(sectionID, section);
        }
    }

    public boolean sectionExists(int section) {
        return sectionMap.containsKey(section);
    }

    public WorldSection getSection(int section) {
        return sectionMap.get(section);
    }
}
