package github.aqumpusaxy.letyouseesee.api;

import github.aqumpusaxy.letyouseesee.client.DetectedIngredient;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;

public interface IIngredientDetector {
    @Nullable
    DetectedIngredient detect(Screen screen);

    /**
     * The higher the priority, the earlier the detector will be called.
     */
    int getPriority();
}
