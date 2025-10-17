package test.client.events.click;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import test.client.MinecubeKeybindings;
import test.client.screen.MessageScreen;

public class MinecubeOpenScreenKeyEvent implements KeyEvent {
    @Override
    public void consumeClick(Minecraft client) {
        client.setScreen(new MessageScreen());
    }
}
