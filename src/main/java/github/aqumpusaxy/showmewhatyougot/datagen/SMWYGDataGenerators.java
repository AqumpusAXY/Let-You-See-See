package github.aqumpusaxy.showmewhatyougot.datagen;

import github.aqumpusaxy.showmewhatyougot.lib.Constants;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMWYGDataGenerators {
    private static final Set<String> LANG_KEYS = Set.of(
            "en_us",
            "zh_cn"
    );

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();

        for (String langKey : LANG_KEYS) {
            event.getGenerator().addProvider(event.includeClient(), new SMWYGLanguageProvider(
                    output,
                    Constants.MODID,
                    langKey
            ));
        }
    }
}
