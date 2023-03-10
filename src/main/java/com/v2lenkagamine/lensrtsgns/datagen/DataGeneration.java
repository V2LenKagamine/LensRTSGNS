package com.v2lenkagamine.lensrtsgns.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        fabricDataGenerator.createPack().addProvider(LensModelGenerator::new);
        fabricDataGenerator.createPack().addProvider(LensLangGenerator::new);
        fabricDataGenerator.createPack().addProvider(LensMaterialGenerator::new);

    }
}
