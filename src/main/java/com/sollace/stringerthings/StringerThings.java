package com.sollace.stringerthings;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

public class StringerThings implements ModInitializer {
    @Override
    public void onInitialize() {
        SSItems.bootstrap();
    }

    public static void generateSilkynessEnchantment(ItemStack stack, NbtList enchantments) {
        Identifier enchantmentId = EnchantmentHelper.getEnchantmentId(Enchantments.SILK_TOUCH);
        int requiredLevel = SSItems.SILKY_LEVELS.getOrDefault(stack.getItem(), 0);

        if (requiredLevel > 0) {
            if (enchantments.isEmpty()) {
                enchantments.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(Enchantments.SILK_TOUCH), 1));
                return;
            }

            enchantments.stream()
                .filter(el -> el instanceof NbtCompound)
                .map(el -> (NbtCompound)el)
                .filter(el -> enchantmentId.equals(EnchantmentHelper.getIdFromNbt(el)))
                .findFirst()
                .ifPresentOrElse(el -> {
                    if (EnchantmentHelper.getLevelFromNbt(el) < requiredLevel) {
                        EnchantmentHelper.writeLevelToNbt(el, requiredLevel);
                    }
                }, () -> {
                    enchantments.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(Enchantments.SILK_TOUCH), 1));
                });
        }
    }
}
