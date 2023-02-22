package com.v2lenkagamine.lensrtsgns.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.v2lenkagamine.lensrtsgns.parts.MaterialBase;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.v2lenkagamine.lensrtsgns.LensRTSGNS.MOD_ID;
import static com.v2lenkagamine.lensrtsgns.parts.MaterialBase.MATERIAL_BASE_CODEC;


public abstract class LensMaterialProvider implements DataProvider {
    protected PackOutput.PathProvider pathProvider;
    protected final FabricDataOutput output;

    protected LensMaterialProvider(FabricDataOutput output) {
        this.output = output;
        this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK,"materials");
    }

    protected abstract void giveContext(ContextExporter exporter);

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        final Map<ResourceLocation,MaterialBase> theMap = new HashMap<>();
        giveContext((s,context)->{
           ResourceLocation rl = new ResourceLocation(MOD_ID,s);
           theMap.put(rl,context);
        });

        return CompletableFuture.allOf(theMap.entrySet().stream().map(
            entry -> {
                Path path = this.pathProvider.json(entry.getKey());
                JsonElement element = MATERIAL_BASE_CODEC.encodeStart(JsonOps.INSTANCE,entry.getValue())
                        .getOrThrow(false,s -> LOGGER.error("{} provider failed to encode {}: {}",this.getName(),path,s));
                return DataProvider.saveStable(cachedOutput,element,path);
            }
        ).toArray(CompletableFuture[]::new));
    }

    @FunctionalInterface
    interface ContextExporter {
        void export(String name,MaterialBase material);
    }

    @Override
    public String getName() {
        return "LensMaterialsProvider";
    }
}
