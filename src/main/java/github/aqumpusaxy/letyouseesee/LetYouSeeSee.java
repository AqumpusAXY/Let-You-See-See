package github.aqumpusaxy.letyouseesee;

import com.mojang.logging.LogUtils;
import github.aqumpusaxy.letyouseesee.client.IngredientDetectorChain;
import github.aqumpusaxy.letyouseesee.client.VanillaIngredientDetector;
import github.aqumpusaxy.letyouseesee.common.Constants;
import github.aqumpusaxy.letyouseesee.network.ModNetworkHandler;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Constants.MODID)
public class LetYouSeeSee {
    public static final Logger LOGGER = LogUtils.getLogger();

    public LetYouSeeSee() {
        VanillaIngredientDetector.init();
        IngredientDetectorChain.INSTANCE.addDetector(VanillaIngredientDetector.getInstance());
        IngredientDetectorChain.INSTANCE.sortDetectors();

        ModNetworkHandler.register();
    }
}
