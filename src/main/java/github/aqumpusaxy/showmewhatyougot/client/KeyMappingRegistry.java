package github.aqumpusaxy.showmewhatyougot.client;

import com.mojang.blaze3d.platform.InputConstants;
import github.aqumpusaxy.showmewhatyougot.lib.Constants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyMappingRegistry {
    public static final Lazy<KeyMapping> SEND_HOVER_ITEM = Lazy.of(() -> new KeyMapping(
            Constants.SEND_HOVER_ITEM_KEY_NAME,
            KeyConflictContext.GUI,
            InputConstants.UNKNOWN,
            Constants.KEY_CATEGORY_NAME
    ));

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SEND_HOVER_ITEM.get());
    }
}
