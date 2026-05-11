package net.rebel459.bloom.mixin.client.snow.wilderwild;

import net.frozenblock.wilderwild.registry.WWBlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.rebel459.bloom.client.snow.SnowOverlayHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowOverlayHelper.class)
public class WilderWildSnowIntegrationMixin {

	@Inject(method = "isValidState", at = @At("RETURN"), cancellable = true)
	private static void isValidState(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(cir.getReturnValue() || (state.hasProperty(WWBlockStateProperties.SNOW_LAYERS) && state.getValue(WWBlockStateProperties.SNOW_LAYERS) > 0));
	}

	@Inject(method = "isOverlaySource", at = @At("RETURN"), cancellable = true)
	private static void isOverlaySource(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(cir.getReturnValue() || (state.hasProperty(WWBlockStateProperties.SNOW_LAYERS) && state.getValue(WWBlockStateProperties.SNOW_LAYERS) > 0));
	}

	@Inject(method = "getSnowloggedLayers", at = @At("RETURN"), cancellable = true)
	private static void getSnowloggedLayers(BlockState state, CallbackInfoReturnable<Integer> cir) {
		if (state.hasProperty(WWBlockStateProperties.SNOW_LAYERS)) cir.setReturnValue(Math.max(state.getValue(WWBlockStateProperties.SNOW_LAYERS), cir.getReturnValue()));
	}
}
