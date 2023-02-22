package com.v2lenkagamine.lensrtsgns.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.v2lenkagamine.lensrtsgns.LensRTSGNS.MOD_ID;

public class LensFabricJsonReloadListener<T> extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson DEFAULT_GSON = new Gson();
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private final Codec<T> codec;

    private final String folderPath;

    protected Map<ResourceLocation,T> data = new HashMap<>();

    public LensFabricJsonReloadListener(String folderPath,Codec<T> codec) {
        this(folderPath,codec,DEFAULT_GSON);
    }

    public LensFabricJsonReloadListener(String folderPath,Codec<T> codec,Gson gson) {
        super (gson,folderPath);
        this.folderPath = folderPath;
        this.codec = codec;
    }

    public Map<ResourceLocation,T> getData(){
        return this.data;
    }

    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(MOD_ID,"lrtsgns");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        LOGGER.info("Loading data for loader: {}",this.folderPath);
        Map<ResourceLocation,T> newMap = new HashMap<>();
        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet())
        {
            ResourceLocation key = entry.getKey();
            JsonElement element = entry.getValue();
            this.codec.decode(JsonOps.INSTANCE, element).get()
                    .ifLeft(result -> newMap.put(key, result.getFirst()))
                    .ifRight(partial -> LOGGER.error("Failed to parse data json for {} due to: {}", key, partial.message()));
        }

        this.data = newMap;
        LOGGER.info("Data loader for {} loaded {} jsons", this.folderPath, this.data.size());
    }
}
