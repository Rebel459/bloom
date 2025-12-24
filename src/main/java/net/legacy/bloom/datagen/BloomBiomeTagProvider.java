package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.frozenblock.lib.datagen.api.FrozenBiomeTagProvider;
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
		this.builder(BloomBiomeTags.MODIFIED_DESERTS)
				.add(Biomes.DESERT)
				.add(Biomes.BADLANDS);

		this.builder(BloomBiomeTags.MODIFIED_JUNGLES)
				.add(Biomes.JUNGLE)
				.add(Biomes.BAMBOO_JUNGLE)
				.add(Biomes.SPARSE_JUNGLE);
	}
}