package test.networking.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MessagePayload(byte[] data) implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("minecube", "message");
    public static final Type<MessagePayload> TYPE = new Type<>(ID);

    public static final StreamCodec<ByteBuf, MessagePayload> CODEC = StreamCodec.of(
        (buf, payload) -> buf.writeBytes(payload.data),
        buf -> {
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            return new MessagePayload(data);
        }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
