package com.sollace.stringerthings;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class StringArmorMaterial implements ArmorMaterial {
    public static final ArmorMaterial INSTANCE = new StringArmorMaterial();

    @Override
    public int getDurability(ArmorItem.Type type) {
        return 15;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 9000;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return StringToolMaterial.INSTANCE.getRepairIngredient();
    }

    @Override
    public String getName() {
        return "string";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
