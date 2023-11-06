package org.hinoob.twodimensionalgame.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.PacketTypes;
import org.hinoob.twodimensionalgame.server.entity.Player;

public class ServerNettyHandler extends ChannelDuplexHandler {

    private final Player player;

    public ServerNettyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ModifiedBuf buf = new ModifiedBuf((ByteBuf) msg);
        System.out.println("received=" + buf.getLength());
        int packetID = buf.readVarInt();
        Packet packet = PacketTypes.clientToServer(packetID);
        if(packet == null) {
            Server.getLogger().info("Invalid packet received, id=" + packetID);
            return;
        }
        Server.getLogger().info("Packet with id " + packetID + " received");
        packet.read(buf);
        Server.getInstance().packetHandler.handlePacket(player, packet);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if(msg instanceof Packet) {
            ModifiedBuf empty = new ModifiedBuf(Unpooled.buffer());
            empty.writeVarInt(((Packet)msg).getID());
            ((Packet)msg).write(empty);
            System.out.println("WRITING AS SERVER len=" + empty.getLength());
            super.write(ctx, empty.getEditedBuf(), promise);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        cause.printStackTrace();

        Server.getInstance().entityManager.removeEntity(player.entityId);
    }
}