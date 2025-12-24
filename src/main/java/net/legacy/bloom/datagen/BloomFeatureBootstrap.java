package net.legacy.bloom.datagen;

import net.frozenblock.lib.worldgen.feature.api.FrozenLibFeatureUtils;
import net.legacy.bloom.worldgen.feature.BloomConfiguredFeatures;
import net.legacy.bloom.worldgen.feature.BloomPlacedFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.NotNull;

public final class BloomFeatureBootstrap {

	public static void bootstrapConfigured(@NotNull BootstrapContext<ConfiguredFeature<?, ?>> entries) {
		FrozenLibFeatureUtils.BOOTSTRAP_CONTEXT = (BootstrapContext) entries;

		BloomConfiguredFeatures.registerConfiguredFeatures(entries);
	}

	public static void bootstrapPlaced(@NotNull BootstrapContext<PlacedFeature> entries) {
		FrozenLibFeatureUtils.BOOTSTRAP_CONTEXT = (BootstrapContext) entries;

		BloomPlacedFeatures.registerPlacedFeatures(entries);
	}
}