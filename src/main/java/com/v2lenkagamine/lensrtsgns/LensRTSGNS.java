package com.v2lenkagamine.lensrtsgns;

import com.v2lenkagamine.lensrtsgns.api.GunTimerAPI;
import com.v2lenkagamine.lensrtsgns.api.LensFabricJsonReloadListener;
import com.v2lenkagamine.lensrtsgns.common.items.Ammo_Base_Item;
import com.v2lenkagamine.lensrtsgns.common.items.Gun_Base_Item;
import com.v2lenkagamine.lensrtsgns.common.items.Test_Item;
import com.v2lenkagamine.lensrtsgns.parts.MaterialBase;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.v2lenkagamine.lensrtsgns.parts.MaterialBase.MATERIAL_BASE_CODEC;


public class LensRTSGNS implements ModInitializer{
	public static final String MOD_ID = "lensrtsgns";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	//Items
	public static final Test_Item TEST_ITEM = new Test_Item(new FabricItemSettings().stacksTo(1));
	public static final Gun_Base_Item GUN_BASE_ITEM = new Gun_Base_Item(new FabricItemSettings().stacksTo(1));
	public static final Ammo_Base_Item AMMO_BASE_ITEM = new Ammo_Base_Item(new FabricItemSettings().stacksTo(1));

	//APIs
	public static final ItemApiLookup<GunTimerAPI, Void> GUN_TIMER = ItemApiLookup.get(new ResourceLocation(MOD_ID,"gun_timer"), GunTimerAPI.class, Void.class);
	@Override
	public void onInitialize() {
		Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(MOD_ID,"test_item"),TEST_ITEM);
		Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(MOD_ID,"gun_base_item"), GUN_BASE_ITEM);
		Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(MOD_ID,"ammo_base_item"),AMMO_BASE_ITEM);

		GUN_TIMER.registerForItems((itemStack, context) -> new GunTimerAPI(),GUN_BASE_ITEM);

		//Pack Loader. Loads parts and stuff.
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new LensFabricJsonReloadListener<>("materials", MATERIAL_BASE_CODEC));
		LOGGER.info("Oh dear god Lens here.");
	}
}
