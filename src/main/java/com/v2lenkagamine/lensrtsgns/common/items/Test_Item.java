package com.v2lenkagamine.lensrtsgns.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Test_Item extends Item {
    public Test_Item(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient) {
            world.getServer().getPlayerManager().broadcast(Text.of("Boner"), true);
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
