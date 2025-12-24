package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.frozenblock.lib.datagen.api.FrozenBiomeTagProvider;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class BloomBiomeTagProvider extends FrozenBiomeTagProvider {
	public BloomBiomeTagProvider(@NotNull FabricDataOutput output, @NotNull CompletableFuture registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider arg) {
		this.builder(BloomBiomeTags.IS_GRAVELLY)
				.add(BloomBiomes.COLD_RIVER)
				.add(BloomBiomes.COLD_BEACH)
				.add(Biomes.SNOWY_BEACH)
				.add(Biomes.FROZEN_RIVER);

		this.builder(BloomBiomeTags.IS_SANDY)
				.add(BloomBiomes.TROPICAL_BEACH);

		this.builder(BloomBiomeTags.IS_COARSE)
				.add(BloomBiomes.ARID_RIVER)
				.add(BloomBiomes.ARID_SHORE);

		this.builder(BloomBiomeTags.MODIFIED_DESERTS)
				.add(Biomes.DESERT)
				.add(Biomes.BADLANDS)
				.add(Biomes.ERODED_BADLANDS)
				.add(Biomes.WOODED_BADLANDS);

		this.builder(BloomBiomeTags.MODIFIED_JUNGLES)
				.add(Biomes.JUNGLE)
				.add(Biomes.BAMBOO_JUNGLE)
				.add(Biomes.SPARSE_JUNGLE);

		this.builder(BloomBiomeTags.MODIFIED_COLD_BIOMES)
				.add(Biomes.TAIGA)
				.add(Biomes.OLD_GROWTH_PINE_TAIGA)
				.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
				.add(Biomes.WINDSWEPT_FOREST)
				.add(Biomes.WINDSWEPT_HILLS)
				.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);

		this.builder(BloomBiomeTags.MODIFIED_FROZEN_BIOMES)
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

		this.builder(BiomeTags.IS_RIVER)
				.add(BloomBiomes.ARID_RIVER)
				.add(BloomBiomes.TROPICAL_RIVER)
				.add(BloomBiomes.COLD_RIVER);

		this.builder(BiomeTags.IS_BEACH)
				.add(BloomBiomes.TROPICAL_BEACH)
				.add(BloomBiomes.COLD_BEACH);

		this.builder(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS)
				.add(BloomBiomes.ARID_RIVER)
				.add(BloomBiomes.ARID_SHORE);

		this.builder(BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS)
				.add(BloomBiomes.COLD_RIVER)
				.add(BloomBiomes.COLD_BEACH);
	}
}