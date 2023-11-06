package org.hinoob.twodimensionalgame.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.flow.FlowControlHandler;
import org.hinoob.twodimensionalgame.network.FrameDecoder;
import org.hinoob.twodimensionalgame.network.FrameEncoder;
import org.hinoob.twodimensionalgame.server.entity.Player;
import org.hinoob.twodimensionalgame.server.manager.DatabaseManager;
import org.hinoob.twodimensionalgame.server.manager.EntityManager;
import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.PacketTypes;
import org.hinoob.twodimensionalgame.server.manager.PacketHandler;
import org.hinoob.twodimensionalgame.server.manager.WorldManager;

import java.util.logging.Logger;

public class Server {

    private static Server instance;

    private static Logger logger = Logger.getLogger("TWODIMENSIONALGAME-SERVER");

    public final EntityManager entityManager = new EntityManager();
    public final PacketHandler packetHandler = new PacketHandler();
    public final DatabaseManager databaseManager = new DatabaseManager();
    public final WorldManager worldManager = new WorldManager();


    public static void main(String[] args) {
        instance = new Server();
        instance.start();
    }

    public void start() {
        databaseManager.connect();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            logger.info("Player connected!");
                            Player player = new Player(ch);
                            entityManager.addEntity(player);

                            ch.pipeline().addLast("frame-decoder", new FrameDecoder());
                            ch.pipeline().addLast("length-encoder", new FrameEncoder());
                            ch.pipeline().addLast(new FlowControlHandler());
                            ch.pipeline().addLast(new ServerNettyHandler(player));
                        }
                    });
            ChannelFuture future = bootstrap.bind(6969).sync();
            future.channel().closeFuture().sync();
            while(true) {
                entityManager.getPlayers().forEach(e -> e.tick());
                try {
                    Thread.sleep(15L);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static Server getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return logger;
    }

}
