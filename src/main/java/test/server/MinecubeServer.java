package test.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.network.ProtoMessages;
import test.networking.payload.MessagePayload;
import test.server.store.entity.Message;
import test.server.store.repository.MessageRepository;

public class MinecubeServer implements DedicatedServerModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinecubeServer.class);
    private final MessageRepository messageRepository = new MessageRepository();


    @Override
    public void onInitializeServer() {
        LOGGER.info("Registering MessagePayload types...");
        PayloadTypeRegistry.playS2C().register(MessagePayload.TYPE, MessagePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(MessagePayload.TYPE, MessagePayload.CODEC);
        LOGGER.info("MessagePayload types registered");

        ServerPlayNetworking.registerGlobalReceiver(MessagePayload.TYPE, (payload, context) -> {
            LOGGER.info("Packet received!");
            ProtoMessages.Message msg;
            try {
                msg = ProtoMessages.Message.parseFrom(payload.data());
            } catch (Exception e) {
                LOGGER.error("Failed to parse message", e);
                return;
            }
            String text = msg.getText();
            Message message = new Message();
            message.setUuid(context.player().getUUID());
            message.setText(text);
            messageRepository.save(message);
            LOGGER.info("Saved message from {}: {}", context.player().getName().getString(), text);
        });
    }
}
