package net.rebel459.bloom.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.registry.BloomBiomes;
import net.rebel459.bloom.tag.BloomBiomeTags;
import net.rebel459.unified.api.data.helper.TagGenerator;

public final class BloomBiomeTagProvider {

	private static final TagGenerator TAGS = Bloom.DATA.helpers().tags();

	public static void init() {

		// Normal
		TAGS.create(BloomBiomeTags.BLOOM_BIOMES)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.TROPICAL_RIVER)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.LUKEWARM_RIVER)
			.add(BloomBiomes.LUKEWARM_BEACH)
			.add(BloomBiomes.WINDSWEPT_JUNGLE)
			.add(BloomBiomes.SPARSE_WINDSWEPT_JUNGLE)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.SNOWY_SHORE)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.add(BloomBiomes.GOLDEN_FOREST)
			.add(BloomBiomes.GOLDEN_FIELDS)
			.add(BloomBiomes.GOLDEN_RIVER)
			.add(BloomBiomes.LAVENDER_FIELDS);

		TAGS.create(BloomBiomeTags.IS_NON_SNOWY_TAIGA)
			.add(Biomes.TAIGA)
			.add(Biomes.OLD_GROWTH_PINE_TAIGA)
			.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
			.add(BloomBiomes.PINE_TAIGA)
			.addOptional(getBiome("wilderwild:birch_taiga"))
			.addOptional(getBiome("wilderwild:old_growth_birch_taiga"))
			.addOptional(getBiome("wilderwild:dark_taiga"));

		// Internal
		TAGS.create(BloomBiomeTags.INTERNAL_DEPTH_ADAPTED)
			.add(BloomBiomeTags.INTERNAL_STEEP)
			.add(BloomBiomeTags.INTERNAL_MOUNTAIN)
			.add(BloomBiomeTags.INTERNAL_STONY)
			.add(BloomBiomeTags.INTERNAL_BADLANDS)
			.add(BloomBiomeTags.INTERNAL_WINDSWEPT_HILL)
			.add(BloomBiomeTags.INTERNAL_WINDSWEPT_SAVANNA);

		TAGS.create(BloomBiomeTags.INTERNAL_STEEP)
			.add(Biomes.JAGGED_PEAKS)
			.add(Biomes.SNOWY_SLOPES);

		TAGS.create(BloomBiomeTags.INTERNAL_MOUNTAIN)
			.add(BloomBiomeTags.INTERNAL_STEEP)
			.add(Biomes.FROZEN_PEAKS);

		TAGS.create(BloomBiomeTags.INTERNAL_STONY)
			.add(BloomBiomeTags.INTERNAL_STONY_SHORE)
			.add(Biomes.STONY_PEAKS);
		TAGS.create(BloomBiomeTags.INTERNAL_STONY_SHORE)
			.add(Biomes.STONY_SHORE)
			.add(BloomBiomes.SNOWY_SHORE);

		TAGS.create(BloomBiomeTags.INTERNAL_BADLANDS)
			.addOptional(BiomeTags.IS_BADLANDS);

		TAGS.create(BloomBiomeTags.INTERNAL_WINDSWEPT_HILL)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

		TAGS.create(BloomBiomeTags.INTERNAL_WINDSWEPT_SAVANNA)
			.add(Biomes.WINDSWEPT_SAVANNA);

		TAGS.create(BloomBiomeTags.INTERNAL_LESS_STRICT_DISKS)
			.addOptional(BiomeTags.IS_JUNGLE)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.TROPICAL_RIVER)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH);

		TAGS.create(BloomBiomeTags.INTERNAL_WINDSWEPT_JUNGLE_FEATURES)
			.add(BloomBiomes.WINDSWEPT_JUNGLE);

		TAGS.create(BloomBiomeTags.INTERNAL_SPARSE_WINDSWEPT_JUNGLE_FEATURES)
			.add(BloomBiomes.SPARSE_WINDSWEPT_JUNGLE);

		TAGS.create(BloomBiomeTags.INTERNAL_FEN_FEATURES)
			.add(BloomBiomes.FEN);

		TAGS.create(BloomBiomeTags.INTERNAL_GOLDEN_FOREST_FEATURES)
			.add(BloomBiomes.GOLDEN_FOREST);

		TAGS.create(BloomBiomeTags.INTERNAL_GOLDEN_FIELDS_FEATURES)
			.add(BloomBiomes.GOLDEN_FIELDS);

		TAGS.create(BloomBiomeTags.INTERNAL_LAVENDER_FIELDS_FEATURES)
			.add(BloomBiomes.LAVENDER_FIELDS);

		// Effects
		TAGS.create(BloomBiomeTags.HAS_WARM_COLORS)
			.add(Biomes.DESERT)
			.addOptional(BiomeTags.IS_BADLANDS)
			.addOptional(getBiome("wilderwild:oasis"))
			.addOptional(getBiome("wilderwild:warm_beach"))
			.addOptional(getBiome("wilderwild:warm_river"));

		TAGS.create(BloomBiomeTags.HAS_LUKEWARM_COLORS)
			.addOptional(BiomeTags.IS_SAVANNA)
			.addOptional(getBiome("wilderwild:arid_forest"))
			.addOptional(getBiome("wilderwild:parched_forest"));

		TAGS.create(BloomBiomeTags.HAS_TROPICAL_COLORS)
			.addOptional(BiomeTags.IS_JUNGLE)
			.addOptional(getBiome("wilderwild:rainforest"));

		TAGS.create(BloomBiomeTags.HAS_COLD_COLORS)
			.add(BloomBiomeTags.IS_NON_SNOWY_TAIGA)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS)
			.addOptional(getBiome("wilderwild:temperate_rainforest"));

		TAGS.create(BloomBiomeTags.HAS_FROZEN_COLORS)
			.add(Biomes.JAGGED_PEAKS)
			.add(Biomes.FROZEN_PEAKS)
			.add(Biomes.SNOWY_SLOPES)
			.add(Biomes.GROVE)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(Biomes.ICE_SPIKES)
			.add(Biomes.FROZEN_RIVER)
			.add(Biomes.FROZEN_OCEAN)
			.add(Biomes.DEEP_FROZEN_OCEAN)
			.addOptional(getBiome("wilderwild:snowy_old_growth_pine_taiga"))
			.addOptional(getBiome("wilderwild:snowy_dying_forest"))
			.addOptional(getBiome("wilderwild:snowy_dying_mixed_forest"))
			.addOptional(getBiome("wilderwild:snowy_old_growth_pine_taiga"));

		TAGS.create(BloomBiomeTags.HAS_MUSHROOM_COLORS)
			.add(Biomes.MUSHROOM_FIELDS);

		// Surface Rules
		TAGS.create(BloomBiomeTags.HAS_SURFACE_GRAVEL)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH)
			.add(Biomes.SNOWY_BEACH)
			.add(Biomes.FROZEN_RIVER);

		TAGS.create(BloomBiomeTags.HAS_SURFACE_SAND)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.LUKEWARM_BEACH);

		TAGS.create(BloomBiomeTags.HAS_SURFACE_COARSE_DIRT)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE);

		TAGS.create(BloomBiomeTags.HAS_UNDERWATER_MUD)
			.add(BloomBiomeTags.HAS_SWAMP_MUD)
			.addOptional(BiomeTags.IS_JUNGLE);

		TAGS.create(BloomBiomeTags.HAS_SWAMP_MUD)
			.add(BloomBiomes.FEN)
			.add(Biomes.SWAMP);

		TAGS.create(BloomBiomeTags.HAS_TAIGA_GRAVEL)
			.add(BiomeTags.IS_TAIGA)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.COLD_RIVER);

		TAGS.create(BloomBiomeTags.HAS_STRIP_COARSE_DIRT)
			.add(Biomes.SAVANNA)
			.add(Biomes.SAVANNA_PLATEAU)
			.add(BloomBiomes.GOLDEN_FOREST)
			.add(BloomBiomes.GOLDEN_FIELDS);

        TAGS.create(BloomBiomeTags.HAS_DEPTH_SANDSTONE)
			.add(Biomes.DESERT)
			.addOptional(getBiome("wilderwild:oasis"));

        TAGS.create(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE)
			.addOptional(BiomeTags.IS_BADLANDS);

        TAGS.create(BloomBiomeTags.HAS_HIGHER_STONE)
			.add(Biomes.CHERRY_GROVE)
			.add(Biomes.MEADOW);

        TAGS.create(BloomBiomeTags.HAS_HIGHER_DEPTH)
			.add(BloomBiomeTags.HAS_HIGHER_STONE)
			.add(Biomes.SAVANNA_PLATEAU);

		// Features
		TAGS.create(BloomBiomeTags.NO_DEFAULT_FLOWERS)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(Biomes.SNOWY_BEACH)
			.add(Biomes.ICE_SPIKES)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.LAVENDER_FIELDS)
			.addOptional(BiomeTags.IS_JUNGLE)
			.addOptional(getBiome("wilderwild:snowy_old_growth_pine_taiga"));

		TAGS.create(BloomBiomeTags.NO_BADLANDS_GRASS)
			.add(Biomes.DESERT);

		TAGS.create(BloomBiomeTags.NO_PINE_TREES)
			.add(Biomes.TAIGA)
			.add(Biomes.SNOWY_TAIGA);

		TAGS.create(BloomBiomeTags.HAS_STONE_BLOBS)
			.add(BloomBiomeTags.HAS_DEPTH_SANDSTONE)
			.add(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE);

		TAGS.create(BloomBiomeTags.HAS_GRAVEL_BLOBS)
			.addOptional(BiomeTags.IS_OVERWORLD);

		TAGS.create(BloomBiomeTags.HAS_BROMELIAD)
			.addOptional(BiomeTags.IS_JUNGLE)
			.add(BloomBiomes.WARM_RIVER)
			.addOptional(getBiome("wilderwild:rainforest"));

		TAGS.create(BloomBiomeTags.HAS_PINK_ORCHID)
			.addOptional(BiomeTags.IS_JUNGLE);

		TAGS.create(BloomBiomeTags.HAS_HELLEBORE)
			.add(BloomBiomeTags.IS_NON_SNOWY_TAIGA);

		TAGS.create(BloomBiomeTags.HAS_BELLFLOWER)
			.addOptional(BiomeTags.IS_JUNGLE)
			.add(Biomes.DARK_FOREST)
			.add(Biomes.FLOWER_FOREST)
			.addOptional(getBiome("wilderwild:dark_birch_forest"))
			.addOptional(getBiome("wilderwild:flower_field"))
			.addOptional(getBiome("wilderwild:old_growth_dark_forest"));

        TAGS.create(BloomBiomeTags.HAS_HYDRANGEA)
			.add(Biomes.FOREST)
			.add(Biomes.FLOWER_FOREST)
			.add(Biomes.CHERRY_GROVE)
			.add(BloomBiomes.GOLDEN_FOREST)
			.addOptional(getBiome("wilderwild:mixed_forest"))
			.addOptional(getBiome("wilderwild:semi_birch_forest"))
			.addOptional(getBiome("wilderwild:sparse_forest"));

        TAGS.create(BloomBiomeTags.HAS_CALLA_LILY)
			.addOptional(BiomeTags.IS_SAVANNA)
			.add(Biomes.FLOWER_FOREST)
			.addOptional(getBiome("wilderwild:arid_forest"))
			.addOptional(getBiome("wilderwild:arid_savanna"))
			.addOptional(getBiome("wilderwild:flower_field"));

        TAGS.create(BloomBiomeTags.HAS_DIANTHUS)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.addOptional(getBiome("wilderwild:snowy_old_growth_pine_taiga"));

        TAGS.create(BloomBiomeTags.HAS_GOLDENROD)
			.add(Biomes.SUNFLOWER_PLAINS)
			.add(BloomBiomes.GOLDEN_FOREST)
			.add(BloomBiomes.GOLDEN_FIELDS);

        TAGS.create(BloomBiomeTags.HAS_ORANGE_DAISY)
			.add(Biomes.OLD_GROWTH_BIRCH_FOREST);

		TAGS.create(BloomBiomeTags.HAS_SCILLA)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(Biomes.ICE_SPIKES)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.addOptional(getBiome("wilderwild:snowy_old_growth_pine_taiga"));

		TAGS.create(BloomBiomeTags.HAS_HYACINTH)
			.add(Biomes.DARK_FOREST)
			.add(Biomes.SWAMP)
			.add(BloomBiomes.FEN);

		TAGS.create(BloomBiomeTags.HAS_QUEENCUP)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

        TAGS.create(BloomBiomeTags.HAS_SUCCULENT)
			.addOptional(BiomeTags.IS_BADLANDS);

		TAGS.create(BloomBiomeTags.HAS_REEDS)
			.add(Biomes.SWAMP)
			.add(BloomBiomes.FEN);

		TAGS.create(BloomBiomeTags.HAS_WILD_COTTON)
			.addOptional(BiomeTags.IS_FOREST)
			.addOptional(BiomeTags.IS_JUNGLE)
			.addOptional(ConventionalBiomeTags.IS_PLAINS)
			.add(BloomBiomeTags.IS_NON_SNOWY_TAIGA);

		TAGS.create(BloomBiomeTags.HAS_LILY_OF_THE_VALLEY)
			.add(BloomBiomeTags.IS_NON_SNOWY_TAIGA);

		TAGS.create(BloomBiomeTags.HAS_ALLIUM)
			.add(BloomBiomes.LAVENDER_FIELDS);

		TAGS.create(BloomBiomeTags.HAS_TAIGA_FOLIAGE)
			.add(BiomeTags.IS_TAIGA);

		// Music
		TAGS.create(BloomBiomeTags.HAS_TAIGA_MUSIC)
			.add(Biomes.TAIGA)
			.addOptional(getBiome("wilderwild:birch_taiga"))
			.addOptional(getBiome("wilderwild:dark_taiga"));

		TAGS.create(BloomBiomeTags.HAS_OLD_GROWTH_TAIGA_MUSIC)
			.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
			.add(Biomes.OLD_GROWTH_PINE_TAIGA)
			.addOptional(getBiome("wilderwild:old_growth_birch_taiga"));

		// Vanilla
		TAGS.create(BiomeTags.IS_OVERWORLD)
			.add(BloomBiomeTags.BLOOM_BIOMES);

		TAGS.create(BiomeTags.IS_RIVER)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.TROPICAL_RIVER)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.LUKEWARM_RIVER)
			.add(BloomBiomes.GOLDEN_RIVER);

        TAGS.create(BiomeTags.IS_BEACH)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.LUKEWARM_BEACH);

		TAGS.create(BiomeTags.IS_JUNGLE)
			.add(BloomBiomes.WINDSWEPT_JUNGLE)
			.add(BloomBiomes.SPARSE_WINDSWEPT_JUNGLE);

		TAGS.create(BiomeTags.HAS_JUNGLE_TEMPLE)
			.add(BloomBiomes.WINDSWEPT_JUNGLE)
			.add(BloomBiomes.SPARSE_WINDSWEPT_JUNGLE);

		TAGS.create(BiomeTags.IS_TAIGA)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

        TAGS.create(BiomeTags.HAS_SWAMP_HUT)
			.add(BloomBiomes.FEN);

		TAGS.create(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.LUKEWARM_RIVER)
			.add(BloomBiomes.LUKEWARM_BEACH);

		TAGS.create(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.SNOWY_SHORE)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

		TAGS.create(BiomeTags.HAS_IGLOO)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

		TAGS.create(BiomeTags.HAS_RUINED_PORTAL_SWAMP)
			.add(BloomBiomes.FEN);

		TAGS.create(BiomeTags.HAS_TRAIL_RUINS)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

		TAGS.create(BiomeTags.HAS_VILLAGE_TAIGA)
			.add(BloomBiomes.PINE_TAIGA);

		TAGS.create(BiomeTags.HAS_MINESHAFT)
			.add(BloomBiomeTags.BLOOM_BIOMES);

		TAGS.create(BiomeTags.HAS_TRIAL_CHAMBERS)
			.add(BloomBiomeTags.BLOOM_BIOMES);

		TAGS.create(BiomeTags.IS_FOREST)
			.add(BloomBiomes.GOLDEN_FOREST);

		// Conventional
		TAGS.create(ConventionalBiomeTags.IS_SWAMP)
			.add(BloomBiomes.FEN);

		TAGS.create(ConventionalBiomeTags.IS_WET_OVERWORLD)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.TROPICAL_RIVER);

		TAGS.create(ConventionalBiomeTags.IS_TEMPERATE_OVERWORLD)
			.add(BloomBiomes.GOLDEN_FOREST)
			.add(BloomBiomes.GOLDEN_FIELDS)
			.add(BloomBiomes.GOLDEN_RIVER)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.LAVENDER_FIELDS);

		TAGS.create(ConventionalBiomeTags.IS_COLD_OVERWORLD)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.add(BloomBiomes.SNOWY_SHORE);

		TAGS.create(ConventionalBiomeTags.IS_HOT_OVERWORLD)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.LUKEWARM_BEACH)
			.add(BloomBiomes.LUKEWARM_RIVER);

		TAGS.create(ConventionalBiomeTags.IS_DRY_OVERWORLD)
			.add(BloomBiomes.ARID_SHORE);

		TAGS.create(ConventionalBiomeTags.IS_WINDSWEPT)
			.add(BloomBiomes.WINDSWEPT_JUNGLE)
			.add(BloomBiomes.SPARSE_WINDSWEPT_JUNGLE);

		TAGS.create(ConventionalBiomeTags.IS_PLAINS)
			.add(BloomBiomes.GOLDEN_FIELDS)
			.add(BloomBiomes.LAVENDER_FIELDS);
	}

	private static ResourceKey<Biome> getBiome(String id) {
		return ResourceKey.create(Registries.BIOME, Identifier.parse(id));
	}
}
