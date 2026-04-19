package github.aqumpusaxy.showmewhatyougot.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ShowItemPacket {
    private final Component COMPONENT;

    public ShowItemPacket(Component component) {
        this.COMPONENT = component;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeComponent(COMPONENT);
    }

    public static ShowItemPacket decode(FriendlyByteBuf buf) {
        return new ShowItemPacket(buf.readComponent());
    }

    public static void handle(ShowItemPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender == null) return;

            MutableComponent chatMessage = MutableComponent.create(ComponentContents.EMPTY)
                    .append("<")
                    .append(sender.getDisplayName())
                    .append("> ")
                    .append(msg.COMPONENT);

            MinecraftServer server = sender.getServer();
            if (server == null) return;

            server.getPlayerList().getPlayers().forEach(serverPlayer ->
                    serverPlayer.sendSystemMessage(chatMessage)
            );
        });

        ctx.get().setPacketHandled(true);
    }
}
