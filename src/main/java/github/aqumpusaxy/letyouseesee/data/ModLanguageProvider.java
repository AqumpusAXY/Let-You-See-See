package github.aqumpusaxy.letyouseesee.data;

import github.aqumpusaxy.letyouseesee.common.Constants;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    private final String LOCALE;

    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
        this.LOCALE = locale;
    }

    @Override
    protected void addTranslations() {
        if (LOCALE.equals("zh_cn")) {
            addChineseTranslations();
        } else if (LOCALE.equals("en_us")) {
            addEnglishTranslations();
        }
    }

    private void addChineseTranslations() {
        add(Constants.KEY_CATEGORY_NAME, "让你看看");

        add(Constants.SEND_HOVER_ITEM_KEY_NAME, "发送物品至聊天栏");
    }

    private void addEnglishTranslations() {
        add(Constants.KEY_CATEGORY_NAME, "Let You See See");

        add(Constants.SEND_HOVER_ITEM_KEY_NAME, "Send Item To Chat");
    }
}
