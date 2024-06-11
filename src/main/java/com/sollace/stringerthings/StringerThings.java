package com.sollace.stringerthings;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class StringerThings implements ModInitializer {
    private static final String DEFAULT_NAMESPACE = "stringerthings";

    public static Identifier id(String name) {
        return Identifier.of(DEFAULT_NAMESPACE, name);
    }

    @Override
    public void onInitialize() {
        SSItems.bootstrap();
    }

    public static boolean hasStringBoots(Entity entity) {
        return entity instanceof LivingEntity l
                && l.getEquippedStack(EquipmentSlot.FEET).isIn(SSTags.STRING_BOOTS);
    }
}
