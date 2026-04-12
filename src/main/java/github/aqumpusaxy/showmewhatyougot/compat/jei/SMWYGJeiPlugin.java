package github.aqumpusaxy.showmewhatyougot.compat.jei;

import github.aqumpusaxy.showmewhatyougot.lib.Constants;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
@JeiPlugin
public class SMWYGJeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Constants.MODID, "jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        JeiScreenInputHandler.setJeiRuntime(runtime);
        JeiScreenInputHandler.init();
    }
}
