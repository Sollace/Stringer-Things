package com.sollace.stringerthings;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKeys;

public interface SSTags {
    TagKey<Item> STRING_TOOLS = register("string_tools");
    TagKey<Item> STRING_BOOTS = register("string_boots");

    static TagKey<Item> register(String name) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
    }
}
