package com.sollace.stringerthings;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class StringMaceItem extends MaceItem {

    public StringMaceItem(Properties settings) {
        super(settings
                .attributes(createAttributeModifiers())
                .durability(SSMaterials.TOOL_MATERIAL.durability())
                .component(DataComponents.TOOL, MaceItem.createToolProperties())
        );
    }

    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder()
            .add(
                Attributes.ATTACK_KNOCKBACK,
                new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 12.0, Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
            .add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(BASE_ATTACK_SPEED_ID, -3.4F, Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
            .build();
    }

    @Override
    public float getAttackDamageBonus(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return -100000;
    }
}
