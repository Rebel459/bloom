package net.legacy.bloom.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.util.Either;
import net.legacy.bloom.block.SleepingBagBlock;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

	@Unique
	private BlockPos blockPos;

	@Inject(method = "startSleepInBed", at = @At("HEAD"))
	private void getSleepPos(BlockPos blockPos, CallbackInfoReturnable<Either<Player.BedSleepingProblem, Unit>> cir) {
		this.blockPos = blockPos;
	}

	@WrapOperation(method = "startSleepInBed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/attribute/BedRule;canSetSpawn(Lnet/minecraft/world/level/Level;)Z"))
	private boolean sleepingBag(BedRule instance, Level level, Operation<Boolean> original) {
		BlockState blockState = level.getBlockState(blockPos);
		if (blockState.getBlock() instanceof SleepingBagBlock) return false;

		return original.call(instance, level);
	}
}
