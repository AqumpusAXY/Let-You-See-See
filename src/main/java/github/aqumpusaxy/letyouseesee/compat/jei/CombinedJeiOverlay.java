package github.aqumpusaxy.letyouseesee.compat.jei;

import mezz.jei.api.runtime.IBookmarkOverlay;
import mezz.jei.api.runtime.IIngredientListOverlay;
import mezz.jei.api.runtime.IRecipesGui;
import mezz.jei.gui.input.IClickableIngredientInternal;
import mezz.jei.gui.input.IRecipeFocusSource;

import java.util.List;
import java.util.stream.Stream;

public class CombinedJeiOverlay {
    private final List<IRecipeFocusSource> RECIPE_FOCUS_SOURCES;

    public CombinedJeiOverlay(IIngredientListOverlay ingredientListOverlay, IBookmarkOverlay bookmarkOverlay, IRecipesGui recipesGui) {
        RECIPE_FOCUS_SOURCES = Stream.of(ingredientListOverlay, bookmarkOverlay, recipesGui)
                .map(overlay -> (IRecipeFocusSource) overlay)
                .toList();
    }

    public Stream<IClickableIngredientInternal<?>> getIngredientUnderMouse(double mouseX, double mouseY) {
        return RECIPE_FOCUS_SOURCES.stream()
                .flatMap(source -> source.getIngredientUnderMouse(mouseX, mouseY));
    }
}
