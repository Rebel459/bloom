package net.legacy.bloom.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.frozenblock.lib.datagen.api.FrozenBiomeTagProvider;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class BloomBiomeTagProvider extends FrozenBiomeTagProvider {

	public BloomBiomeTagProvider(FabricDataOutput output, CompletableFuture registries) {
		super(output, registries);
	}

	private ResourceKey<Biome> getBiome(String id) {
		return ResourceKey.create(this.registryKey, Identifier.parse(id));
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {

		// Normal
		this.builder(BloomBiomeTags.BLOOM_BIOMES)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.TROPICAL_RIVER)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.LUKEWARM_RIVER)
			.add(BloomBiomes.LUKEWARM_BEACH)
			.add(BloomBiomes.WINDSWEPT_JUNGLE)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.SNOWY_SHORE)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.add(BloomBiomes.GOLDEN_FOREST);

		this.builder(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE)
			.add(Biomes.JUNGLE)
			.add(Biomes.SPARSE_JUNGLE)
			.add(BloomBiomes.WINDSWEPT_JUNGLE)
			.addOptional(this.getBiome("wilderwild:birch_jungle"))
			.addOptional(this.getBiome("wilderwild:sparse_birch_jungle"));

		this.builder(BloomBiomeTags.IS_NON_SNOWY_TAIGA)
			.add(Biomes.TAIGA)
			.add(Biomes.OLD_GROWTH_PINE_TAIGA)
			.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
			.add(BloomBiomes.PINE_TAIGA)
			.addOptional(this.getBiome("wilderwild:birch_taiga"))
			.addOptional(this.getBiome("wilderwild:old_growth_birch_taiga"))
			.addOptional(this.getBiome("wilderwild:dark_taiga"));

		// Internal
		this.builder(BloomBiomeTags.INTERNAL_DEPTH_ADAPTED)
			.addTag(BloomBiomeTags.INTERNAL_STEEP)
			.addTag(BloomBiomeTags.INTERNAL_MOUNTAIN)
			.addTag(BloomBiomeTags.INTERNAL_STONY)
			.addTag(BloomBiomeTags.INTERNAL_BADLANDS)
			.addTag(BloomBiomeTags.INTERNAL_WINDSWEPT_HILL)
			.addTag(BloomBiomeTags.INTERNAL_WINDSWEPT_SAVANNA);

		this.builder(BloomBiomeTags.INTERNAL_STEEP)
			.add(Biomes.JAGGED_PEAKS)
			.add(Biomes.SNOWY_SLOPES);

		this.builder(BloomBiomeTags.INTERNAL_MOUNTAIN)
			.addTag(BloomBiomeTags.INTERNAL_STEEP)
			.add(Biomes.FROZEN_PEAKS);

		this.builder(BloomBiomeTags.INTERNAL_STONY)
			.addTag(BloomBiomeTags.INTERNAL_STONY_SHORE)
			.add(Biomes.STONY_PEAKS);
		this.builder(BloomBiomeTags.INTERNAL_STONY_SHORE)
			.add(Biomes.STONY_SHORE)
			.add(BloomBiomes.SNOWY_SHORE);

		this.builder(BloomBiomeTags.INTERNAL_BADLANDS)
			.addOptionalTag(BiomeTags.IS_BADLANDS);

		this.builder(BloomBiomeTags.INTERNAL_WINDSWEPT_HILL)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

		this.builder(BloomBiomeTags.INTERNAL_WINDSWEPT_SAVANNA)
			.add(Biomes.WINDSWEPT_SAVANNA);

        this.builder(BloomBiomeTags.INTERNAL_LESS_STRICT_DISKS)
			.addOptionalTag(BiomeTags.IS_JUNGLE)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.TROPICAL_RIVER)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH);

        this.builder(BloomBiomeTags.INTERNAL_WINDSWEPT_JUNGLE_FEATURES)
			.add(BloomBiomes.WINDSWEPT_JUNGLE);

		this.builder(BloomBiomeTags.INTERNAL_FEN_FEATURES)
			.add(BloomBiomes.FEN);

		this.builder(BloomBiomeTags.INTERNAL_GOLDEN_FOREST_FEATURES)
			.add(BloomBiomes.GOLDEN_FOREST);

		// Effects
        this.builder(BloomBiomeTags.HAS_WARM_COLORS)
			.add(Biomes.DESERT)
			.addOptionalTag(BiomeTags.IS_BADLANDS)
			.addOptional(this.getBiome("wilderwild:oasis"))
			.addOptional(this.getBiome("wilderwild:warm_beach"))
			.addOptional(this.getBiome("wilderwild:warm_river"));

        this.builder(BloomBiomeTags.HAS_LUKEWARM_COLORS)
			.addOptionalTag(BiomeTags.IS_SAVANNA)
			.addOptional(this.getBiome("wilderwild:arid_forest"))
			.addOptional(this.getBiome("wilderwild:parched_forest"));

		this.builder(BloomBiomeTags.HAS_TROPICAL_COLORS)
			.addOptionalTag(BiomeTags.IS_JUNGLE)
			.addOptional(this.getBiome("wilderwild:rainforest"));

		this.builder(BloomBiomeTags.HAS_COLD_COLORS)
			.addTag(BloomBiomeTags.IS_NON_SNOWY_TAIGA)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS)
			.addOptional(this.getBiome("wilderwild:temperate_rainforest"));

		this.builder(BloomBiomeTags.HAS_FROZEN_COLORS)
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
			.addOptional(this.getBiome("wilderwild:snowy_old_growth_pine_taiga"))
			.addOptional(this.getBiome("wilderwild:snowy_dying_forest"))
			.addOptional(this.getBiome("wilderwild:snowy_dying_mixed_forest"))
			.addOptional(this.getBiome("wilderwild:snowy_old_growth_pine_taiga"));

		// Surface Rules
		this.builder(BloomBiomeTags.HAS_SURFACE_GRAVEL)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH)
			.add(Biomes.SNOWY_BEACH)
			.add(Biomes.FROZEN_RIVER);

		this.builder(BloomBiomeTags.HAS_SURFACE_SAND)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.LUKEWARM_BEACH);

		this.builder(BloomBiomeTags.HAS_SURFACE_COARSE_DIRT)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE);

        this.builder(BloomBiomeTags.HAS_UNDERWATER_MUD)
			.addTag(BloomBiomeTags.HAS_SWAMP_MUD)
			.addOptionalTag(BiomeTags.IS_JUNGLE);

        this.builder(BloomBiomeTags.HAS_SWAMP_MUD)
			.add(BloomBiomes.FEN)
			.add(Biomes.SWAMP);

		this.builder(BloomBiomeTags.HAS_STRIP_COARSE_DIRT)
			.add(Biomes.SAVANNA)
			.add(Biomes.SAVANNA_PLATEAU)
			.add(BloomBiomes.GOLDEN_FOREST);

        this.builder(BloomBiomeTags.HAS_DEPTH_SANDSTONE)
			.add(Biomes.DESERT)
			.addOptional(this.getBiome("wilderwild:oasis"));

        this.builder(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE)
			.addOptionalTag(BiomeTags.IS_BADLANDS);

        this.builder(BloomBiomeTags.HAS_HIGHER_STONE)
			.add(Biomes.CHERRY_GROVE)
			.add(Biomes.MEADOW);

        this.builder(BloomBiomeTags.HAS_HIGHER_DEPTH)
			.addTag(BloomBiomeTags.HAS_HIGHER_STONE)
			.add(Biomes.SAVANNA_PLATEAU);

		// Features
		this.builder(BloomBiomeTags.NO_DEFAULT_FLOWERS)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(Biomes.SNOWY_BEACH)
			.add(Biomes.ICE_SPIKES)
			.add(BloomBiomes.FEN)
			.addOptionalTag(BiomeTags.IS_JUNGLE)
			.addOptional(this.getBiome("wilderwild:snowy_old_growth_pine_taiga"));

		this.builder(BloomBiomeTags.NO_PINE_TREES)
			.add(Biomes.TAIGA)
			.add(Biomes.SNOWY_TAIGA);

		this.builder(BloomBiomeTags.HAS_STONE_BLOBS)
			.addTag(BloomBiomeTags.HAS_DEPTH_SANDSTONE)
			.addTag(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE);

		this.builder(BloomBiomeTags.HAS_GRAVEL_BLOBS)
			.addOptionalTag(BiomeTags.IS_OVERWORLD);

		this.builder(BloomBiomeTags.HAS_BROMELIAD)
			.addTag(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE)
			.add(BloomBiomes.WARM_RIVER)
			.addOptional(this.getBiome("wilderwild:rainforest"));

		this.builder(BloomBiomeTags.HAS_PINK_ORCHID)
			.addTag(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE);

		this.builder(BloomBiomeTags.HAS_LILY_OF_THE_VALLEY)
			.addTag(BloomBiomeTags.IS_NON_SNOWY_TAIGA);

		this.builder(BloomBiomeTags.HAS_HELLEBORE)
			.addTag(BloomBiomeTags.IS_NON_SNOWY_TAIGA);

		this.builder(BloomBiomeTags.HAS_BELLFLOWER)
			.addTag(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE)
			.add(Biomes.DARK_FOREST)
			.add(Biomes.FLOWER_FOREST)
			.addOptional(this.getBiome("wilderwild:dark_birch_forest"))
			.addOptional(this.getBiome("wilderwild:flower_field"))
			.addOptional(this.getBiome("wilderwild:old_growth_dark_forest"));

        this.builder(BloomBiomeTags.HAS_HYDRANGEA)
			.add(Biomes.FOREST)
			.add(Biomes.FLOWER_FOREST)
			.add(Biomes.CHERRY_GROVE)
			.add(BloomBiomes.GOLDEN_FOREST)
			.addOptional(this.getBiome("wilderwild:mixed_forest"))
			.addOptional(this.getBiome("wilderwild:semi_birch_forest"))
			.addOptional(this.getBiome("wilderwild:flower_field"))
			.addOptional(this.getBiome("wilderwild:sparse_forest"));

        this.builder(BloomBiomeTags.HAS_CALLA_LILY)
			.addOptionalTag(BiomeTags.IS_SAVANNA)
			.add(Biomes.FLOWER_FOREST)
			.addOptional(this.getBiome("wilderwild:arid_forest"))
			.addOptional(this.getBiome("wilderwild:arid_savanna"))
			.addOptional(this.getBiome("wilderwild:flower_field"));

        this.builder(BloomBiomeTags.HAS_DIANTHUS)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.addOptional(this.getBiome("wilderwild:snowy_old_growth_pine_taiga"));

        this.builder(BloomBiomeTags.HAS_GOLDENROD)
			.add(Biomes.SUNFLOWER_PLAINS)
			.add(BloomBiomes.GOLDEN_FOREST);

        this.builder(BloomBiomeTags.HAS_ORANGE_DAISY)
			.add(Biomes.OLD_GROWTH_BIRCH_FOREST);

		this.builder(BloomBiomeTags.HAS_SCILLA)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(Biomes.ICE_SPIKES)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.addOptional(this.getBiome("wilderwild:snowy_old_growth_pine_taiga"));

		this.builder(BloomBiomeTags.HAS_HYACINTH)
			.add(Biomes.DARK_FOREST)
			.add(Biomes.SWAMP)
			.add(BloomBiomes.FEN);

		this.builder(BloomBiomeTags.HAS_QUEENCUP)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

        this.builder(BloomBiomeTags.HAS_SUCCULENT)
			.addOptionalTag(BiomeTags.IS_BADLANDS);

        this.builder(BloomBiomeTags.HAS_REEDS)
			.add(BloomBiomes.FEN)
			.addOptional(this.getBiome("wilderwild:cypress_wetlands"));

		// Music
		this.builder(BloomBiomeTags.HAS_TAIGA_MUSIC)
			.add(Biomes.TAIGA)
			.addOptional(this.getBiome("wilderwild:birch_taiga"))
			.addOptional(this.getBiome("wilderwild:dark_taiga"));

		this.builder(BloomBiomeTags.HAS_OLD_GROWTH_TAIGA_MUSIC)
			.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
			.add(Biomes.OLD_GROWTH_PINE_TAIGA)
			.addOptional(this.getBiome("wilderwild:old_growth_birch_taiga"));

		// Vanilla
		this.builder(BiomeTags.IS_OVERWORLD)
			.addTag(BloomBiomeTags.BLOOM_BIOMES);

		this.builder(BiomeTags.IS_RIVER)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.TROPICAL_RIVER)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.LUKEWARM_RIVER);

        this.builder(BiomeTags.IS_BEACH)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.LUKEWARM_BEACH);

		this.builder(BiomeTags.IS_JUNGLE)
			.add(BloomBiomes.WINDSWEPT_JUNGLE);

		this.builder(BiomeTags.HAS_JUNGLE_TEMPLE)
			.add(BloomBiomes.WINDSWEPT_JUNGLE);

		this.builder(BiomeTags.IS_TAIGA)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

        this.builder(BiomeTags.HAS_SWAMP_HUT)
			.add(BloomBiomes.FEN);

		this.builder(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.LUKEWARM_RIVER)
			.add(BloomBiomes.LUKEWARM_BEACH);

		this.builder(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.SNOWY_SHORE)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

		this.builder(BiomeTags.HAS_IGLOO)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

		this.builder(BiomeTags.HAS_RUINED_PORTAL_SWAMP)
			.add(BloomBiomes.FEN);

		this.builder(BiomeTags.HAS_TRAIL_RUINS)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

		this.builder(BiomeTags.HAS_VILLAGE_TAIGA)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.SNOWY_PINE_TAIGA);

		this.builder(BiomeTags.HAS_MINESHAFT)
			.addTag(BloomBiomeTags.BLOOM_BIOMES);

		this.builder(BiomeTags.HAS_TRIAL_CHAMBERS)
			.addTag(BloomBiomeTags.BLOOM_BIOMES);

		this.builder(BiomeTags.IS_FOREST)
			.add(BloomBiomes.GOLDEN_FOREST);

		// Conventional
		this.builder(ConventionalBiomeTags.IS_SWAMP)
			.add(BloomBiomes.FEN);

		this.builder(ConventionalBiomeTags.IS_WET_OVERWORLD)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.TROPICAL_BEACH)
			.add(BloomBiomes.TROPICAL_RIVER);

		this.builder(ConventionalBiomeTags.IS_TEMPERATE_OVERWORLD)
			.add(BloomBiomes.GOLDEN_FOREST)
			.add(BloomBiomes.PINE_TAIGA)
			.add(BloomBiomes.COLD_BEACH)
			.add(BloomBiomes.COLD_RIVER);

		this.builder(ConventionalBiomeTags.IS_COLD_OVERWORLD)
			.add(BloomBiomes.SNOWY_PINE_TAIGA)
			.add(BloomBiomes.SNOWY_SHORE);

		this.builder(ConventionalBiomeTags.IS_HOT_OVERWORLD)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.LUKEWARM_BEACH)
			.add(BloomBiomes.LUKEWARM_RIVER);

		this.builder(ConventionalBiomeTags.IS_DRY_OVERWORLD)
			.add(BloomBiomes.ARID_SHORE);

		this.builder(ConventionalBiomeTags.IS_WINDSWEPT)
			.add(BloomBiomes.WINDSWEPT_JUNGLE);
	}
}
