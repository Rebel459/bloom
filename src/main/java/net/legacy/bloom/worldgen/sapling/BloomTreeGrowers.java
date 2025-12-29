package net.legacy.bloom.worldgen.sapling;

import net.legacy.bloom.Bloom;
import net.legacy.bloom.worldgen.BloomFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public final class BloomTreeGrowers {

	public static final TreeGrower JACARANDA = new TreeGrower(
		Bloom.id("jacaranda").toString(),
		Optional.empty(),
		Optional.empty(),
		Optional.empty()
	) {
		@Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
			return bees ? BloomFeatures.CONFIGURED_JACARANDA_TREE_BEES : BloomFeatures.CONFIGURED_JACARANDA_TREE;
		}
	};

	private BloomTreeGrowers() {
	}
}