package net.legacy.bloom.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.frozenblock.lib.datagen.api.FrozenBiomeTagProvider;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

public final class BloomBiomeTagProvider extends FrozenBiomeTagProvider {

	public BloomBiomeTagProvider(FabricDataOutput output, CompletableFuture registries) {
		super(output, registries);
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
			.add(BloomBiomes.FEN);

		this.builder(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE)
			.add(Biomes.JUNGLE)
			.add(Biomes.SPARSE_JUNGLE)
			.add(BloomBiomes.WINDSWEPT_JUNGLE);

		this.builder(BloomBiomeTags.IS_NON_SNOWY_TAIGA)
			.add(Biomes.TAIGA)
			.add(Biomes.OLD_GROWTH_PINE_TAIGA)
			.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA);

		// Internal

		this.builder(BloomBiomeTags.INTERNAL_STEEP)
			.add(Biomes.JAGGED_PEAKS)
			.add(Biomes.SNOWY_SLOPES);

		this.builder(BloomBiomeTags.INTERNAL_MOUNTAIN)
			.addTag(BloomBiomeTags.INTERNAL_STEEP)
			.add(Biomes.FROZEN_PEAKS);

		this.builder(BloomBiomeTags.INTERNAL_STONY)
			.add(Biomes.STONY_PEAKS)
			.add(Biomes.STONY_SHORE);

        this.builder(BloomBiomeTags.HAS_LESS_STRICT_DISKS)
			.addOptionalTag(BiomeTags.IS_JUNGLE)
			.add(BloomBiomes.WARM_RIVER)
			.add(BloomBiomes.ARID_SHORE)
			.add(BloomBiomes.TROPICAL_RIVER)
			.add(BloomBiomes.COLD_RIVER)
			.add(BloomBiomes.COLD_BEACH);

        this.builder(BloomBiomeTags.HAS_WINDSWEPT_JUNGLE_FEATURES)
			.add(BloomBiomes.WINDSWEPT_JUNGLE);

        this.builder(BloomBiomeTags.HAS_FEN_FEATURES)
			.add(BloomBiomes.FEN);

		// Effects
        this.builder(BloomBiomeTags.HAS_WARM_COLORS)
			.add(Biomes.DESERT)
			.addOptionalTag(BiomeTags.IS_BADLANDS);

        this.builder(BloomBiomeTags.HAS_LUKEWARM_COLORS)
			.addOptionalTag(BiomeTags.IS_SAVANNA);

		this.builder(BloomBiomeTags.HAS_TROPICAL_COLORS)
			.addOptionalTag(BiomeTags.IS_JUNGLE);

		this.builder(BloomBiomeTags.HAS_COLD_COLORS)
			.addTag(BloomBiomeTags.IS_NON_SNOWY_TAIGA)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

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
			.add(Biomes.DEEP_FROZEN_OCEAN);

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
			.add(Biomes.SAVANNA_PLATEAU);

        this.builder(BloomBiomeTags.HAS_DEPTH_GRANITE)
			.add(Biomes.SAVANNA)
			.add(Biomes.SAVANNA_PLATEAU)
			.add(Biomes.WINDSWEPT_SAVANNA)
			.add(BloomBiomes.LUKEWARM_RIVER);

        this.builder(BloomBiomeTags.HAS_DEPTH_DIORITE);

        this.builder(BloomBiomeTags.HAS_DEPTH_ANDESITE)
			.addTag(BloomBiomeTags.IS_NON_SNOWY_TAIGA)
			.add(BloomBiomes.FEN)
			.add(BloomBiomes.COLD_RIVER);

