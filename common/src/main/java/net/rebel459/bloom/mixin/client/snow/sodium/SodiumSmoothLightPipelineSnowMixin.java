package net.rebel459.bloom.mixin.client.snow.sodium;

import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.model.light.smooth.SmoothLightPipeline;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.rebel459.bloom.client.snow.SodiumSnowOverlayLightingContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SmoothLightPipeline.class, remap = false)
public abstract class SodiumSmoothLightPipelineSnowMixin {
	@Shadow
	private long cachedPos;

	@Inject(method = "calculate", at = @At("HEAD"))
	private void bloom$resetCachedFaceDataForFakeSnowLayer(
		ModelQuadView quad,
		BlockPos pos,
		QuadLightData out,
		Direction cullFace,
		Direction lightFace,
		boolean shade,
		boolean enhanced,
		CallbackInfo ci
	) {
		if (SodiumSnowOverlayLightingContext.isActiveFor(pos)) {
			this.cachedPos = Long.MIN_VALUE;
		}
	}
}
