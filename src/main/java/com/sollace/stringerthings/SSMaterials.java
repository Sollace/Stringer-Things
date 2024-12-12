package com.sollace.stringerthings;

import java.util.EnumMap;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;

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
            new EnumMap<>(EquipmentType.class),
            9000,
            SSSounds.ITEM_ARMOR_EQUIP_STRING,
            0F, 0F,
            SSTags.STRING_REPAIR_INGREDIENT,
            RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, StringerThings.id("string"))
    );
}
