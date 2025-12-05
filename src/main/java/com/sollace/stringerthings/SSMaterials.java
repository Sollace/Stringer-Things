package com.sollace.stringerthings;

import java.util.EnumMap;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

public interface SSMaterials {
    ToolMaterial TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            15,
            7,
            9,
            9000,
            SSTags.STRING_REPAIR_INGREDIENT
    );
    ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(
            0,
            new EnumMap<>(ArmorType.class),
            9000,
            SSSounds.ITEM_ARMOR_EQUIP_STRING,
            0F, 0F,
            SSTags.STRING_REPAIR_INGREDIENT,
            ResourceKey.create(EquipmentAssets.ROOT_ID, StringerThings.id("string"))
    );
}
