package net.rebel459.bloom.worldgen;

import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.bloom.sound.BloomSounds;
import net.rebel459.bloom.tag.BloomBiomeTags;
import net.rebel459.bloom.util.BiomeHelper;
import net.rebel459.unified.platform.UnifiedHelpers;

public final class BloomBiomeModifications {

	public static void init() {
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.NO_BADLANDS_GRASS, context -> {
			context.getFeatures().removeFeature(VegetationPlacements.PATCH_GRASS_BADLANDS, GenerationStep.Decoration.VEGETAL_DECORATION);
		});

		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_WARM_COLORS, context -> {
			context.getEffects().setWaterColor(BiomeHelper.Colors.WARM_WATER);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_LUKEWARM_COLORS, context -> {
			context.getEffects().setWaterColor(BiomeHelper.Colors.LUKEWARM_WATER);
			context.getEffects().setFoliageColor(BiomeHelper.Colors.LUKEWARM_FOLIAGE);
			context.getEffects().setGrassColor(BiomeHelper.Colors.LUKEWARM_GRASS);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_TROPICAL_COLORS, context -> {
			context.getEffects().setWaterColor(BiomeHelper.Colors.TROPICAL_WATER);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_COLD_COLORS, context -> {
			context.getEffects().setWaterColor(BiomeHelper.Colors.COLD_WATER);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_FROZEN_COLORS, context -> {
			context.getEffects().setWaterColor(BiomeHelper.Colors.FROZEN_WATER);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_MUSHROOM_COLORS, context -> {
			context.getEffects().setWaterColor(BiomeHelper.Colors.MUSHROOM_WATER);
		});

		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_SWAMP_MUD, context -> {
			context.getFeatures().removeFeature(MiscOverworldPlacements.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES);
			context.getFeatures().removeFeature(MiscOverworldPlacements.DISK_SAND, GenerationStep.Decoration.UNDERGROUND_ORES);
			context.getFeatures().removeFeature(MiscOverworldPlacements.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);
			context.getFeatures().addFeature(BloomFeatures.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES);
			context.getFeatures().addFeature(BloomFeatures.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);
		});

		if (BloomConfig.get().worldgen.pine_trees) {
			UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.NO_PINE_TREES, context -> {
				context.getFeatures().removeFeature(VegetationPlacements.TREES_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION);
				context.getFeatures().addFeature(BloomFeatures.TREES_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION);
			});
		}

		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.NO_DEFAULT_FLOWERS, context -> {
			context.getFeatures().removeFeature(VegetationPlacements.FLOWER_WARM, GenerationStep.Decoration.VEGETAL_DECORATION);
			context.getFeatures().removeFeature(VegetationPlacements.FLOWER_DEFAULT, GenerationStep.Decoration.VEGETAL_DECORATION);
		});

		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.INTERNAL_LESS_STRICT_DISKS, context -> {
			context.getFeatures().removeFeature(MiscOverworldPlacements.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES);
			context.getFeatures().removeFeature(MiscOverworldPlacements.DISK_SAND, GenerationStep.Decoration.UNDERGROUND_ORES);
			context.getFeatures().removeFeature(MiscOverworldPlacements.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);
			BiomeHelper.addLessStrictDisks(context);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.INTERNAL_WINDSWEPT_JUNGLE_FEATURES, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.WINDSWEPT_JUNGLE_TREES);
			BiomeHelper.addVegetation(context, BloomFeatures.WINDSWEPT_JUNGLE_FLOWERS);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.INTERNAL_SPARSE_WINDSWEPT_JUNGLE_FEATURES, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.SPARSE_WINDSWEPT_JUNGLE_TREES);
			BiomeHelper.addVegetation(context, BloomFeatures.WINDSWEPT_JUNGLE_FLOWERS);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.INTERNAL_FEN_FEATURES, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FEN_FLOWERS);
			BiomeHelper.addVegetation(context, BloomFeatures.FEN_TREES);
			BiomeHelper.addVegetation(context, BloomFeatures.FEN_WILDFLOWERS);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.INTERNAL_GOLDEN_FOREST_FEATURES, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.GOLDEN_FOREST_FLOWERS);
			BiomeHelper.addVegetation(context, BloomFeatures.GOLDEN_FOREST_TREES);
			BiomeHelper.addVegetation(context, BloomFeatures.GOLDEN_FOREST_WILDFLOWERS);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.INTERNAL_GOLDEN_FIELDS_FEATURES, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.GOLDEN_FOREST_FLOWERS);
			BiomeHelper.addVegetation(context, BloomFeatures.GOLDEN_FIELDS_TREES);
		});

		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_STONE_BLOBS, context -> {
			context.getFeatures().addFeature(BloomFeatures.ORE_STONE, GenerationStep.Decoration.UNDERGROUND_ORES);
		});

		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_GRAVEL_BLOBS, context -> {
			context.getFeatures().addFeature(BloomFeatures.ORE_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);
		});

		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_BROMELIAD, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_BROMELIAD);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_PINK_ORCHID, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_PINK_ORCHID);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_BELLFLOWER, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_BELLFLOWER);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_HELLEBORE, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_HELLEBORE);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_LILY_OF_THE_VALLEY, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_LILY_OF_THE_VALLEY);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_HYDRANGEA, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_HYDRANGEA);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_CALLA_LILY, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_CALLA_LILY);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_DIANTHUS, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_DIANTHUS);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_GOLDENROD, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_GOLDENROD);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_ORANGE_DAISY, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_ORANGE_DAISY);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_SCILLA, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_SCILLA);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_HYACINTH, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_HYACINTH);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_QUEENCUP, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.FLOWER_QUEENCUP);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_SUCCULENT, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.PATCH_SUCCULENT);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_REEDS, context -> {
			BiomeHelper.addVegetation(context, BloomFeatures.PATCH_REEDS);
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_TAIGA_MUSIC, context -> {
			context.getEnvironmentAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(BloomSounds.MUSIC_BIOME_TAIGA)));
		});
		UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_OLD_GROWTH_TAIGA_MUSIC, context -> {
			context.getEnvironmentAttributes().set(
				EnvironmentAttributes.BACKGROUND_MUSIC,
				new BackgroundMusic(Musics.createGameMusic(BloomSounds.MUSIC_BIOME_OLD_GROWTH_TAIGA))
			);
		});
	}
}
