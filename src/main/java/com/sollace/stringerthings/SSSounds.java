package com.sollace.stringerthings;

import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

public interface SSSounds {
    RegistryEntry<SoundEvent> ITEM_ARMOR_EQUIP_STRING = registerReference("item.armor.equip_sring");

    private static RegistryEntry.Reference<SoundEvent> registerReference(String name) {
        Identifier id = StringerThings.id(name);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    static void bootstrap() {
    }
}
