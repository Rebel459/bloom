package net.legacy.bloom.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.legacy.bloom.tag.BloomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

	@WrapOperation(
		method = "startSleepInBed",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/attribute/BedRule;canSetSpawn(Lnet/minecraft/world/level/Level;)Z"
		)
	)
	private boolean bloom$cancelSetSpawnInSleepingBags(
		BedRule instance, Level level, Operation<Boolean> original,
		BlockPos pos
	) {
		if (level.getBlockState(pos).is(BloomBlockTags.SLEEPING_BAGS)) return false;
		return original.call(instance, level);
	}
}
