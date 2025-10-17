package test.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import test.networking.payload.MessagePayload;
import test.client.screen.MessageScreen;

public class MinecubeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {;
            client.execute(()->client.setScreen(new MessageScreen()));
        });
        PayloadTypeRegistry.playS2C().register(MessagePayload.TYPE, MessagePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(MessagePayload.TYPE, MessagePayload.CODEC);
    }
}
