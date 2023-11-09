package org.hinoob.twodimensionalgame.server.entity;

import io.netty.channel.Channel;
import org.hinoob.twodimensionalgame.EntityType;
import org.hinoob.twodimensionalgame.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity{

    private Channel channel;
    public String displayName;
    public boolean authenticated = false;
    public List<Player> canSee = new ArrayList<>();

    public Player(Channel channel) {
        this.channel = channel;
        this.type = EntityType.PLAYER;
    }

    public void sendPacket(Packet packet) {
        channel.write(packet);
    }

    public void tick() {
        channel.flush();
    }

    public Channel getChannel() {
        return channel;
    }

}
