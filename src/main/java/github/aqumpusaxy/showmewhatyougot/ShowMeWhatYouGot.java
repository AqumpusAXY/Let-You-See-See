package github.aqumpusaxy.showmewhatyougot;

import com.mojang.logging.LogUtils;
import github.aqumpusaxy.showmewhatyougot.lib.Constants;
import github.aqumpusaxy.showmewhatyougot.network.SMWYGNetworkManager;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Constants.MODID)
public class ShowMeWhatYouGot {
    public static final Logger LOGGER = LogUtils.getLogger();

    public ShowMeWhatYouGot() {
        SMWYGNetworkManager.register();
    }
}
