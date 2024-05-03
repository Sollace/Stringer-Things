package com.sollace.stringerthings;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

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
    public int getEnchantability() {
        return 9000;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return BlockTags.INCORRECT_FOR_STONE_TOOL;
    }
}