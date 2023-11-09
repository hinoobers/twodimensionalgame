package org.hinoob.twodimensionalgame.server.world;

import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.server.Server;
import org.hinoob.twodimensionalgame.server.block.Block;
import org.hinoob.twodimensionalgame.server.entity.Player;
import org.hinoob.twodimensionalgame.server.world.generator.DefaultGenerator;

import java.util.Collection;
import java.util.Collections;
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

    public void doWorldGeneration() {
        // Load two sections at once, minimum loaded and maximum loaded
        int min = Collections.min(this.sectionMap.keySet());
        int max = Collections.max(this.sectionMap.keySet());

        // Do we even need to load?
        boolean need = false;
        for(Player player : Server.getInstance().entityManager.getPlayers()) {
            if(Math.abs(player.section - min) < 2 || Math.abs(player.section - max) < 2) {
                need = true;
            }
        }
        if(!need) return;
        WorldSection minSection = new WorldSection();
        minSection.generate(new DefaultGenerator());
        WorldSection maxSection = new WorldSection();
        maxSection.generate(new DefaultGenerator());

        this.sectionMap.put(min - 1, minSection);
        this.sectionMap.put(max + 1, maxSection);
        System.out.println("Generated " + (min - 1) + " and " + (max + 1));
    }
}
