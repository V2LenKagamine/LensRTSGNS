package com.v2lenkagamine.lensrtsgns.api;


import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public interface IGunBase extends ItemLike {

    @Override
    default Item asItem() {
     return (Item) this;
    }


}
