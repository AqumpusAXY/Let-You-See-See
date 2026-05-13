package github.aqumpusaxy.letyouseesee.compat.jei;

import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.common.gui.JeiTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Optional;

public class JeiTooltipGetter {
    @SuppressWarnings("removal")
    public static <T> Component getIngredientComponent(T ingredient) {
        Optional<IIngredientType<T>> ingredientType = ModJeiPlugin.getIngredientManager().getIngredientTypeChecked(ingredient);
        if (ingredientType.isEmpty()) return Component.empty();
        if (ingredientType.get().getUid().equals("item_stack")) {
            return ((ItemStack) ingredient).getDisplayName();
        }

        IIngredientRenderer<T> renderer = ModJeiPlugin.getIngredientManager().getIngredientRenderer(ingredientType.get());
        ITooltipBuilder tooltipBuilder = new JeiTooltip();
        renderer.getTooltip(tooltipBuilder, ingredient, TooltipFlag.NORMAL);

        return toStyledComponent(tooltipBuilder.toLegacyToComponents());
    }

    private static MutableComponent toStyledComponent(List<Component> components) {
        if (components.isEmpty()) return Component.empty();

        Component name = Component.empty().append(components.get(0));
        MutableComponent display = ComponentUtils.wrapInSquareBrackets(name);

        display.withStyle(style ->
                style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, name))
        );

        return display;
    }
}
