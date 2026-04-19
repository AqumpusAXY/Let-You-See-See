package github.aqumpusaxy.letyouseesee.compat.jei;

import github.aqumpusaxy.letyouseesee.network.ModNetworkHandler;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.gui.input.IClickableIngredientInternal;
import mezz.jei.gui.input.MouseUtil;

public class JeiScreenInputHandler {
    private static JeiScreenInputHandler INSTANCE;

    private final CombinedJeiOverlay combinedJeiOverlay;

    private JeiScreenInputHandler(IJeiRuntime runtime) {
        combinedJeiOverlay = new CombinedJeiOverlay(
                runtime.getIngredientListOverlay(),
                runtime.getBookmarkOverlay(),
                runtime.getRecipesGui()
        );
    }

    public void handleJeiInput() {
       combinedJeiOverlay.getIngredientUnderMouse(MouseUtil.getX(), MouseUtil.getY())
               .findFirst()
               .map(IClickableIngredientInternal::getTypedIngredient)
               .map(ITypedIngredient::getIngredient)
               .ifPresent(ingredient ->
                       ModNetworkHandler.sendComponentToServer(JeiTooltipGetter.getIngredientComponent(ingredient))
               );
        //TODO:点击聊天栏中的物品添加到书签
    }

    public static void init(IJeiRuntime runtime) {
        INSTANCE = new JeiScreenInputHandler(runtime);
    }

    public static JeiScreenInputHandler getInstance() {
        return INSTANCE;
    }
}
