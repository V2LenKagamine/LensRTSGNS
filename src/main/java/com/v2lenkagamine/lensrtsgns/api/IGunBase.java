package com.v2lenkagamine.lensrtsgns.api;


import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

public interface IGunBase extends ItemConvertible {

    @Override
    default Item asItem() {
     return (Item) this;
    }
}
