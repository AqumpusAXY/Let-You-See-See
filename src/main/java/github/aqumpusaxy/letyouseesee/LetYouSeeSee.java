package github.aqumpusaxy.letyouseesee;

import com.mojang.logging.LogUtils;
import github.aqumpusaxy.letyouseesee.common.Constants;
import github.aqumpusaxy.letyouseesee.network.ModNetworkHandler;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Constants.MODID)
public class LetYouSeeSee {
    public static final Logger LOGGER = LogUtils.getLogger();

    public LetYouSeeSee() {
        ModNetworkHandler.register();
    }
}
