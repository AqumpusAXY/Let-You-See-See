package github.aqumpusaxy.letyouseesee.compat.jei;

import github.aqumpusaxy.letyouseesee.api.IIngredientDetector;
import github.aqumpusaxy.letyouseesee.client.DetectedIngredient;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.gui.input.IClickableIngredientInternal;
import mezz.jei.gui.input.MouseUtil;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class JeiIngredientDetector implements IIngredientDetector {
    private static JeiIngredientDetector INSTANCE;

    private final CombinedJeiOverlay COMBINED_JEI_OVERLAY;

    private JeiIngredientDetector(IJeiRuntime runtime) {
        this.COMBINED_JEI_OVERLAY = new CombinedJeiOverlay(
                runtime.getIngredientListOverlay(),
                runtime.getBookmarkOverlay(),
                runtime.getRecipesGui()
        );
    }

    @Override
    public @Nullable DetectedIngredient detect(Screen screen) {
        ITypedIngredient<?> typedIngredient = COMBINED_JEI_OVERLAY.getIngredientUnderMouse(MouseUtil.getX(), MouseUtil.getY())
                .findFirst()
                .map(IClickableIngredientInternal::getTypedIngredient)
                .orElse(null);
        if (typedIngredient == null) return null;

        ResourceLocation id = getResourceLocationForIngredient(typedIngredient);
        String type = typedIngredient.getType().getUid();
        Optional<ItemStack> itemStack = typedIngredient.getItemStack();
        Component info = itemStack.map(ItemStack::getDisplayName)
                .orElseGet(() -> JeiTooltipGetter.getIngredientComponent(typedIngredient.getIngredient())
                );

        return new DetectedIngredient(
                id,
                type,
                info,
                itemStack
        );
    }

    @Override
    public int getPriority() {
        return 100;
    }

    public static void init(IJeiRuntime runtime) {
        INSTANCE = new JeiIngredientDetector(runtime);
    }

    public static IIngredientDetector getInstance() {
        if (INSTANCE == null) throw new IllegalStateException("JeiIngredientDetector is not initialized");

        return INSTANCE;
    }

    private static <T> ResourceLocation getResourceLocationForIngredient(ITypedIngredient<T> typedIngredient) {
        T ingredient = typedIngredient.getIngredient();
        IIngredientType<T> ingredientType = typedIngredient.getType();
        IIngredientHelper<T> ingredientHelper = ModJeiPlugin.getIngredientManager().getIngredientHelper(ingredientType);

        return ingredientHelper.getResourceLocation(ingredient);
    }
}
