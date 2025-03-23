package com.sollace.stringerthings;

import java.util.function.Function;

import com.sollace.stringerthings.util.RegistryEntryUtil;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Util;
import net.minecraft.registry.Registries;

public interface SSItems {
    Function<Integer, ItemEnchantmentsComponent> SILKY_ENCHANTMENTS_FACTORY = Util.memoize(level -> {
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
        builder.set(RegistryEntryUtil.dynamicEntryOf(Enchantments.SILK_TOUCH), level);
        DynamicRegistrySetupCallback.EVENT.register(registries -> {
            registries.registerEntryAdded(RegistryKeys.ENCHANTMENT, (raw, id, value) -> {
                if (Enchantments.SILK_TOUCH.getValue().equals(id)) {
                    RegistryEntry<Enchantment> entry = registries.getOptional(RegistryKeys.ENCHANTMENT).get().getEntry(value);
                    builder.set(entry, level);
                }
            });
        });

        return builder.build();
    });

    Item STRING_SWORD = register("string_sword", settings -> new Item(makeSilky(settings, 1).sword(SSMaterials.TOOL_MATERIAL, 3, -2.4F)));
    Item STRING_SHOVEL = register("string_shovel", settings -> new ShovelItem(SSMaterials.TOOL_MATERIAL, 1.5F, -3, makeSilky(settings, 2)));
    Item STRING_PICKAXE = register("string_pickaxe", settings -> new Item(makeSilky(settings, 2).pickaxe(SSMaterials.TOOL_MATERIAL, 1, -2.8F)));
    Item STRING_AXE = register("string_axe", settings -> new AxeItem(SSMaterials.TOOL_MATERIAL, 7, -3.2F, makeSilky(settings, 2)));
    Item STRING_HOE = register("string_hoe", settings -> new HoeItem(SSMaterials.TOOL_MATERIAL, -1, -2, makeSilky(settings, 3)) {});
    Item STRING_MACE = register("string_mace", settings -> new StringMaceItem(makeSilky(settings, 1)));

    Item STRING_BOOTS = register("string_boots", settings -> new Item(makeSilky(settings, 1).armor(SSMaterials.ARMOR_MATERIAL, EquipmentType.BOOTS).maxCount(1)));

    static Item register(String name, Function<Item.Settings, Item> factory) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, StringerThings.id(name));
        return Registry.register(Registries.ITEM, key, factory.apply(new Item.Settings().registryKey(key)));
    }

    static Item.Settings makeSilky(Item.Settings settings, int level) {
        return settings.component(DataComponentTypes.ENCHANTMENTS, SILKY_ENCHANTMENTS_FACTORY.apply(level));
    }

    static void bootstrap() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(event -> {
            event.addAfter(Items.NETHERITE_HOE, STRING_SHOVEL, STRING_PICKAXE, STRING_AXE, STRING_HOE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(event -> {
            event.addAfter(Items.NETHERITE_SWORD, STRING_SWORD);
            event.addAfter(Items.NETHERITE_AXE, STRING_AXE);
            event.addAfter(Items.NETHERITE_BOOTS, STRING_BOOTS);
            event.addAfter(Items.MACE, STRING_MACE);
        });
    }
}
