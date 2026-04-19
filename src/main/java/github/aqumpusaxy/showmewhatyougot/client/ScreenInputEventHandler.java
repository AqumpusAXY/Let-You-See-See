package github.aqumpusaxy.showmewhatyougot.client;

import com.mojang.blaze3d.platform.InputConstants;
import github.aqumpusaxy.showmewhatyougot.compat.jei.JeiScreenInputHandler;
import github.aqumpusaxy.showmewhatyougot.lib.Constants;
import github.aqumpusaxy.showmewhatyougot.network.SMWYGNetworkManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
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
            if (screen instanceof AbstractContainerScreen<?> containerScreen) {
                handleContainerInput(containerScreen);
            }

            if (ModList.get().isLoaded("jei")) {
                JeiScreenInputHandler.getInstance().handleJeiInput();
            }
        }
    }

    private static void handleContainerInput(AbstractContainerScreen<?> containerScreen) {
        Slot hoveredSlot = containerScreen.getSlotUnderMouse();
        if (hoveredSlot == null) return;

        ItemStack itemStack = hoveredSlot.getItem();
        if (itemStack.isEmpty()) return;

        SMWYGNetworkManager.sendComponentToServer(itemStack.getDisplayName());
    }
}
