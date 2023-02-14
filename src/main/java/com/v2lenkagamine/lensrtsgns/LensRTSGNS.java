package com.v2lenkagamine.lensrtsgns;

import com.v2lenkagamine.lensrtsgns.api.GunTimerAPI;
import com.v2lenkagamine.lensrtsgns.common.items.Gun_Base_Item;
import com.v2lenkagamine.lensrtsgns.common.items.Test_Item;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LensRTSGNS implements ModInitializer {
	public static final String MOD_ID = "lensrtsgns";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	//Items
	public static final Test_Item TEST_ITEM = new Test_Item(new FabricItemSettings());
	public static final Gun_Base_Item GUN_BASE_ITEM = new Gun_Base_Item(new FabricItemSettings());
	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM,new Identifier(MOD_ID,"test_item"),TEST_ITEM);
		Registry.register(Registries.ITEM,new Identifier(MOD_ID,"gun_base_item"), GUN_BASE_ITEM);

		GunTimerAPI.GUN_TIMER.registerSelf(GUN_BASE_ITEM);

		LOGGER.info("Oh dear god Lens here.");
	}
}
