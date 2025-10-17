package test.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import test.client.events.click.MinecubeOpenScreenKeyEvent;
import test.client.events.join.MinecubeMessageKeybindingToast;
import test.client.screen.MessageScreen;
import test.utils.payload.PayloadRegistry;

public class MinecubeClient implements ClientModInitializer {

    public static KeyMapping OPEN_MESSAGE_SCREEN_KEY;

    @Override
    public void onInitializeClient() {
        PayloadRegistry.registerAll();

        OPEN_MESSAGE_SCREEN_KEY = MinecubeKeybindings.openMessageScreenKey;

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_MESSAGE_SCREEN_KEY.consumeClick()) {
                new MinecubeOpenScreenKeyEvent().consumeClick(client);
            }
        });

        ClientPlayConnectionEvents.JOIN.register(new MinecubeMessageKeybindingToast());
    }
}
