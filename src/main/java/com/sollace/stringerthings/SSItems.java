package com.sollace.stringerthings;

import java.util.HashMap;
import java.util.Map;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public interface SSItems {
    Map<Item, Integer> SILKY_LEVELS = new HashMap<>();

    Item STRING_SWORD = register("string_sword", 1, new SwordItem(StringToolMaterial.INSTANCE, 3, -2.4f, new Item.Settings()));
    Item STRING_SHOVEL = register("string_shovel", 2, new ShovelItem(StringToolMaterial.INSTANCE, 1.5f, -3.0f, new Item.Settings()));
    Item STRING_PICKAXE = register("string_pickaxe", 2, new PickaxeItem(StringToolMaterial.INSTANCE, 1, -2.8f, new Item.Settings()));
    Item STRING_AXE = register("string_axe", 2, new AxeItem(StringToolMaterial.INSTANCE, 7.0f, -3.2f, new Item.Settings()));
    Item STRING_HOE = register("string_hoe", 3, new HoeItem(StringToolMaterial.INSTANCE, -1, -2.0f, new Item.Settings()) {});

    Item STRING_BOOTS = register("string_boots", 1, new ArmorItem(StringArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new Item.Settings()));

    static Item register(String name, int level, Item item) {
        SILKY_LEVELS.put(item, level);
        return Registry.register(Registries.ITEM, new Identifier("stringerthings", name), item);
    }

    static void bootstrap() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(event -> {
            event.addAfter(Items.NETHERITE_HOE, STRING_SHOVEL, STRING_PICKAXE, STRING_AXE, STRING_HOE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(event -> {
            event.addAfter(Items.NETHERITE_SWORD, STRING_SWORD);
            event.addAfter(Items.NETHERITE_AXE, STRING_AXE);
            event.addAfter(Items.NETHERITE_BOOTS, STRING_BOOTS);
        });
    }
}
