package net.rebel459.bloom.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.rebel459.bloom.config.BloomConfig;
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
			target = "(Ljava/lang/String;FLjava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/world/level/block/grower/TreeGrower;",
			ordinal = 1
		)
	)
	private static TreeGrower bloom$pineTrees(String name, float secondaryChance, Optional megaTree, Optional secondaryMegaTree, Optional tree, Optional secondaryTree, Optional flowers, Optional secondaryFlowers, Operation<TreeGrower> original) {
		if (BloomConfig.get().worldgen.pine_trees) return original.call(name, secondaryChance, megaTree, megaTree, tree, secondaryTree, flowers, secondaryFlowers);
		else return original.call(name, secondaryChance, megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
	}
}
