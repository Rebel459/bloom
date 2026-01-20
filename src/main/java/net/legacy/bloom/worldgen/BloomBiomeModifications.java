package net.legacy.bloom.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.sound.BloomSounds;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.util.BiomeHelper;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class BloomBiomeModifications {

	//modificationContext.getAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(LaLSounds.SAVANNA_MUSIC)));

	public static void init() {
        BiomeModifications.create(Bloom.id("remove_desert_grass")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.includeByKey(Biomes.DESERT),
			(selectionContext, modificationContext) -> {
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_BADLANDS);
			}
        );

        BiomeModifications.create(Bloom.id("has_warm_colors")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_WARM_COLORS),
			(selectionContext, modificationContext) -> {
				modificationContext.getEffects().setWaterColor(BiomeHelper.Colors.WARM_WATER);
			}
        );
        BiomeModifications.create(Bloom.id("has_lukewarm_colors")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_LUKEWARM_COLORS),
			(selectionContext, modificationContext) -> {
				modificationContext.getEffects().setWaterColor(BiomeHelper.Colors.LUKEWARM_WATER);
				modificationContext.getEffects().setFoliageColor(BiomeHelper.Colors.LUKEWARM_FOLIAGE);
				modificationContext.getEffects().setGrassColor(BiomeHelper.Colors.LUKEWARM_GRASS);
			}
        );
		BiomeModifications.create(Bloom.id("has_tropical_colors")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_TROPICAL_COLORS),
			(selectionContext, modificationContext) -> {
				modificationContext.getEffects().setWaterColor(BiomeHelper.Colors.TROPICAL_WATER);
			}
		);
		BiomeModifications.create(Bloom.id("has_cold_colors")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_COLD_COLORS),
			(selectionContext, modificationContext) -> {
				modificationContext.getEffects().setWaterColor(BiomeHelper.Colors.COLD_WATER);;
			}
		);
		BiomeModifications.create(Bloom.id("has_frozen_colors")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_FROZEN_COLORS),
			(selectionContext, modificationContext) -> {
				modificationContext.getEffects().setWaterColor(BiomeHelper.Colors.FROZEN_WATER);
			}
		);

		BiomeModifications.create(Bloom.id("has_swamp_mud")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_SWAMP_MUD),
			(selectionContext, modificationContext) -> {
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_SAND);
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
				modificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_CLAY);
				modificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_GRAVEL);
			}
		);

		BiomeModifications.create(Bloom.id("no_pine_trees")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.NO_PINE_TREES),
			(selectionContext, modificationContext) -> {
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_TAIGA);
				modificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BloomFeatures.TREES_TAIGA);
			}
		);

		BiomeModifications.create(Bloom.id("no_default_flowers")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.NO_DEFAULT_FLOWERS),
			(selectionContext, modificationContext) -> {
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_WARM);
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_DEFAULT);
			}
		);

        BiomeModifications.create(Bloom.id("has_less_strict_disks")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.INTERNAL_LESS_STRICT_DISKS),
			(selectionContext, modificationContext) -> {
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_SAND);
				modificationContext.getGenerationSettings().removeFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_GRAVEL);
				BiomeHelper.addLessStrictDisks(modificationContext);
			}
        );
        BiomeModifications.create(Bloom.id("has_windswept_jungle_features")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.INTERNAL_WINDSWEPT_JUNGLE_FEATURES),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.WINDSWEPT_JUNGLE_TREES);
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.WINDSWEPT_JUNGLE_FLOWERS);
			}
        );
		BiomeModifications.create(Bloom.id("has_fen_features")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.INTERNAL_FEN_FEATURES),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FEN_FLOWERS);
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FEN_TREES);
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FEN_WILDFLOWERS);
			}
		);
		BiomeModifications.create(Bloom.id("has_golden_forest_features")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.INTERNAL_GOLDEN_FOREST_FEATURES),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.GOLDEN_FOREST_FLOWERS);
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.GOLDEN_FOREST_TREES);
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.GOLDEN_FOREST_WILDFLOWERS);
			}
		);

		BiomeModifications.create(Bloom.id("has_stone_blobs")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_STONE_BLOBS),
			(selectionContext, modificationContext) -> {
				modificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.ORE_STONE);
			}
		);

		BiomeModifications.create(Bloom.id("has_gravel_blobs")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_GRAVEL_BLOBS),
			(selectionContext, modificationContext) -> {
				modificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.ORE_GRAVEL);
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
        BiomeModifications.create(Bloom.id("has_calla_lily")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_CALLA_LILY),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_CALLA_LILY);
			}
        );
        BiomeModifications.create(Bloom.id("has_dianthus")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_DIANTHUS),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_DIANTHUS);
			}
        );
        BiomeModifications.create(Bloom.id("has_goldenrod")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_GOLDENROD),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_GOLDENROD);
			}
        );
        BiomeModifications.create(Bloom.id("has_orange_daisy")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_ORANGE_DAISY),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_ORANGE_DAISY);
			}
        );
		BiomeModifications.create(Bloom.id("has_scilla")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_SCILLA),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_SCILLA);
			}
		);
		BiomeModifications.create(Bloom.id("has_hyacinth")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_HYACINTH),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_HYACINTH);
			}
		);
		BiomeModifications.create(Bloom.id("has_queencup")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_QUEENCUP),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.FLOWER_QUEENCUP);
			}
		);
        BiomeModifications.create(Bloom.id("has_succulent")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_SUCCULENT),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.PATCH_SUCCULENT);
			}
        );
        BiomeModifications.create(Bloom.id("has_reeds")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_REEDS),
			(selectionContext, modificationContext) -> {
				BiomeHelper.addVegetation(modificationContext, BloomFeatures.PATCH_REEDS);
			}
        );
		BiomeModifications.create(Bloom.id("has_taiga_music")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_TAIGA_MUSIC),
			(selectionContext, modificationContext) -> {
				modificationContext.getAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(BloomSounds.MUSIC_BIOME_TAIGA)));
			}
		);
		BiomeModifications.create(Bloom.id("has_old_growth_taiga_music")).add(
			ModificationPhase.REPLACEMENTS,
			BiomeSelectors.tag(BloomBiomeTags.HAS_OLD_GROWTH_TAIGA_MUSIC),
			(selectionContext, modificationContext) -> {
				modificationContext.getAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(BloomSounds.MUSIC_BIOME_OLD_GROWTH_TAIGA)));
			}
		);
	}
}
