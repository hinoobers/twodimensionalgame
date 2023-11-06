package org.hinoob.twodimensionalgame.server.entity;

import io.netty.channel.Channel;
import org.hinoob.twodimensionalgame.packet.Packet;

public class Player extends Entity{

    private Channel channel;

    public Player(Channel channel) {
        this.channel = channel;
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
