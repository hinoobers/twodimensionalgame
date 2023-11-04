package org.hinoob.two2d.packet;

import org.hinoob.loom.ByteWriter;
import org.hinoob.loom.LoomClient;

public class PacketWriter {

    private LoomClient client;

    public PacketWriter(LoomClient client) {
        this.client = client;
    }

    public void sendPacket(Packet packet) {
        ByteWriter writer = new ByteWriter();
        writer.writeInt(packet.getID());
        packet.write(writer);
        client.sendBytesToServer(writer.getBytes());
    }
}
