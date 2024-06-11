package com.sollace.stringerthings;

import java.util.EnumMap;
import java.util.List;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class StringArmorMaterial {
    public static final RegistryEntry<ArmorMaterial> INSTANCE = Registry.registerReference(
            Registries.ARMOR_MATERIAL, StringerThings.id("string"), new ArmorMaterial(
            new EnumMap<>(ArmorItem.Type.class),
            9000,
            SSSounds.ITEM_ARMOR_EQUIP_STRING,
            StringToolMaterial.INSTANCE::getRepairIngredient,
            List.of(new ArmorMaterial.Layer(StringerThings.id("string"))),
            0F, 0F));
}
