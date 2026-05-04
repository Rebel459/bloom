package net.rebel459.bloom.worldgen.sapling;

import java.util.Optional;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.worldgen.BloomFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public final class BloomTreeGrowers {

	public static final TreeGrower JACARANDA = new TreeGrower(
		Bloom.id("jacaranda").toString(),
		Optional.empty(),
		Optional.of(BloomFeatures.CONFIGURED_JACARANDA_TREE),
		Optional.of(BloomFeatures.CONFIGURED_JACARANDA_TREE_BEES)
	);

	public static final TreeGrower PINE = new TreeGrower(
		Bloom.id("pine").toString(),
		0.5F,
		Optional.of(TreeFeatures.MEGA_PINE),
		Optional.of(TreeFeatures.MEGA_PINE),
		Optional.of(TreeFeatures.PINE),
		Optional.empty(),
		Optional.empty(),
		Optional.empty()
	);

	public static final TreeGrower GOLDEN_BIRCH = new TreeGrower(
		Bloom.id("golden_birch").toString(),
		Optional.empty(),
		Optional.of(BloomFeatures.CONFIGURED_GOLDEN_BIRCH_TREE),
		Optional.empty()
	);

	private BloomTreeGrowers() {}
}
