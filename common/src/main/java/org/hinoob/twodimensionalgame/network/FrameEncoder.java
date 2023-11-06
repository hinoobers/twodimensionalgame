package org.hinoob.twodimensionalgame.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.hinoob.twodimensionalgame.ModifiedBuf;

import java.util.List;

public class FrameEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) {
        // Varints are never longer than 5 bytes
        ByteBuf lengthBuf = ctx.alloc().heapBuffer(5);
        new ModifiedBuf(lengthBuf).writeVarInt(buf.readableBytes());

        list.add(lengthBuf);
        list.add(buf.retain());
    }
}
