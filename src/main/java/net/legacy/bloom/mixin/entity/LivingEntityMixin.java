package net.legacy.bloom.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.legacy.bloom.tag.BloomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@WrapOperation(
		method = "setPosToBed",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/LivingEntity;setPos(DDD)V"
		)
	)
	public void bloom$sleepLowerInSleepingBags(
		LivingEntity instance, double x, double y, double z, Operation<Void> original,
		BlockPos pos
	) {
		y = LivingEntity.class.cast(this).level().getBlockState(pos).is(BloomBlockTags.SLEEPING_BAGS) ? y - 0.1F : y;
		original.call(instance, x, y, z);
	}

}
