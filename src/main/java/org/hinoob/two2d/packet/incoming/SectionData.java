package org.hinoob.two2d.packet.incoming;

import org.hinoob.loom.ByteReader;
import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.packet.Packet;
import org.hinoob.two2d.packet.PacketReader;
import org.hinoob.two2d.packet.PacketWriter;
import org.hinoob.two2d.world.World;
import org.hinoob.two2d.world.WorldSection;

public class SectionData extends Packet {

    @Override
    public void handle(ByteReader reader) {
        int sectionID = reader.readInt();
        World world = getDefaultWorld();

        WorldSection section = world.getSection(sectionID);
        if(section == null) {
            world.createSection(sectionID);
            section = world.getSection(sectionID);
        }

        section.update(reader);
    }
}
