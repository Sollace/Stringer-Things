package com.sollace.stringerthings;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.MaceItem;

public class StringMaceItem extends MaceItem {

    public StringMaceItem(Settings settings) {
        super(settings
                .attributeModifiers(createAttributeModifiers())
                .maxDamage(StringToolMaterial.INSTANCE.getDurability())
                .component(DataComponentTypes.TOOL, MaceItem.createToolComponent())
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 12.0, Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .add(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3.4F, Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .build();
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return -100000;
    }
}
