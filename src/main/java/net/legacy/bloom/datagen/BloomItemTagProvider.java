package net.legacy.bloom.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.tag.BloomBlockTags;
import net.legacy.bloom.tag.BloomItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.NotNull;

public final class BloomItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public BloomItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		this.valueLookupBuilder(ItemTags.SMALL_FLOWERS)
			.add(BloomBlocks.PINK_ORCHID.asItem())
			.add(BloomBlocks.GOLDENROD.asItem())
			.add(BloomBlocks.CALLA_LILY.asItem())
			.add(BloomBlocks.ORANGE_DAISY.asItem());

		this.valueLookupBuilder(ItemTags.FLOWERS)
			.add(BloomBlocks.DIANTHUS.asItem())
			.add(BloomBlocks.GOLDENROD.asItem())
			.add(BloomBlocks.CALLA_LILY.asItem())
			.add(BloomBlocks.ORANGE_DAISY.asItem())
			.add(BloomBlocks.SCILLA.asItem())
			.add(BloomBlocks.BELLFLOWER.asItem())
			.add(BloomBlocks.PINK_ORCHID.asItem())
			.add(BloomBlocks.BROMELIAD.asItem())
			.add(BloomBlocks.HELLEBORE.asItem())
			.add(BloomBlocks.HYDRANGEA.asItem());

		this.valueLookupBuilder(BloomItemTags.JACARANDA_LOGS)
			.add(BloomBlocks.JACARANDA_LOG.asItem(), BloomBlocks.STRIPPED_JACARANDA_LOG.asItem())
			.add(BloomBlocks.JACARANDA_WOOD.asItem(), BloomBlocks.STRIPPED_JACARANDA_WOOD.asItem());

		this.valueLookupBuilder(ItemTags.LOGS)
			.addOptionalTag(BloomItemTags.JACARANDA_LOGS);

		this.valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
			.addOptionalTag(BloomItemTags.JACARANDA_LOGS);

		this.valueLookupBuilder(ItemTags.LEAVES)
			.add(BloomBlocks.JACARANDA_LEAVES.asItem());

		this.valueLookupBuilder(ItemTags.PLANKS)
			.add(BloomBlocks.JACARANDA_PLANKS.asItem());

		this.valueLookupBuilder(ItemTags.SAPLINGS)
			.add(BloomBlocks.JACARANDA_SAPLING.asItem());

		this.valueLookupBuilder(ItemTags.SIGNS)
			.add(BloomBlocks.JACARANDA_SIGN.asItem());

		this.valueLookupBuilder(ItemTags.HANGING_SIGNS)
			.add(BloomBlocks.JACARANDA_WALL_HANGING_SIGN.asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_BUTTONS)
			.add(BloomBlocks.JACARANDA_BUTTON.asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_DOORS)
			.add(BloomBlocks.JACARANDA_DOOR.asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_FENCES)
			.add(BloomBlocks.JACARANDA_FENCE.asItem());

		this.valueLookupBuilder(ItemTags.FENCE_GATES)
			.add(BloomBlocks.JACARANDA_FENCE_GATE.asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
			.add(BloomBlocks.JACARANDA_PRESSURE_PLATE.asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_SLABS)
			.add(BloomBlocks.JACARANDA_SLAB.asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_STAIRS)
			.add(BloomBlocks.JACARANDA_STAIRS.asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS)
			.add(BloomBlocks.JACARANDA_TRAPDOOR.asItem());
	}
}
