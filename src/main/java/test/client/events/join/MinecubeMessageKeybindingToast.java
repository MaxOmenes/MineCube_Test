package test.client.events.join;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.gui.components.toasts.TutorialToast;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;

public class MinecubeMessageKeybindingToast implements ClientPlayConnectionEvents.Join {
    private final Minecraft minecraft = Minecraft.getInstance();
    private final ToastManager toastManager = minecraft.getToastManager();
    @Override
    public void onPlayReady(ClientPacketListener handler, PacketSender sender, Minecraft client) {
//        toastManager.addToast(
//                new TutorialToast(
//                        client.font,
//                        TutorialToast.Icons.RECIPE_BOOK,
//                        Component.literal("Message Keybinding Activated!"),
//                        Component.literal("Press 'M' to open the message screen."),
//                        false
//                )
//        );
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                client.execute(() -> {
                    client.getToastManager().addToast(
                            SystemToast.multiline(
                                    minecraft,
                                    SystemToast.SystemToastId.PERIODIC_NOTIFICATION,
                                    Component.literal("Message Keybinding Activated!"),
                                    Component.literal("Press 'M' to open the message screen.")
                            )
                    );
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
