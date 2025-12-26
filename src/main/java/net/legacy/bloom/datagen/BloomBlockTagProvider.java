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
				.add(BloomBlocks.PINK_ORCHID);
		this.valueLookupBuilder(BlockTags.FLOWERS)
				.add(BloomBlocks.BELLFLOWER)
				.add(BloomBlocks.PINK_ORCHID)
				.add(BloomBlocks.BROMELIAD)
				.add(BloomBlocks.HELLEBORE);
		this.valueLookupBuilder(BlockTags.FLOWER_POTS)
				.add(BloomBlocks.POTTED_PINK_ORCHID)
				.add(BloomBlocks.POTTED_BROMELIAD)
				.add(BloomBlocks.POTTED_HELLEBORE);
	}
}