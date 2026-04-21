package github.aqumpusaxy.letyouseesee.client;

import github.aqumpusaxy.letyouseesee.api.IIngredientDetector;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class VanillaIngredientDetector implements IIngredientDetector {
    private static final VanillaIngredientDetector INSTANCE = new VanillaIngredientDetector();

    private VanillaIngredientDetector() {}

    @Override
    public @Nullable DetectedIngredient detect(Screen screen) {
        if (!(screen instanceof AbstractContainerScreen<?> containerScreen)) return null;

        Slot hoveredSlot = containerScreen.getSlotUnderMouse();
        if (hoveredSlot == null) return null;

        ItemStack itemStack = hoveredSlot.getItem();
        if (itemStack.isEmpty()) return null;

        return new DetectedIngredient(
                ForgeRegistries.ITEMS.getKey(itemStack.getItem()),
                "item_stack",
                itemStack.getDisplayName(),
                Optional.of(itemStack)
        );
    }

    @Override
    public int getPriority() {
        return 0;
    }

    public static VanillaIngredientDetector getInstance() {
        return INSTANCE;
    }
}
