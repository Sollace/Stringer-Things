package com.sollace.stringerthings;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface SSTags {
    TagKey<Item> STRING_TOOLS = register("string_tools");
    TagKey<Item> STRING_BOOTS = register("string_boots");

    TagKey<Item> STRING_REPAIR_INGREDIENT = register("string_tool_materials");

    static TagKey<Item> register(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", name));
    }
}
