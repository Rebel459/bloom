package net.rebel459.bloom.mixin.client.snow.snowrealmagic;

import net.minecraft.world.level.block.state.BlockState;
import net.rebel459.bloom.client.snow.SnowOverlayHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.snow.block.SnowVariant;

@Mixin(SnowOverlayHelper.class)
public class SnowRealMagicSnowIntegrationMixin {

	@Inject(method = "isValidState", at = @At("RETURN"), cancellable = true)
	private static void isValidState(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(cir.getReturnValue() || (state.hasProperty(SnowVariant.OPTIONAL_LAYERS) && state.getValue(SnowVariant.OPTIONAL_LAYERS) > 0));
	}

	@Inject(method = "isOverlaySource", at = @At("RETURN"), cancellable = true)
	private static void isOverlaySource(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(cir.getReturnValue() || (state.hasProperty(SnowVariant.OPTIONAL_LAYERS) && state.getValue(SnowVariant.OPTIONAL_LAYERS) > 0));
	}
}
