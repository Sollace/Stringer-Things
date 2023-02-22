package com.sollace.stringerthings;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class StringToolMaterial implements ToolMaterial {
    public static final ToolMaterial INSTANCE = new StringToolMaterial();

    private final Ingredient repairIngredient = Ingredient.ofItems(Items.STRING);

    @Override
    public int getDurability() {
        return 15;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7;
    }

    @Override
    public float getAttackDamage() {
        return 9;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 9000;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}