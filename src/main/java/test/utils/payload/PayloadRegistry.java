package test.utils.payload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.networking.payload.MessagePayload;

public class PayloadRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayloadRegistry.class);

    public static void registerAll() {
        LOGGER.info("Registering MessagePayload types...");
        PayloadTypeRegistry.playS2C().register(MessagePayload.TYPE, MessagePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(MessagePayload.TYPE, MessagePayload.CODEC);
        LOGGER.info("MessagePayload types registered");
    }
}
