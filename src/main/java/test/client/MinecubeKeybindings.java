package test.client;


import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;


public class MinecubeKeybindings {
    public static final KeyMapping openMessageScreenKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.minecube.open_message_screen",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "category.minecube.keys"
    ));
}
