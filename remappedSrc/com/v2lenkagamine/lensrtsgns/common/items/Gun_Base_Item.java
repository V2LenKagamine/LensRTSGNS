package com.v2lenkagamine.lensrtsgns.common.items;

import com.v2lenkagamine.lensrtsgns.LensRTSGNS;
import com.v2lenkagamine.lensrtsgns.api.GunTimerAPI;
import com.v2lenkagamine.lensrtsgns.api.IGunBase;
import com.v2lenkagamine.lensrtsgns.util.WeaponFire;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Gun_Base_Item extends Item implements IGunBase{
    public Gun_Base_Item(Properties settings) {
        super(settings);
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }
    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient) {
        return false;
    }

    @Override
    public boolean allowNbtUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide && selected) {
            GunTimerAPI gunTimer = LensRTSGNS.GUN_TIMER.find(stack,null);
            if (gunTimer != null) {
                if (gunTimer.getTimerTicks() > 0) {
                    gunTimer.timerDownTick();
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (!world.isClientSide) {
            GunTimerAPI heldGun = LensRTSGNS.GUN_TIMER.find(user.getItemInHand(hand), null);
            if (heldGun != null) {
                if(heldGun.getTimerTicks() <= 0) {
                    WeaponFire.firePierce(user, 2, 5);
                    heldGun.setTimer(5);
                }
            }
        }
        return super.use(world, user, hand);
    }
}

