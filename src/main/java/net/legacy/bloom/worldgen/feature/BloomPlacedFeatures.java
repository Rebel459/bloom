package net.legacy.bloom.worldgen.feature;

import net.frozenblock.lib.worldgen.feature.api.FrozenLibPlacedFeature;
import net.legacy.bloom.Bloom;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class BloomPlacedFeatures {
	//FALLEN TREES
	public static final FrozenLibPlacedFeature DISK_CLAY = register("disk_clay");
	public static final FrozenLibPlacedFeature DISK_SAND = register("disk_sand");
	public static final FrozenLibPlacedFeature DISK_GRAVEL = register("disk_gravel");

	public static final FrozenLibPlacedFeature FLOWER_HELLEBORE = register("flower_hellebore");
	public static final FrozenLibPlacedFeature FLOWER_LILY_OF_THE_VALLEY = register("flower_lily_of_the_valley");

	public static void registerPlacedFeatures(@NotNull BootstrapContext<PlacedFeature> entries) {

		var configuredFeatures = entries.lookup(Registries.CONFIGURED_FEATURE);

		DISK_CLAY.makeAndSetHolder(configuredFeatures.getOrThrow(BloomConfiguredFeatures.DISK_CLAY.getKey()),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
				BiomeFilter.biome()
		);
		DISK_SAND.makeAndSetHolder(configuredFeatures.getOrThrow(BloomConfiguredFeatures.DISK_SAND.getKey()),
				CountPlacement.of(3),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
				BiomeFilter.biome()
		);
		DISK_GRAVEL.makeAndSetHolder(configuredFeatures.getOrThrow(BloomConfiguredFeatures.DISK_GRAVEL.getKey()),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_TOP_SOLID,
				BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
				BiomeFilter.biome()
		);
	}

	@NotNull
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <FC extends FeatureConfiguration> FrozenLibPlacedFeature register(
			@NotNull String id, Holder<ConfiguredFeature<FC, ?>> configured, @NotNull List<PlacementModifier> modifiers
	) {
		return new FrozenLibPlacedFeature(Bloom.id(id)).makeAndSetHolder((Holder) configured, modifiers);
	}

	@NotNull
	public static <FC extends FeatureConfiguration> FrozenLibPlacedFeature register(
			@NotNull String id, Holder<ConfiguredFeature<FC, ?>> registryEntry, @NotNull PlacementModifier... modifiers
	) {
		return register(id, registryEntry, List.of(modifiers));
	}

	@NotNull
	public static FrozenLibPlacedFeature register(@NotNull String id) {
		return new FrozenLibPlacedFeature(Bloom.id(id));
	}
}