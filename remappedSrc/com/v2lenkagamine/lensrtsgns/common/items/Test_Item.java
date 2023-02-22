package com.v2lenkagamine.lensrtsgns.common.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Test_Item extends Item {
    public Test_Item(Properties settings) {
        super(settings);
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
        if (!world.isClientSide) {
            world.getServer().getPlayerList().broadcastSystemMessage(Component.nullToEmpty("Boner"), true);
        }
        return InteractionResultHolder.success(playerEntity.getItemInHand(hand));
    }
}
