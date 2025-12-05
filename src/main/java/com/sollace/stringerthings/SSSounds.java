package com.sollace.stringerthings;

import net.minecraft.core.Holder.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public interface SSSounds {
    Reference<SoundEvent> ITEM_ARMOR_EQUIP_STRING = registerReference("item.armor.equip_sring");

    private static Reference<SoundEvent> registerReference(String name) {
        Identifier id = StringerThings.id(name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    static void bootstrap() {
    }
}
