package com.v2lenkagamine.lensrtsgns.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

import static com.v2lenkagamine.lensrtsgns.LensRTSGNS.TEST_ITEM;

public class LensModelGenerator extends FabricModelProvider {

    public LensModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(TEST_ITEM, ModelTemplates.FLAT_ITEM);
    }
}
