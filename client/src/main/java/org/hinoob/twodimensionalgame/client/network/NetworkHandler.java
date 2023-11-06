package org.hinoob.twodimensionalgame.client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.client.TwodimensionalGame;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.PacketTypes;

public class NetworkHandler {

    private Channel ourChannel;

    public void connect() throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            TwodimensionalGame.getInstance().getWindow().add(TwodimensionalGame.getInstance().getAuthGui());
                            TwodimensionalGame.getInstance().getWindow().setResizable(false);
                            TwodimensionalGame.getInstance().getWindow().pack();
                            TwodimensionalGame.getInstance().getWindow().setLocationRelativeTo(null);
                            TwodimensionalGame.getInstance().getWindow().setVisible(true);

                            NetworkHandler.this.ourChannel = ch;
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("localhost", 6969).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    static class EchoClientHandler extends ChannelDuplexHandler {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {

        }
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ModifiedBuf buf = new ModifiedBuf((ByteBuf) msg);
            System.out.println("received=" + buf.getLength());
            int packetID = buf.readInt();
            Packet packet = PacketTypes.serverToClient(packetID);
            if(packet == null) {
                TwodimensionalGame.getLogger().info("Invalid packet received, id=" + packetID);
                return;
            }
            TwodimensionalGame.getLogger().info("Packet with id " + packetID + " received");
            packet.read(buf);
            TwodimensionalGame.getInstance().getPacketHandler().handlePacket(packet);
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            if(msg instanceof Packet) {
                System.out.println("Writing");
                ModifiedBuf empty = new ModifiedBuf(Unpooled.buffer());
                empty.writeInt(((Packet)msg).getID());
                ((Packet)msg).write(empty);
                System.out.println("Sent, length=" + empty.getLength());
                super.write(ctx, empty.getEditedBuf(), promise);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    public void sendPacket(Packet packet) {
        ourChannel.writeAndFlush(packet);
    }
}
