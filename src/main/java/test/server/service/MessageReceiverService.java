package test.server.service;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.network.ProtoMessages;
import test.networking.payload.MessagePayload;
import test.server.MinecubeServer;
import test.server.store.entity.Message;
import test.server.store.repository.MessageRepository;

public class MessageReceiverService implements ServerPlayNetworking.PlayPayloadHandler<MessagePayload> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiverService.class);

    private static MessageReceiverService instance;

    private MessageReceiverService() {}

    public static MessageReceiverService getInstance() {
        return instance == null ? instance = new MessageReceiverService() : instance;
    }

    @Override
    public void receive(MessagePayload payload, ServerPlayNetworking.Context context) {
        LOGGER.info("Packet received!");
        ProtoMessages.Message msg;
        try {
            msg = ProtoMessages.Message.parseFrom(payload.data());
        } catch (Exception e) {
            LOGGER.error("Failed to parse message", e);
            return;
        }
        String text = msg.getText();

        MessageRepository.getInstance().save(Message.builder()
                .uuid(context.player().getUUID())
                .text(text).build());
        LOGGER.info("Saved message from {}: {}", context.player().getName().getString(), text);
    }
}
