package test.client.screen;

import com.google.protobuf.Message;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import test.network.ProtoMessages;
import test.networking.payload.MessagePayload;

public class MessageScreen extends Screen {
    private Button sendButton;
    private EditBox textField;

    private final int sendButtonHeight = 20;
    private final int sendButtonWidth = 120;

    private final int textFieldHeight = 20;
    private final int textFieldWidth = 200;

    private static final ResourceLocation MESSAGE_PACKET_ID = ResourceLocation.fromNamespaceAndPath("minecube", "message");
    private final Minecraft minecraft = Minecraft.getInstance();
    private final ToastManager toastManager = minecraft.getToastManager();


    public MessageScreen() {
        super(Component.literal("Message Screen"));
    }

    @Override
    protected void init() {
        textField = new EditBox(this.font,
                (this.width-textFieldWidth)/2, (this.height-textFieldHeight)/2-15,
                textFieldWidth, textFieldHeight,
                Component.literal("Type here"));
        sendButton = Button.builder(Component.literal("Send Message"), (btn) -> {
                    toastManager.addToast(SystemToast.multiline(
                            minecraft,
                            SystemToast.SystemToastId.PERIODIC_NOTIFICATION,
                            Component.literal("Message sent!"),
                            Component.literal("Thank you for using this mod!")
                    ));

                    ProtoMessages.Message message = ProtoMessages.Message.newBuilder()
                            .setText(textField.getValue())
                            .build();
                    MessagePayload payload = new MessagePayload(message.toByteArray());
                    ClientPlayNetworking.send(payload);
                })
                .bounds((this.width-sendButtonWidth)/2, (this.height-sendButtonHeight)/2+15,
                        sendButtonWidth, sendButtonHeight)
                .build();


        this.addRenderableWidget(sendButton);
        this.addRenderableWidget(textField);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.drawString(this.font, "Send Message", (this.width)/2-this.font.width("Send Message")/2, 40 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
    }
}
