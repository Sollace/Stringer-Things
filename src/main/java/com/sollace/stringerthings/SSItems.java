package com.sollace.stringerthings;

import java.util.function.Function;

import com.sollace.stringerthings.util.RegistryEntryUtil;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registry;
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

    Item STRING_SWORD = register("string_sword", new SwordItem(StringToolMaterial.INSTANCE, makeSilky(1).attributeModifiers(SwordItem.createAttributeModifiers(StringToolMaterial.INSTANCE, 3, -2.4F))));
    Item STRING_SHOVEL = register("string_shovel", new ShovelItem(StringToolMaterial.INSTANCE, makeSilky(2).attributeModifiers(ShovelItem.createAttributeModifiers(StringToolMaterial.INSTANCE, 1.5F, -3))));
    Item STRING_PICKAXE = register("string_pickaxe", new PickaxeItem(StringToolMaterial.INSTANCE, makeSilky(2).attributeModifiers(PickaxeItem.createAttributeModifiers(StringToolMaterial.INSTANCE, 1, -2.8F))));
    Item STRING_AXE = register("string_axe", new AxeItem(StringToolMaterial.INSTANCE, makeSilky(2).attributeModifiers(PickaxeItem.createAttributeModifiers(StringToolMaterial.INSTANCE, 7, -3.2F))));
    Item STRING_HOE = register("string_hoe", new HoeItem(StringToolMaterial.INSTANCE, makeSilky(3).attributeModifiers(PickaxeItem.createAttributeModifiers(StringToolMaterial.INSTANCE, -1, -2))) {});

    Item STRING_BOOTS = register("string_boots", new ArmorItem(StringArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, makeSilky(1).maxCount(1)));

    static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, StringerThings.id(name), item);
    }

    static Item.Settings makeSilky(int level) {
        return new Item.Settings().component(DataComponentTypes.ENCHANTMENTS, SILKY_ENCHANTMENTS_FACTORY.apply(level));
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
