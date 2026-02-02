package net.legacy.bloom.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.legacy.bloom.block.ExtendedLeavesBlock;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

	@WrapOperation(
		method = "allChanged",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/LeavesBlock;setCutoutLeaves(Z)V"
		)
	)
	private void setCutoutExtendedLeaves(boolean bl, Operation<Void> original) {
		ExtendedLeavesBlock.setCutoutLeaves(bl);
		original.call(bl);
	}
}
