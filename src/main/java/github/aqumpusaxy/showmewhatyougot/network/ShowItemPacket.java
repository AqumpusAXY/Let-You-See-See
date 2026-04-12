package github.aqumpusaxy.showmewhatyougot.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ShowItemPacket {
    private final ItemStack ITEM_STACK;

    public ShowItemPacket(ItemStack itemStack) {
        this.ITEM_STACK = itemStack;
    }

    public ShowItemPacket(FriendlyByteBuf buf) {
        this.ITEM_STACK = buf.readItem();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeItem(ITEM_STACK);
    }

    public static void handle(ShowItemPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            if (sender == null) return;

            Component itemInfo = msg.ITEM_STACK.getDisplayName();
            MutableComponent chatMessage = MutableComponent.create(ComponentContents.EMPTY)
                    .append("<")
                    .append(sender.getDisplayName())
                    .append("> ")
                    .append(itemInfo);

            MinecraftServer server = sender.getServer();
            if (server == null) return;

            server.getPlayerList().getPlayers().forEach(serverPlayer ->
                    serverPlayer.sendSystemMessage(chatMessage)
            );
        });

        ctx.get().setPacketHandled(true);
    }
}
