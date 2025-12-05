package com.sollace.stringerthings;

import java.util.function.Function;

import com.sollace.stringerthings.util.RegistryEntryUtil;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.equipment.ArmorType;

public interface SSItems {
    Function<Integer, ItemEnchantments> SILKY_ENCHANTMENTS_FACTORY = Util.memoize(level -> {
        ItemEnchantments.Mutable builder = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        builder.set(RegistryEntryUtil.dynamicEntryOf(Enchantments.SILK_TOUCH), level);
        DynamicRegistrySetupCallback.EVENT.register(registries -> {
            registries.registerEntryAdded(Registries.ENCHANTMENT, (raw, id, value) -> {
                if (Enchantments.SILK_TOUCH.identifier().equals(id)) {
                    Holder<Enchantment> entry = registries.getOptional(Registries.ENCHANTMENT).get().wrapAsHolder(value);
                    builder.set(entry, level);
                }
            });
        });

        return builder.toImmutable();
    });

    Item STRING_SWORD = register("string_sword", settings -> new Item(makeSilky(settings, 1).sword(SSMaterials.TOOL_MATERIAL, 3, -2.4F)));
    Item STRING_SHOVEL = register("string_shovel", settings -> new ShovelItem(SSMaterials.TOOL_MATERIAL, 1.5F, -3, makeSilky(settings, 2)));
    Item STRING_PICKAXE = register("string_pickaxe", settings -> new Item(makeSilky(settings, 2).pickaxe(SSMaterials.TOOL_MATERIAL, 1, -2.8F)));
    Item STRING_AXE = register("string_axe", settings -> new AxeItem(SSMaterials.TOOL_MATERIAL, 7, -3.2F, makeSilky(settings, 2)));
    Item STRING_HOE = register("string_hoe", settings -> new HoeItem(SSMaterials.TOOL_MATERIAL, -1, -2, makeSilky(settings, 3)) {});
    Item STRING_MACE = register("string_mace", settings -> new StringMaceItem(makeSilky(settings, 1)));

    Item STRING_BOOTS = register("string_boots", settings -> new Item(makeSilky(settings, 1).humanoidArmor(SSMaterials.ARMOR_MATERIAL, ArmorType.BOOTS).stacksTo(1)));

    static Item register(String name, Function<Item.Properties, Item> factory) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, StringerThings.id(name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(new Item.Properties().setId(key)));
    }

    static Item.Properties makeSilky(Item.Properties settings, int level) {
        return settings.component(DataComponents.ENCHANTMENTS, SILKY_ENCHANTMENTS_FACTORY.apply(level));
    }

    static void bootstrap() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(event -> {
            event.addAfter(Items.NETHERITE_HOE, STRING_SHOVEL, STRING_PICKAXE, STRING_AXE, STRING_HOE);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(event -> {
            event.addAfter(Items.NETHERITE_SWORD, STRING_SWORD);
            event.addAfter(Items.NETHERITE_AXE, STRING_AXE);
            event.addAfter(Items.NETHERITE_BOOTS, STRING_BOOTS);
            event.addAfter(Items.MACE, STRING_MACE);
        });
    }
}
