package github.aqumpusaxy.letyouseesee.compat.jei.command;

import com.mojang.brigadier.CommandDispatcher;
import github.aqumpusaxy.letyouseesee.common.Constants;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MODID, value = Dist.CLIENT)
public class ClientJeiCompatibleCommandRegistry {
    @SubscribeEvent
    public static void onClientCommandRegister(RegisterClientCommandsEvent event) {
        register(event.getDispatcher(), event.getBuildContext());
    }

    private static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext ctx) {
        dispatcher.register(Commands.literal("lyss")
                .then(Commands.literal("addJEIBookmark")
                        .then(Commands.literal("item_stack")
                                .then(Commands.argument("itemStack", ItemArgument.item(ctx))
                                        .executes(cmd -> {
                                            System.out.println(cmd.getArgument("itemStack", ItemInput.class).getItem());
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }
}
