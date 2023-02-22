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
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.v2lenkagamine.lensrtsgns.parts.MaterialBase.MATERIAL_BASE_CODEC;


public class LensMaterialProvider implements DataProvider {
    protected PackOutput.PathProvider pathProvider;
    protected final FabricDataOutput output;
    protected final Map<ResourceLocation, MaterialBase> theMap;

    protected LensMaterialProvider(FabricDataOutput output, Map<ResourceLocation, MaterialBase> map) {
        this.output = output;
        this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK,"materials");
        this.theMap = map;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return CompletableFuture.allOf(theMap.entrySet().stream().map(
            entry -> {
                Path path = this.pathProvider.json(entry.getKey());
                JsonElement element = MATERIAL_BASE_CODEC.encodeStart(JsonOps.INSTANCE,entry.getValue())
                        .getOrThrow(false,s -> LOGGER.error("{} provider failed to encode {}: {}",this.getName(),path,s));
                return DataProvider.saveStable(cachedOutput,element,path);
            }
        ).toArray(CompletableFuture[]::new));
    }
    @Override
    public String getName() {
        return "LensMaterialsProvider";
    }
}
