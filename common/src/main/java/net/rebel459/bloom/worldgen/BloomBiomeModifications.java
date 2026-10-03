package net.rebel459.bloom.worldgen;

import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.bloom.sound.BloomSounds;
import net.rebel459.bloom.tag.BloomBiomeTags;
import net.rebel459.bloom.util.BiomeHelper;
import net.rebel459.unified.api.core.UnifiedData;
import net.rebel459.unified.api.data.helper.BiomeModifierGenerator;
import java.util.function.Supplier;

public final class BloomBiomeModifications {

	public static void init() {
		create(BloomBiomeTags.NO_BADLANDS_GRASS).removeFeature(VegetationPlacements.PATCH_GRASS_BADLANDS, GenerationStep.Decoration.VEGETAL_DECORATION);

		create(BloomBiomeTags.HAS_WARM_COLORS).setWaterColor(BiomeHelper.Colors.WARM_WATER);
		create(BloomBiomeTags.HAS_LUKEWARM_COLORS)
			.setWaterColor(BiomeHelper.Colors.LUKEWARM_WATER)
			.setFoliageColor(BiomeHelper.Colors.LUKEWARM_FOLIAGE)
			.setGrassColor(BiomeHelper.Colors.LUKEWARM_GRASS);
		create(BloomBiomeTags.HAS_TROPICAL_COLORS).setWaterColor(BiomeHelper.Colors.TROPICAL_WATER);
		create(BloomBiomeTags.HAS_COLD_COLORS).setWaterColor(BiomeHelper.Colors.COLD_WATER);
		if (BloomConfig.get().worldgen.taiga_tweaks) {
			create(BiomeTags.IS_TAIGA).setGrassColor(BiomeHelper.Colors.TAIGA_GRASS);
		}
		create(BloomBiomeTags.HAS_FROZEN_COLORS).setWaterColor(BiomeHelper.Colors.FROZEN_WATER);
		create(BloomBiomeTags.HAS_MUSHROOM_COLORS).setWaterColor(BiomeHelper.Colors.MUSHROOM_WATER);

		create(BloomBiomeTags.HAS_SWAMP_MUD)
			.removeFeature(MiscOverworldPlacements.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES)
			.removeFeature(MiscOverworldPlacements.DISK_SAND, GenerationStep.Decoration.UNDERGROUND_ORES)
			.removeFeature(MiscOverworldPlacements.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES)
			.addFeature(BloomFeatures.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES)
			.addFeature(BloomFeatures.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);

		create("pine_trees_enabled", () -> BloomConfig.get().worldgen.pine_trees, BloomBiomeTags.NO_PINE_TREES)
			.removeFeature(VegetationPlacements.TREES_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.TREES_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION);

		create(BloomBiomeTags.HAS_TAIGA_FOLIAGE)
			.removeFeature(VegetationPlacements.PATCH_LARGE_FERN, GenerationStep.Decoration.VEGETAL_DECORATION)
			.removeFeature(VegetationPlacements.PATCH_GRASS_TAIGA_2, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.FERNS_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.GRASS_TAIGA, GenerationStep.Decoration.VEGETAL_DECORATION);

		create(BloomBiomeTags.NO_DEFAULT_FLOWERS)
			.removeFeature(VegetationPlacements.FLOWER_WARM, GenerationStep.Decoration.VEGETAL_DECORATION)
			.removeFeature(VegetationPlacements.FLOWER_DEFAULT, GenerationStep.Decoration.VEGETAL_DECORATION);

		create(BloomBiomeTags.INTERNAL_LESS_STRICT_DISKS)
			.removeFeature(MiscOverworldPlacements.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES)
			.removeFeature(MiscOverworldPlacements.DISK_SAND, GenerationStep.Decoration.UNDERGROUND_ORES)
			.removeFeature(MiscOverworldPlacements.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES)
			.addFeature(BloomFeatures.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES)
			.addFeature(BloomFeatures.DISK_SAND, GenerationStep.Decoration.UNDERGROUND_ORES)
			.addFeature(BloomFeatures.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);
		create(BloomBiomeTags.INTERNAL_WINDSWEPT_JUNGLE_FEATURES)
			.addFeature(BloomFeatures.WINDSWEPT_JUNGLE_TREES, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.WINDSWEPT_JUNGLE_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.INTERNAL_SPARSE_WINDSWEPT_JUNGLE_FEATURES)
			.addFeature(BloomFeatures.SPARSE_WINDSWEPT_JUNGLE_TREES, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.WINDSWEPT_JUNGLE_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.INTERNAL_FEN_FEATURES)
			.addFeature(BloomFeatures.FEN_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.FEN_TREES, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.FEN_WILDFLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.INTERNAL_GOLDEN_FOREST_FEATURES)
			.addFeature(BloomFeatures.GOLDEN_FOREST_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.GOLDEN_FOREST_TREES, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.GOLDEN_FOREST_WILDFLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.INTERNAL_GOLDEN_FIELDS_FEATURES)
			.addFeature(BloomFeatures.GOLDEN_FOREST_FLOWERS, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.GOLDEN_FIELDS_TREES, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.INTERNAL_LAVENDER_FIELDS_FEATURES)
			.addFeature(BloomFeatures.LAVENDER_FIELDS_TREES, GenerationStep.Decoration.VEGETAL_DECORATION)
			.addFeature(BloomFeatures.LAVENDER_FIELDS_LAVENDER, GenerationStep.Decoration.VEGETAL_DECORATION);

		create(BloomBiomeTags.HAS_STONE_BLOBS).addFeature(BloomFeatures.ORE_STONE, GenerationStep.Decoration.UNDERGROUND_ORES);

		create(BloomBiomeTags.HAS_GRAVEL_BLOBS).addFeature(BloomFeatures.ORE_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);

		create(BloomBiomeTags.HAS_BROMELIAD).addFeature(BloomFeatures.FLOWER_BROMELIAD, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_PINK_ORCHID).addFeature(BloomFeatures.FLOWER_PINK_ORCHID, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_BELLFLOWER).addFeature(BloomFeatures.FLOWER_BELLFLOWER, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_HELLEBORE).addFeature(BloomFeatures.FLOWER_HELLEBORE, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_HYDRANGEA).addFeature(BloomFeatures.FLOWER_HYDRANGEA, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_CALLA_LILY).addFeature(BloomFeatures.FLOWER_CALLA_LILY, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_DIANTHUS).addFeature(BloomFeatures.FLOWER_DIANTHUS, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_GOLDENROD).addFeature(BloomFeatures.FLOWER_GOLDENROD, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_ORANGE_DAISY).addFeature(BloomFeatures.FLOWER_ORANGE_DAISY, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_SCILLA).addFeature(BloomFeatures.FLOWER_SCILLA, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_HYACINTH).addFeature(BloomFeatures.FLOWER_HYACINTH, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_QUEENCUP).addFeature(BloomFeatures.FLOWER_QUEENCUP, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_SUCCULENT).addFeature(BloomFeatures.PATCH_SUCCULENT, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_REEDS).addFeature(BloomFeatures.PATCH_REEDS, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_LILY_OF_THE_VALLEY).addFeature(BloomFeatures.FLOWER_LILY_OF_THE_VALLEY, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_ALLIUM).addFeature(BloomFeatures.FLOWER_ALLIUM, GenerationStep.Decoration.VEGETAL_DECORATION);
		create(BloomBiomeTags.HAS_TAIGA_MUSIC).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(BloomSounds.MUSIC_BIOME_TAIGA)));
		create(BloomBiomeTags.HAS_OLD_GROWTH_TAIGA_MUSIC).setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(BloomSounds.MUSIC_BIOME_OLD_GROWTH_TAIGA)));
	}

	private static BiomeModifierGenerator.Builder create(TagKey<Biome> biomes) {
		return Bloom.DATA.helpers().biomeModifiers().create(biomes.location().getPath(), provider -> provider.getOrThrow(biomes));
	}

	private static BiomeModifierGenerator.Builder create(String path, Supplier<Boolean> value, TagKey<Biome> biomes) {
		return UnifiedData.create(Bloom.MOD_ID).requirement(path, value).build().helpers().biomeModifiers().create(biomes.location().getPath(), provider -> provider.getOrThrow(biomes));
	}
}
