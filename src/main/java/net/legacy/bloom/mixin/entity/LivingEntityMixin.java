package net.legacy.bloom.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.legacy.bloom.block.SleepingBagBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Optional;
import java.util.function.Consumer;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@Inject(method = "startSleeping", at = @At("HEAD"), cancellable = true)
	private void sleepingBagStartSleeping(BlockPos blockPos, CallbackInfo ci) {
		LivingEntity livingEntity = LivingEntity.class.cast(this);
		BlockState blockState = livingEntity.level().getBlockState(blockPos);
		if (!(blockState.getBlock() instanceof SleepingBagBlock)) return;
		if (!(livingEntity instanceof Player player)) {
			ci.cancel();
			return;
		}
		if (blockState.getBlock() instanceof SleepingBagBlock) {
			player.level().setBlock(blockPos, blockState.setValue(SleepingBagBlock.OCCUPIED, true), 3);
		}

		player.setPose(Pose.SLEEPING);
		this.setPosToBed(player, blockPos);
		player.setSleepingPos(blockPos);
		player.setDeltaMovement(Vec3.ZERO);
		player.needsSync = true;
		ci.cancel();
	}

	@Inject(method = "getBedOrientation", at = @At("TAIL"), cancellable = true)
	private void sleepingBagOrientation(CallbackInfoReturnable<Direction> cir) {
		if (cir.getReturnValue() != null) return;
		LivingEntity livingEntity = LivingEntity.class.cast(this);
		BlockPos blockPos = livingEntity.getSleepingPos().orElse(null);
		cir.setReturnValue(blockPos != null ? SleepingBagBlock.getOrientation(livingEntity.level(), blockPos) : null);
	}

	@Inject(method = "checkBedExists", at = @At("TAIL"), cancellable = true)
	private void checkSleepingBagExists(CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue()) return;
		LivingEntity livingEntity = LivingEntity.class.cast(this);
		cir.setReturnValue(livingEntity.getSleepingPos().map(blockPos -> livingEntity.level().getBlockState(blockPos).getBlock() instanceof SleepingBagBlock).orElse(false));
	}

	@Inject(method = "stopSleeping", at = @At(value = "HEAD"))
	private void sleepingBagStopSleeping(CallbackInfo ci) {
		LivingEntity livingEntity = LivingEntity.class.cast(this);
		livingEntity.getSleepingPos().filter(livingEntity.level()::hasChunkAt).ifPresent(blockPos -> {
			BlockState blockState = livingEntity.level().getBlockState(blockPos);
			if (blockState.getBlock() instanceof SleepingBagBlock) {
				Direction direction = blockState.getValue(SleepingBagBlock.FACING);
				livingEntity.level().setBlock(blockPos, blockState.setValue(SleepingBagBlock.OCCUPIED, false), 3);
				Vec3 vec3x = BedBlock.findStandUpPosition(livingEntity.getType(), livingEntity.level(), blockPos, direction, livingEntity.getYRot()).orElseGet(() -> {
					BlockPos blockPos2 = blockPos.above();
					return new Vec3(blockPos2.getX() + 0.5, blockPos2.getY() + 0.1, blockPos2.getZ() + 0.5);
				});
				Vec3 vec32 = Vec3.atBottomCenterOf(blockPos).subtract(vec3x).normalize();
				float f = (float)Mth.wrapDegrees(Mth.atan2(vec32.z, vec32.x) * 180.0F / (float)Math.PI - 90.0);
				livingEntity.setPos(vec3x.x, vec3x.y, vec3x.z);
				livingEntity.setYRot(f);
				livingEntity.setXRot(0.0F);
			}
		});
	}

	@Unique
	private void setPosToBed(Player player, BlockPos blockPos) {
		player.setPos((double)blockPos.getX() + (double)0.5F, (double)blockPos.getY() + (double)0.1875F, (double)blockPos.getZ() + (double)0.5F);
	}
}
