package github.aqumpusaxy.letyouseesee.client;

import com.mojang.blaze3d.platform.InputConstants;
import github.aqumpusaxy.letyouseesee.common.Constants;
import github.aqumpusaxy.letyouseesee.network.ModNetworkHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MODID, value = Dist.CLIENT)
public class ScreenInputEventHandler {
    private static final KeyMapping SEND_HOVER_ITEM_KEY = KeyMappingRegistry.SEND_HOVER_ITEM.get();

    @SubscribeEvent
    public static void handleKeyInput(ScreenEvent.KeyPressed.Post event) {
        int keyCode = event.getKeyCode();
        int scanCode = event.getScanCode();

        if (SEND_HOVER_ITEM_KEY.isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return;

            Screen screen = event.getScreen();

            DetectedIngredient ingredient = IngredientDetectorChain.INSTANCE.detect(screen);
            if (ingredient == null) return;

            ModNetworkHandler.sendComponentToServer(ingredient.info());
        }
    }
}
