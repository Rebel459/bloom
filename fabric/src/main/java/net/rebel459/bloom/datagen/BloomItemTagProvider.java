package net.rebel459.bloom.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.tag.BloomItemTags;
import net.rebel459.unified.util.builder.WoodSet;

public final class BloomItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

	public BloomItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		this.valueLookupBuilder(BloomItemTags.SLEEPING_BAGS)
			.add(BloomItems.WHITE_SLEEPING_BAG.get())
			.add(BloomItems.ORANGE_SLEEPING_BAG.get())
			.add(BloomItems.MAGENTA_SLEEPING_BAG.get())
			.add(BloomItems.LIGHT_BLUE_SLEEPING_BAG.get())
			.add(BloomItems.YELLOW_SLEEPING_BAG.get())
			.add(BloomItems.LIME_SLEEPING_BAG.get())
			.add(BloomItems.PINK_SLEEPING_BAG.get())
			.add(BloomItems.GRAY_SLEEPING_BAG.get())
			.add(BloomItems.LIGHT_GRAY_SLEEPING_BAG.get())
			.add(BloomItems.CYAN_SLEEPING_BAG.get())
			.add(BloomItems.PURPLE_SLEEPING_BAG.get())
			.add(BloomItems.BLUE_SLEEPING_BAG.get())
			.add(BloomItems.BROWN_SLEEPING_BAG.get())
			.add(BloomItems.GREEN_SLEEPING_BAG.get())
			.add(BloomItems.RED_SLEEPING_BAG.get())
			.add(BloomItems.BLACK_SLEEPING_BAG.get());

		this.valueLookupBuilder(BloomItemTags.SLEEPING_BAG_MATERIALS)
			.add(BloomItems.YARN.get())
			.addOptionalTag(ItemTags.WOOL);

		this.valueLookupBuilder(BloomItemTags.WILD_CROPS)
			.add(BloomBlocks.WILD_COTTON.asItem());

		this.valueLookupBuilder(ItemTags.SMALL_FLOWERS)
			.add(BloomBlocks.PINK_ORCHID.asItem())
			.add(BloomBlocks.GOLDENROD.asItem())
			.add(BloomBlocks.CALLA_LILY.asItem())
			.add(BloomBlocks.ORANGE_DAISY.asItem())
			.add(BloomBlocks.HYACINTH.asItem())
			.add(BloomBlocks.QUEENCUP.asItem())
			.add(BloomBlocks.WILD_COTTON.asItem())
			.add(BloomBlocks.LAVENDER.asItem());

		this.valueLookupBuilder(ItemTags.FLOWERS)
			.add(BloomBlocks.DIANTHUS.asItem())
			.add(BloomBlocks.SCILLA.asItem())
			.add(BloomBlocks.BELLFLOWER.asItem())
			.add(BloomBlocks.BROMELIAD.asItem())
			.add(BloomBlocks.HELLEBORE.asItem())
			.add(BloomBlocks.HYDRANGEA.asItem());

		this.valueLookupBuilder(ItemTags.STONE_TOOL_MATERIALS)
			.add(BloomBlocks.DOLERITE.asItem());

		this.valueLookupBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
			.add(BloomBlocks.DOLERITE.asItem());

		this.valueLookupBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
			.add(BloomItems.COTTON_SEEDS.get());

		tagWoodSet(BloomBlocks.JACARANDA, BloomItemTags.JACARANDA_LOGS);
		tagWoodSet(BloomBlocks.GOLDEN_BIRCH, BloomItemTags.GOLDEN_BIRCH_LOGS);
		tagWoodSet(BloomBlocks.PINE, BloomItemTags.PINE_LOGS);
	}

	public void tagWoodSet(WoodSet woodSet, TagKey<Item> tag) {

		if (woodSet.hasWood()) {
			this.valueLookupBuilder(tag)
				.add(woodSet.getLog().asItem(), woodSet.getStrippedLog().asItem())
				.add(woodSet.getWood().asItem(), woodSet.getStrippedWood().asItem());
		}

		this.valueLookupBuilder(ItemTags.LOGS)
			.addOptionalTag(tag);

		this.valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
			.addOptionalTag(tag);

		if (woodSet.hasLeaves()) {
			this.valueLookupBuilder(ItemTags.LEAVES)
				.add(woodSet.getLeaves().asItem());
		}

		this.valueLookupBuilder(ItemTags.PLANKS)
			.add(woodSet.getPlanks().asItem());

		this.valueLookupBuilder(ItemTags.SIGNS)
			.add(woodSet.getSignItem().get());

		this.valueLookupBuilder(ItemTags.HANGING_SIGNS)
			.add(woodSet.getWallHangingSign().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_BUTTONS)
			.add(woodSet.getButton().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_DOORS)
			.add(woodSet.getDoor().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_FENCES)
			.add(woodSet.getFence().asItem());

		this.valueLookupBuilder(ItemTags.FENCE_GATES)
			.add(woodSet.getFenceGate().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
			.add(woodSet.getPressurePlate().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_SLABS)
			.add(woodSet.getSlab().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_STAIRS)
			.add(woodSet.getStairs().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS)
			.add(woodSet.getTrapdoor().asItem());

		if (woodSet.hasBoats()) {
			this.valueLookupBuilder(ItemTags.BOATS)
				.add(woodSet.getBoatItem().asItem());

			this.valueLookupBuilder(ItemTags.CHEST_BOATS)
				.add(woodSet.getChestBoatItem().asItem());
		}

		if (woodSet.hasSapling()) {
			this.valueLookupBuilder(ItemTags.SAPLINGS)
				.add(woodSet.getSapling().asItem());
		}
	}
}
