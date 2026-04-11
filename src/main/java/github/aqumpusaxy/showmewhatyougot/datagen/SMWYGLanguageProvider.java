package github.aqumpusaxy.showmewhatyougot.datagen;

import github.aqumpusaxy.showmewhatyougot.lib.Constants;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class SMWYGLanguageProvider extends LanguageProvider {
    private final String LOCALE;

    public SMWYGLanguageProvider(PackOutput output, String modid, String locale) {
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
        //TODO: 起中文名
        add(Constants.KEY_CATEGORY_NAME, "Show Me What You Got");

        add(Constants.SEND_HOVER_ITEM_KEY_NAME, "发送物品至聊天栏");
    }

    private void addEnglishTranslations() {
        add(Constants.KEY_CATEGORY_NAME, "Show Me What You Got");

        add(Constants.SEND_HOVER_ITEM_KEY_NAME, "Send Item To Chat");
    }
}
