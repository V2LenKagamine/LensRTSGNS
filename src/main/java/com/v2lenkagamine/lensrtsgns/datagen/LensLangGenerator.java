package com.v2lenkagamine.lensrtsgns.datagen;

import com.v2lenkagamine.lensrtsgns.common.items.Test_Item;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static com.v2lenkagamine.lensrtsgns.LensRTSGNS.*;

public class LensLangGenerator extends FabricLanguageProvider {
    public LensLangGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(TEST_ITEM,"Testing Item");
        translationBuilder.add(GUN_BASE_ITEM,"Modular Gun");
        translationBuilder.add(AMMO_BASE_ITEM,"Modular Ammo");
    }
}
