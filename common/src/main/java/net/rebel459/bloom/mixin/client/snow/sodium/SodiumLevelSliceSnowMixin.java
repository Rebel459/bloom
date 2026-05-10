package net.rebel459.bloom.mixin.client.snow.sodium;

import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.rebel459.bloom.client.snow.SodiumSnowOverlayLightingContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LevelSlice.class, remap = false)
public abstract class SodiumLevelSliceSnowMixin {
	@Inject(method = "getBlockState(III)Lnet/minecraft/world/level/block/state/BlockState;", at = @At("HEAD"), cancellable = true)
	private void bloom$fakeSnowBlockAsSnowLayer(int blockX, int blockY, int blockZ, CallbackInfoReturnable<BlockState> cir) {
		BlockState fakeState = SodiumSnowOverlayLightingContext.get(BlockPos.containing(blockX, blockY, blockZ));

		if (fakeState != null) {
			cir.setReturnValue(fakeState);
		}
	}
}
