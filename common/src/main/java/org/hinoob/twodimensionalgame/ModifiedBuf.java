package org.hinoob.twodimensionalgame;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class ModifiedBuf {

    private ByteBuf buf;

    public ModifiedBuf(ByteBuf buf) {
        this.buf = buf;
    }

    public int getLength() {
        return buf.readableBytes();
    }

    public ByteBuf getEditedBuf() {
        return buf;
    }

    public void writeString(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        buf.writeInt(bytes.length);
        for(byte b : bytes) {
            buf.writeByte(b);
        }
    }

    public String readString() {
        int len = buf.readInt();
        byte[] bytes = new byte[len];
        for(int i = 0; i < len; i++) {
            bytes[i] = buf.readByte();
        }
        return new String(bytes);
    }

    public void writeInt(int i){
        buf.writeInt(i);
    }

    public int readInt() {
        return buf.readInt();
    }

    public void writeVarInt(int value) {
        while (true) {
            if ((value & 0xFFFFFF80) == 0) {
                buf.writeByte(value);
                return;
            }
            buf.writeByte(value & 0x7F | 0x80);
            value >>>= 7;
        }
    }

    public int readVarInt() {
        int i = 0;
        int j = 0;
        while (true) {
            int k = buf.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
            if ((k & 0x80) != 128) {
                break;
            }
        }
        return i;
    }
}
