package net.legacy.bloom.worldgen.feature;

import net.frozenblock.lib.worldgen.feature.api.FrozenLibConfiguredFeature;
import net.legacy.bloom.Bloom;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class BloomConfiguredFeatures {
	public static final FrozenLibConfiguredFeature<DiskConfiguration> DISK_CLAY = register("disk_clay");
	public static final FrozenLibConfiguredFeature<DiskConfiguration> DISK_SAND = register("disk_sand");
	public static final FrozenLibConfiguredFeature<DiskConfiguration> DISK_GRAVEL = register("disk_gravel");

	public static void registerConfiguredFeatures(@NotNull BootstrapContext<ConfiguredFeature<?, ?>> entries) {
		var configuredFeatures = entries.lookup(Registries.CONFIGURED_FEATURE);
		var placedFeatures = entries.lookup(Registries.PLACED_FEATURE);

		DISK_CLAY.makeAndSetHolder(Feature.DISK,
				new DiskConfiguration(
						RuleBasedBlockStateProvider.simple(Blocks.CLAY), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.CLAY, Blocks.GRAVEL, Blocks.MUD)), UniformInt.of(2, 3), 1
				)
		);
		DISK_SAND.makeAndSetHolder(Feature.DISK,
				new DiskConfiguration(
						new RuleBasedBlockStateProvider(
								BlockStateProvider.simple(Blocks.SAND),
								List.of(
										new RuleBasedBlockStateProvider.Rule(BlockPredicate.matchesBlocks(Direction.DOWN.getUnitVec3i(), Blocks.AIR), BlockStateProvider.simple(Blocks.SANDSTONE))
								)
						),
						BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MUD)),
						UniformInt.of(2, 6),
						2
				)
		);
		DISK_GRAVEL.makeAndSetHolder(Feature.DISK,
				new DiskConfiguration(
						RuleBasedBlockStateProvider.simple(Blocks.GRAVEL), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MUD)), UniformInt.of(2, 5), 2
				)
		);
	}

	@NotNull
	public static FrozenLibConfiguredFeature<NoneFeatureConfiguration> register(@NotNull String id, @NotNull Feature<NoneFeatureConfiguration> feature) {
		return register(id, feature, FeatureConfiguration.NONE);
	}

	@NotNull
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> FrozenLibConfiguredFeature<FC> register(@NotNull String id, F feature, @NotNull FC config) {
		FrozenLibConfiguredFeature<FC> frozen = new FrozenLibConfiguredFeature<>(Bloom.id(id));
		frozen.makeAndSetHolder(feature, config);
		return frozen;
	}

	@NotNull
	public static <FC extends FeatureConfiguration> FrozenLibConfiguredFeature<FC> register(@NotNull String id) {
		return new FrozenLibConfiguredFeature<>(Bloom.id(id));
	}
}