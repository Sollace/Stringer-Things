package com.sollace.stringerthings;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class StringerThings implements ModInitializer {
    @Override
    public void onInitialize() {
        SSItems.bootstrap();
    }

    public static boolean hasStringBoots(Entity entity) {
        return entity instanceof LivingEntity l
                && l.getEquippedStack(EquipmentSlot.FEET).isIn(SSTags.STRING_BOOTS);
    }
}
