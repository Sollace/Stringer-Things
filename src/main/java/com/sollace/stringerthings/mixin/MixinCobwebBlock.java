package com.sollace.stringerthings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.sollace.stringerthings.StringerThings;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;


@Mixin(WebBlock.class)
abstract class MixinCobwebBlock {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void onEntityCollision(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean isPrecise, CallbackInfo info) {
        if (StringerThings.hasStringBoots(entity)) {
            info.cancel();
        }
    }
}
