package net.rebel459.bloom.mixin.client.snow.sodium;

import net.caffeinemc.mods.sodium.client.model.light.data.ArrayLightDataCache;
import net.caffeinemc.mods.sodium.client.model.light.data.SingleBlockLightDataCache;
import net.rebel459.bloom.client.snow.SodiumSnowOverlayLightingContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
	value = {
		ArrayLightDataCache.class,
		SingleBlockLightDataCache.class
	},
	remap = false
)
public abstract class SodiumLightDataCacheSnowMixin {
	@Inject(method = "get(III)I", at = @At("HEAD"), cancellable = true)
	private void bloom$bypassCachedSnowBlockLightData(
		int x,
		int y,
		int z,
		CallbackInfoReturnable<Integer> cir
	) {
		if (SodiumSnowOverlayLightingContext.isActiveFor(x, y, z)) {
			cir.setReturnValue(((SodiumLightDataAccessInvoker) this).bloom$compute(x, y, z));
		}
	}
}
