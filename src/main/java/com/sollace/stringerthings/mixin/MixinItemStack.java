package com.sollace.stringerthings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.sollace.stringerthings.StringerThings;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;

@Mixin(ItemStack.class)
abstract class MixinItemStack {
    @Inject(method = "getEnchantments", at = @At("RETURN"))
    private void onGetEnchantments(CallbackInfoReturnable<NbtList> info) {
        StringerThings.generateSilkynessEnchantment((ItemStack)(Object)this, info.getReturnValue());
    }
}
