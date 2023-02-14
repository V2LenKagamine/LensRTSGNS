package com.v2lenkagamine.lensrtsgns.api;

import com.v2lenkagamine.lensrtsgns.common.items.Gun_Base_Item;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.minecraft.util.Identifier;

public final class GunTimerAPI {
    public static final ItemApiLookup<Gun_Base_Item,Void> GUN_TIMER = ItemApiLookup.get(new Identifier("lrtsgns:gun_timer"), Gun_Base_Item.class,Void.class);
}
