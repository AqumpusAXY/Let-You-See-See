package github.aqumpusaxy.letyouseesee.client;

import github.aqumpusaxy.letyouseesee.api.IIngredientDetector;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;
import java.util.concurrent.CopyOnWriteArrayList;

public class IngredientDetectorChain {
    public static final IngredientDetectorChain INSTANCE = new IngredientDetectorChain();
    private final CopyOnWriteArrayList<IIngredientDetector> detectors = new CopyOnWriteArrayList<>();

    static {
        INSTANCE.addDetector(VanillaIngredientDetector.getInstance());
    }

    public void sortDetectors() {
        detectors.sort((d1, d2) -> d2.getPriority() - d1.getPriority());
    }

    public void addDetector(IIngredientDetector detector) {
        detectors.add(detector);
    }

    public @Nullable DetectedIngredient detect(Screen screen) {
        for (IIngredientDetector detector : detectors) {
            DetectedIngredient ingredient = detector.detect(screen);
            if (ingredient != null) return ingredient;
        }

        return null;
    }
}
