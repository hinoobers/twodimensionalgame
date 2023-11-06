package org.hinoob.twodimensionalgame.server.entity;

import io.netty.channel.Channel;
import lombok.Getter;
import org.hinoob.twodimensionalgame.packet.Packet;

public class Player extends Entity{

    @Getter private Channel channel;

    public Player(Channel channel) {
        this.channel = channel;
    }

    public void sendPacket(Packet packet) {
        channel.writeAndFlush(packet);
    }
}
