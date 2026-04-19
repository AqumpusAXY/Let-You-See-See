package github.aqumpusaxy.letyouseesee.network;

import github.aqumpusaxy.letyouseesee.common.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    private static int packetId = 0;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(Constants.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.registerMessage(
                packetId++,
                ShowItemPacket.class,
                ShowItemPacket::encode,
                ShowItemPacket::decode,
                ShowItemPacket::handle
        );
    }

    public static void sendComponentToServer(Component component) {
        if (component.equals(Component.empty())) return;
        INSTANCE.sendToServer(new ShowItemPacket(component));
    }
}
