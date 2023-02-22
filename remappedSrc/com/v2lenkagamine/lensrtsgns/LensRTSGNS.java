package com.v2lenkagamine.lensrtsgns;

import com.google.gson.stream.JsonReader;
import com.v2lenkagamine.lensrtsgns.api.GunTimerAPI;
import com.v2lenkagamine.lensrtsgns.common.items.Ammo_Base_Item;
import com.v2lenkagamine.lensrtsgns.common.items.Gun_Base_Item;
import com.v2lenkagamine.lensrtsgns.common.items.Test_Item;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class LensRTSGNS implements ModInitializer {
	public static final String MOD_ID = "lensrtsgns";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	//Items
	public static final Test_Item TEST_ITEM = new Test_Item(new FabricItemSettings().stacksTo(1));
	public static final Gun_Base_Item GUN_BASE_ITEM = new Gun_Base_Item(new FabricItemSettings().stacksTo(1));
	public static final Ammo_Base_Item AMMO_BASE_ITEM = new Ammo_Base_Item(new FabricItemSettings().stacksTo(1));

	//APIs
	public static final ItemApiLookup<GunTimerAPI, Void> GUN_TIMER = ItemApiLookup.get(new ResourceLocation("lrtsgns:gun_timer"), GunTimerAPI.class, Void.class);
	@Override
	public void onInitialize() {
		Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(MOD_ID,"test_item"),TEST_ITEM);
		Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(MOD_ID,"gun_base_item"), GUN_BASE_ITEM);
		Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(MOD_ID,"ammo_base_item"),AMMO_BASE_ITEM);

		GUN_TIMER.registerForItems((itemStack, context) -> {return new GunTimerAPI();},GUN_BASE_ITEM);


		//Pack Loader. Loads parts and stuff.
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public ResourceLocation getFabricId() {
				return new ResourceLocation(MOD_ID,"lensrtsgns");
			}

			@Override
			public void onResourceManagerReload(ResourceManager manager) {
				//Clear the cache here?
				manager.listResources("lensrtsgns", identifier -> identifier.getPath().endsWith(".json")).forEach((identifier, resource) -> {
					try {
						InputStream stream = resource.open();

						stream.close(); //I swear to fuck do not forget this.
					} catch (Exception e) {
						LOGGER.error("Error occured loading data json " + identifier.toString(),e);
					}
				});
			}
		});

		LOGGER.info("Oh dear god Lens here.");
	}
}
