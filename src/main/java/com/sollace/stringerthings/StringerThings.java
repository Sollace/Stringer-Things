package com.sollace.stringerthings;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;


public class StringerThings implements ModInitializer {
    private static final String DEFAULT_NAMESPACE = "stringerthings";

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(DEFAULT_NAMESPACE, name);
    }

    @Override
    public void onInitialize() {
        SSItems.bootstrap();
    }

    public static boolean hasStringBoots(Entity entity) {
        return entity instanceof LivingEntity l
                && l.getItemBySlot(EquipmentSlot.FEET).is(SSTags.STRING_BOOTS);
    }
}
