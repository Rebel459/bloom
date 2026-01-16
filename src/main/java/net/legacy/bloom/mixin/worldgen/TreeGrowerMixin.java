package net.legacy.bloom.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.legacy.bloom.tag.BloomBlockTags;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Optional;

@Mixin(TreeGrower.class)
public class TreeGrowerMixin {

	@WrapOperation(
		method = "<clinit>",
		at = @At(
			value = "NEW",
			target = "(Ljava/lang/String;FLjava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/world/level/block/grower/TreeGrower;")
	)
	private static TreeGrower bloom$cancelSetSpawnInSleepingBags(String string, float f, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Operation<TreeGrower> original) {
		if (string.equals("spruce") && optional2.equals(Optional.of(TreeFeatures.MEGA_PINE))) original.call(string, f, optional, Optional.of(TreeFeatures.SPRUCE), optional3, optional4, optional5, optional6);
		return original.call(string, f, optional, optional2, optional3, optional4, optional5, optional6);
	}
}
