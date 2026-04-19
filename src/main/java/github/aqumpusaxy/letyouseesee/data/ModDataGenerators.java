package github.aqumpusaxy.letyouseesee.data;

import github.aqumpusaxy.letyouseesee.common.Constants;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
    private static final Set<String> LANG_KEYS = Set.of(
            "en_us",
            "zh_cn"
    );

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();

        for (String langKey : LANG_KEYS) {
            event.getGenerator().addProvider(event.includeClient(), new ModLanguageProvider(
                    output,
                    Constants.MODID,
                    langKey
            ));
        }
    }
}
