package net.legacy.bloom.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.util.BiomeHelper;
import net.legacy.bloom.worldgen.biome.AridRiver;
import net.legacy.bloom.worldgen.biome.ColdRiver;
import net.legacy.bloom.worldgen.biome.TropicalRiver;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class BloomBiomeModifications {

	//modificationContext.getAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(LaLSounds.SAVANNA_MUSIC)));

	public static void init() {
		BiomeModifications.create(Bloom.id("has_warm_colors")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_WARM_COLORS),
				(selectionContext, modificationContext) -> {
					modificationContext.getEffects().setWaterColor(AridRiver.WATER_COLOR);;
				}
		);
		BiomeModifications.create(Bloom.id("has_tropical_colors")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_TROPICAL_COLORS),
				(selectionContext, modificationContext) -> {
					var generation = modificationContext.getGenerationSettings();
					modificationContext.getEffects().setWaterColor(TropicalRiver.WATER_COLOR);
					generation.removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
					generation.removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_SAND);
					generation.removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
					generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_CLAY);
					generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_SAND);
					generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_GRAVEL);;
				}
		);
		BiomeModifications.create(Bloom.id("has_cold_colors")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_COLD_COLORS),
				(selectionContext, modificationContext) -> {
					modificationContext.getEffects().setWaterColor(ColdRiver.WATER_COLOR);;
				}
		);
		BiomeModifications.create(Bloom.id("has_frozen_colors")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_FROZEN_COLORS),
				(selectionContext, modificationContext) -> {
					modificationContext.getEffects().setWaterColor(1594256);
				}
		);

		BiomeModifications.create(Bloom.id("has_underwater_mud")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_UNDERWATER_MUD),
				(selectionContext, modificationContext) -> {
					modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
					modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_SAND);
					modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
					BiomeHelper.addLessStrictDisks(modificationContext);
				}
		);
		BiomeModifications.create(Bloom.id("has_less_strict_disks")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_LESS_STRICT_DISKS),
				(selectionContext, modificationContext) -> {
					BiomeHelper.addLessStrictDisks(modificationContext);
				}
		);

		BiomeModifications.create(Bloom.id("has_bromeliad")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_BROMELIAD),
				(selectionContext, modificationContext) -> {
					BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_BROMELIAD);
				}
		);
		BiomeModifications.create(Bloom.id("has_pink_orchid")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_PINK_ORCHID),
				(selectionContext, modificationContext) -> {
					BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_PINK_ORCHID);
				}
		);
		BiomeModifications.create(Bloom.id("has_bellflower")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_BELLFLOWER),
				(selectionContext, modificationContext) -> {
					BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_BELLFLOWER);
				}
		);
		BiomeModifications.create(Bloom.id("has_hellebore")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_HELLEBORE),
				(selectionContext, modificationContext) -> {
					BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_HELLEBORE);
				}
		);
		BiomeModifications.create(Bloom.id("has_lily_of_the_valley")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_LILY_OF_THE_VALLEY),
				(selectionContext, modificationContext) -> {
					BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_LILY_OF_THE_VALLEY);
				}
		);
		BiomeModifications.create(Bloom.id("has_hydrangea")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.HAS_HYDRANGEA),
				(selectionContext, modificationContext) -> {
					BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_HYDRANGEA);
				}
		);
	}
}