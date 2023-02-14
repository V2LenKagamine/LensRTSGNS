package com.v2lenkagamine.lensrtsgns.common.items;

import com.v2lenkagamine.lensrtsgns.api.IGunBase;
import com.v2lenkagamine.lensrtsgns.util.WeaponFire;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Gun_Base_Item extends Item implements IGunBase{
    public Gun_Base_Item(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isDamageable() {
        return false;
    }
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()){
            WeaponFire.fireNormal(user, 16, 5);
        }
        return super.use(world, user, hand);
    }
}

