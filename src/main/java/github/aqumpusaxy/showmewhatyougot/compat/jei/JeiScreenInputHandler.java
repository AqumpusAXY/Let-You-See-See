package github.aqumpusaxy.showmewhatyougot.compat.jei;

import github.aqumpusaxy.showmewhatyougot.network.SMWYGNetworkManager;
import github.aqumpusaxy.showmewhatyougot.network.ShowItemPacket;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class JeiScreenInputHandler {
    private static IJeiRuntime jeiRuntime;
    private static IIngredientListOverlay ingredientListOverlay;
    private static IBookmarkOverlay bookmarkOverlay;
    private static IRecipesGui recipesGui;
    private static IIngredientManager ingredientManager;
    private static Collection<IIngredientType<?>> ingredientTypes;

    public static void setJeiRuntime(IJeiRuntime runtime) {
        jeiRuntime = runtime;
    }

    public static void init() {
        ingredientListOverlay = jeiRuntime.getIngredientListOverlay();
        bookmarkOverlay = jeiRuntime.getBookmarkOverlay();
        recipesGui = jeiRuntime.getRecipesGui();
        ingredientManager = jeiRuntime.getIngredientManager();
        ingredientTypes = ingredientManager.getRegisteredIngredientTypes();
    }

    public static void handleJeiInput() {
        if (ingredientListOverlay != null) {
            ingredientListOverlay.getIngredientUnderMouse()
                    .flatMap(ITypedIngredient::getItemStack)
                    .ifPresent(itemStack -> SMWYGNetworkManager.INSTANCE.sendToServer(new ShowItemPacket(itemStack)));

        }

        if (bookmarkOverlay != null) {
            bookmarkOverlay.getIngredientUnderMouse()
                    .flatMap(ITypedIngredient::getItemStack)
                    .ifPresent(itemStack -> SMWYGNetworkManager.INSTANCE.sendToServer(new ShowItemPacket(itemStack)));

        }

        if (recipesGui != null) {
            recipesGui.getIngredientUnderMouse(VanillaTypes.ITEM_STACK)
                    .ifPresent(itemStack -> SMWYGNetworkManager.INSTANCE.sendToServer(new ShowItemPacket(itemStack)));
        }

        //TODO:兼容JEI的其他Type, 点击聊天栏中的物品添加到书签
    }
}
