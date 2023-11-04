package org.hinoob.two2d.packet;

import org.hinoob.loom.ByteReader;
import org.hinoob.loom.ByteWriter;
import org.hinoob.loom.LoomClient;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.packet.incoming.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PacketReader {

    private LoomClient client;


    public PacketReader(LoomClient client) {
        this.client = client;
    }

    public void handlePacket(byte[] bytes) {
        ByteReader reader = new ByteReader(bytes);

        int packetID = reader.readInt();
        System.out.println("Received packet from server id="+packetID);
        switch (packetID) {
            case 0:
                new PosUpdate().handle(reader);
                break;
            case 1:
                new SectionData().handle(reader);
                break;
            case 2:
                new SpawnEntities().handle(reader);
                break;
            case 3:
                new LoginResponse().handle(reader);
                break;
            case 4:
                new WorldData().handle(reader);
                break;
        }
    }
}

