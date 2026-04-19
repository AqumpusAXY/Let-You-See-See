package github.aqumpusaxy.letyouseesee.compat.jei;

import github.aqumpusaxy.letyouseesee.common.Constants;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    private static IJeiRuntime jeiRuntime;
    private static IIngredientManager ingredientManager;

    public static IJeiRuntime getJeiRuntime() {
        return jeiRuntime;
    }

    public static IIngredientManager getIngredientManager() {
        return ingredientManager;
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Constants.MODID, "jei_plugin");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        jeiRuntime = runtime;
        ingredientManager = runtime.getIngredientManager();

        JeiScreenInputHandler.init(runtime);
        ingredientManager.getRegisteredIngredientTypes()
                .forEach(ingredientType -> JeiTooltipGetter.addRenderer(
                        ingredientType,
                        ingredientManager.getIngredientRenderer(ingredientType)
                        )
                );
    }
}
