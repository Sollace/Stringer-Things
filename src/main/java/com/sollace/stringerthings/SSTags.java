package com.sollace.stringerthings;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface SSTags {
    TagKey<Item> STRING_TOOLS = register("string_tools");

    static TagKey<Item> register(String name) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
    }
}