        this.builder(BloomBiomeTags.HAS_DEPTH_DOLERITE)
			.add(Biomes.SNOWY_PLAINS)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.FROZEN_RIVER)
			.add(Biomes.ICE_SPIKES);

        this.builder(BloomBiomeTags.HAS_DEPTH_SANDSTONE)
			.add(Biomes.DESERT);

        this.builder(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE)
			.addOptionalTag(BiomeTags.IS_BADLANDS);

        this.builder(BloomBiomeTags.HAS_HIGHER_STONE)
			.add(Biomes.CHERRY_GROVE)
			.add(Biomes.MEADOW);

        this.builder(BloomBiomeTags.HAS_HIGHER_DEPTH)
			.addTag(BloomBiomeTags.HAS_HIGHER_STONE)
			.add(Biomes.SAVANNA_PLATEAU);

		this.builder(BloomBiomeTags.HAS_MODIFIED_STONE_TYPE)
			.addTag(BloomBiomeTags.HAS_DEPTH_GRANITE)
			.addTag(BloomBiomeTags.HAS_DEPTH_DIORITE)
			.addTag(BloomBiomeTags.HAS_DEPTH_DOLERITE)
			.addTag(BloomBiomeTags.HAS_DEPTH_GRANITE)
			.addTag(BloomBiomeTags.HAS_DEPTH_ANDESITE)
			.addTag(BloomBiomeTags.HAS_DEPTH_SANDSTONE)
			.addTag(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE);

		// Features

		this.builder(BloomBiomeTags.HAS_STONE_BLOBS)
			.addTag(BloomBiomeTags.HAS_DEPTH_SANDSTONE)
			.addTag(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE);

		this.builder(BloomBiomeTags.HAS_BROMELIAD)
			.addTag(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE)
			.add(BloomBiomes.WARM_RIVER);

		this.builder(BloomBiomeTags.HAS_PINK_ORCHID)
			.addTag(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE);

		this.builder(BloomBiomeTags.HAS_LILY_OF_THE_VALLEY)
			.addTag(BloomBiomeTags.IS_NON_SNOWY_TAIGA);

		this.builder(BloomBiomeTags.HAS_HELLEBORE)
			.addTag(BloomBiomeTags.IS_NON_SNOWY_TAIGA);

		this.builder(BloomBiomeTags.HAS_BELLFLOWER)
			.addTag(BloomBiomeTags.IS_NON_BAMBOO_JUNGLE)
			.add(Biomes.DARK_FOREST)
			.add(Biomes.FLOWER_FOREST);

        this.builder(BloomBiomeTags.HAS_HYDRANGEA)
			.add(Biomes.FOREST)
			.add(Biomes.FLOWER_FOREST)
			.add(Biomes.CHERRY_GROVE);

        this.builder(BloomBiomeTags.HAS_CALLA_LILY)
			.addOptionalTag(BiomeTags.IS_SAVANNA)
			.add(Biomes.FLOWER_FOREST);

        this.builder(BloomBiomeTags.HAS_DIANTHUS)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS);

        this.builder(BloomBiomeTags.HAS_GOLDENROD)
			.add(Biomes.SUNFLOWER_PLAINS);

        this.builder(BloomBiomeTags.HAS_ORANGE_DAISY)
			.add(Biomes.OLD_GROWTH_BIRCH_FOREST);

        this.builder(BloomBiomeTags.HAS_SCILLA)
			.add(Biomes.SNOWY_TAIGA)
			.add(Biomes.SNOWY_PLAINS)
			.add(Biomes.WINDSWEPT_FOREST)
			.add(Biomes.WINDSWEPT_HILLS)
			.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

        this.builder(BloomBiomeTags.HAS_SUCCULENT)
			.addOptionalTag(BiomeTags.IS_BADLANDS);

        this.builder(BloomBiomeTags.HAS_REEDS)
			.add(BloomBiomes.FEN);

		// Vanilla
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
			.add(BloomBiomes.FEN);

		this.builder(BiomeTags.HAS_MINESHAFT)
			.addTag(BloomBiomeTags.BLOOM_BIOMES);

		this.builder(BiomeTags.HAS_TRIAL_CHAMBERS)
			.addTag(BloomBiomeTags.BLOOM_BIOMES);
	}
}
