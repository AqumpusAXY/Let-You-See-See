package github.aqumpusaxy.showmewhatyougot.compat.jei;

import github.aqumpusaxy.showmewhatyougot.network.SMWYGNetworkManager;
import github.aqumpusaxy.showmewhatyougot.network.ShowItemPacket;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IBookmarkOverlay;
import mezz.jei.api.runtime.IIngredientListOverlay;
import mezz.jei.api.runtime.IJeiRuntime;

public class JeiScreenInputHandler {
    private static IJeiRuntime jeiRuntime;
    private static IIngredientListOverlay ingredientListOverlay;
    private static IBookmarkOverlay bookmarkOverlay;

    public static void setJeiRuntime(IJeiRuntime runtime) {
        jeiRuntime = runtime;
    }

    public static void init() {
        ingredientListOverlay = jeiRuntime.getIngredientListOverlay();
        bookmarkOverlay = jeiRuntime.getBookmarkOverlay();
    }

    public static void handleJeiInput() {
        if (ingredientListOverlay == null) return;
        ingredientListOverlay.getIngredientUnderMouse()
                .flatMap(ITypedIngredient::getItemStack)
                .ifPresent(itemStack -> SMWYGNetworkManager.INSTANCE.sendToServer(new ShowItemPacket(itemStack)));

        if (bookmarkOverlay == null) return;
        bookmarkOverlay.getIngredientUnderMouse()
                .flatMap(ITypedIngredient::getItemStack)
                .ifPresent(itemStack -> SMWYGNetworkManager.INSTANCE.sendToServer(new ShowItemPacket(itemStack)));

        //TODO:配方界面物品兼容, 兼容JEI的其他Type, 点击聊天栏中的物品添加到书签
    }
}
