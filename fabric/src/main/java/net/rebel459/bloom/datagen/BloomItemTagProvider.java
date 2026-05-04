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
import net.rebel459.bloom.util.WoodsetRegistry;

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
			.add(BloomBlocks.WILD_COTTON.asItem());

		this.valueLookupBuilder(ItemTags.FLOWERS)
			.add(BloomBlocks.DIANTHUS.asItem())
			.add(BloomBlocks.SCILLA.asItem())
			.add(BloomBlocks.BELLFLOWER.asItem())
			.add(BloomBlocks.BROMELIAD.asItem())
			.add(BloomBlocks.HELLEBORE.asItem())
			.add(BloomBlocks.HYDRANGEA.asItem());

		this.valueLookupBuilder(ItemTags.SAPLINGS)
			.add(BloomBlocks.JACARANDA_SAPLING.asItem());

		this.valueLookupBuilder(ItemTags.STONE_TOOL_MATERIALS)
			.add(BloomBlocks.DOLERITE.asItem());

		this.valueLookupBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
			.add(BloomBlocks.DOLERITE.asItem());

		this.valueLookupBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
			.add(BloomItems.COTTON_SEEDS.get());

		tagWoodset(BloomBlocks.JACARANDA, BloomItemTags.JACARANDA_LOGS);
		tagWoodset(BloomBlocks.GOLDEN_BIRCH, BloomItemTags.GOLDEN_BIRCH_LOGS);
		tagWoodset(BloomBlocks.PINE, BloomItemTags.PINE_LOGS);
	}

	public void tagWoodset(WoodsetRegistry woodset, TagKey<Item> tag) {

		this.valueLookupBuilder(tag)
			.add(woodset.getLog().asItem(), woodset.getStrippedLog().asItem())
			.add(woodset.getWood().asItem(), woodset.getStrippedWood().asItem());

		this.valueLookupBuilder(ItemTags.LOGS)
			.addOptionalTag(tag);

		this.valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
			.addOptionalTag(tag);

		this.valueLookupBuilder(ItemTags.LEAVES)
			.add(woodset.getLeaves().asItem());

		this.valueLookupBuilder(ItemTags.PLANKS)
			.add(woodset.getPlanks().asItem());

		this.valueLookupBuilder(ItemTags.SIGNS)
			.add(woodset.getSignItem().get());

		this.valueLookupBuilder(ItemTags.HANGING_SIGNS)
			.add(woodset.getWallHangingSign().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_BUTTONS)
			.add(woodset.getButton().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_DOORS)
			.add(woodset.getDoor().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_FENCES)
			.add(woodset.getFence().asItem());

		this.valueLookupBuilder(ItemTags.FENCE_GATES)
			.add(woodset.getFenceGate().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
			.add(woodset.getPressurePlate().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_SLABS)
			.add(woodset.getSlab().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_STAIRS)
			.add(woodset.getStairs().asItem());

		this.valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS)
			.add(woodset.getTrapDoor().asItem());
	}
}
