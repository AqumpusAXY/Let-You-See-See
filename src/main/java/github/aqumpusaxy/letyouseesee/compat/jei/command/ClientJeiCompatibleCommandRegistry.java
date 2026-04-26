package github.aqumpusaxy.letyouseesee.compat.jei.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import github.aqumpusaxy.letyouseesee.common.Constants;
import net.minecraft.commands.Commands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MODID, value = Dist.CLIENT)
public class ClientJeiCompatibleCommandRegistry {
    @SubscribeEvent
    public static void onClientCommandRegister(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("lyss")
            .then(Commands.literal("addBookmark")
                .then(Commands.argument("text", StringArgumentType.string())
                    .executes(ctx -> {
                        String text = ctx.getArgument("text", String.class);
                        var ingredient = github.aqumpusaxy.letyouseesee.client.IngredientUtil.fromString(text);
                        if (ingredient != null) {
                        github.aqumpusaxy.letyouseesee.client.JeiBookmarkHelper.addBookmark(ingredient);
                        ctx.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal("已添加到JEI书签"), false);
                        return 1;
                        } else {
                        ctx.getSource().sendFailure(net.minecraft.network.chat.Component.literal("无法识别该物品"));
                        return 0;
                        }
                    })
                )
            )
        );
    }
}
