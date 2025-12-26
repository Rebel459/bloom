package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class BloomBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public BloomBlockTagProvider(@NotNull FabricDataOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider arg) {
		this.valueLookupBuilder(BlockTags.SMALL_FLOWERS)
				.add(BloomBlocks.PINK_ORCHID)
				.add(BloomBlocks.GOLDENROD)
				.add(BloomBlocks.CALLA_LILY)
				.add(BloomBlocks.ORANGE_DAISY);
		this.valueLookupBuilder(BlockTags.FLOWERS)
				.add(BloomBlocks.DIANTHUS)
				.add(BloomBlocks.GOLDENROD)
				.add(BloomBlocks.CALLA_LILY)
				.add(BloomBlocks.ORANGE_DAISY)
				.add(BloomBlocks.SCILLA)
				.add(BloomBlocks.BELLFLOWER)
				.add(BloomBlocks.PINK_ORCHID)
				.add(BloomBlocks.BROMELIAD)
				.add(BloomBlocks.HELLEBORE)
				.add(BloomBlocks.HYDRANGEA);
		this.valueLookupBuilder(BlockTags.FLOWER_POTS)
				.add(BloomBlocks.POTTED_DIANTHUS)
				.add(BloomBlocks.POTTED_GOLDENROD)
				.add(BloomBlocks.POTTED_CALLA_LILY)
				.add(BloomBlocks.POTTED_ORANGE_DAISY)
				.add(BloomBlocks.POTTED_SCILLA)
				.add(BloomBlocks.POTTED_PINK_ORCHID)
				.add(BloomBlocks.POTTED_BROMELIAD)
				.add(BloomBlocks.POTTED_HELLEBORE);
	}
}