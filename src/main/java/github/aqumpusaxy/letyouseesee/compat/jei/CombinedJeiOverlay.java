package github.aqumpusaxy.letyouseesee.compat.jei;

import mezz.jei.api.runtime.IBookmarkOverlay;
import mezz.jei.api.runtime.IIngredientListOverlay;
import mezz.jei.api.runtime.IRecipesGui;
import mezz.jei.gui.input.IClickableIngredientInternal;
import mezz.jei.gui.input.IRecipeFocusSource;

import java.util.List;
import java.util.stream.Stream;

public class CombinedJeiOverlay {
    private final IIngredientListOverlay INGREDIENT_LIST_OVERLAY;
    private final IBookmarkOverlay BOOKMARK_OVERLAY;
    private final IRecipesGui RECIPES_GUI;

    private final List<IRecipeFocusSource> RECIPE_FOCUS_SOURCES;

    public CombinedJeiOverlay(IIngredientListOverlay ingredientListOverlay, IBookmarkOverlay bookmarkOverlay, IRecipesGui recipesGui) {
        INGREDIENT_LIST_OVERLAY = ingredientListOverlay;
        BOOKMARK_OVERLAY = bookmarkOverlay;
        RECIPES_GUI = recipesGui;

        RECIPE_FOCUS_SOURCES = List.of(
                (IRecipeFocusSource) INGREDIENT_LIST_OVERLAY,
                (IRecipeFocusSource) BOOKMARK_OVERLAY,
                (IRecipeFocusSource) RECIPES_GUI
        );
    }

    public Stream<IClickableIngredientInternal<?>> getIngredientUnderMouse(double mouseX, double mouseY) {
        if (INGREDIENT_LIST_OVERLAY == null) return Stream.empty();
        if (BOOKMARK_OVERLAY == null) return Stream.empty();
        if (RECIPES_GUI == null) return Stream.empty();

        return RECIPE_FOCUS_SOURCES.stream()
                .flatMap(source -> source.getIngredientUnderMouse(mouseX, mouseY));
    }
}
