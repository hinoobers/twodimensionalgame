package org.hinoob.twodimensionalgame.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.Setter;
import org.hinoob.twodimensionalgame.server.entity.Player;
import org.hinoob.twodimensionalgame.server.manager.DatabaseManager;
import org.hinoob.twodimensionalgame.server.manager.EntityManager;
import org.hinoob.twodimensionalgame.ModifiedBuf;
import org.hinoob.twodimensionalgame.packet.Packet;
import org.hinoob.twodimensionalgame.packet.PacketTypes;
import org.hinoob.twodimensionalgame.server.manager.PacketHandler;

import java.util.logging.Logger;

@Getter
public class Server {

    @Setter
    @Getter private static Server instance;

    @Getter private static Logger logger = Logger.getLogger("TWODIMENSIONALGAME-SERVER");

    private EntityManager entityManager = new EntityManager();
    private PacketHandler packetHandler = new PacketHandler();
    private DatabaseManager databaseManager = new DatabaseManager();


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

                            ch.pipeline().addLast(new ServerNettyHandler(player));
                        }
                    });
            ChannelFuture future = bootstrap.bind(6969).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
