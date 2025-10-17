package test.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.network.ProtoMessages;
import test.networking.payload.MessagePayload;
import test.server.service.MessageReceiverService;
import test.server.store.entity.Message;
import test.server.store.repository.MessageRepository;
import test.utils.payload.PayloadRegistry;

public class MinecubeServer implements DedicatedServerModInitializer {
    private final MessageReceiverService messageReceiverService = MessageReceiverService.getInstance();

    @Override
    public void onInitializeServer() {
        PayloadRegistry.registerAll();
        ServerPlayNetworking.registerGlobalReceiver(MessagePayload.TYPE, messageReceiverService);
    }
}
