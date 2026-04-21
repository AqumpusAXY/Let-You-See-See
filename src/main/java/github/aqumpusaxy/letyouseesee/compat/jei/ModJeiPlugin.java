package github.aqumpusaxy.letyouseesee.compat.jei;

import github.aqumpusaxy.letyouseesee.client.IngredientDetectorChain;
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
        if (jeiRuntime == null) throw new IllegalStateException("JeiRuntime is not available");

        return jeiRuntime;
    }

    public static IIngredientManager getIngredientManager() {
        if (ingredientManager == null) throw new IllegalStateException("IngredientManager is not available");

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

        JeiIngredientDetector.init(runtime);
        IngredientDetectorChain.INSTANCE.addDetector(JeiIngredientDetector.getInstance());
        IngredientDetectorChain.INSTANCE.sortDetectors();

        ingredientManager.getRegisteredIngredientTypes()
                .forEach(ingredientType -> JeiTooltipGetter.addRenderer(
                        ingredientType,
                        ingredientManager.getIngredientRenderer(ingredientType)
                        )
                );
    }
}
