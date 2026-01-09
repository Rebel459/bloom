package net.legacy.bloom.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.tag.BloomBlockTags;
import net.legacy.bloom.tag.BloomItemTags;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class BloomItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public BloomItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		this.valueLookupBuilder(BloomItemTags.SLEEPING_BAGS)
			.add(BloomItems.WHITE_SLEEPING_BAG)
			.add(BloomItems.ORANGE_SLEEPING_BAG)
			.add(BloomItems.MAGENTA_SLEEPING_BAG)
			.add(BloomItems.LIGHT_BLUE_SLEEPING_BAG)
			.add(BloomItems.YELLOW_SLEEPING_BAG)
			.add(BloomItems.LIME_SLEEPING_BAG)
			.add(BloomItems.PINK_SLEEPING_BAG)
			.add(BloomItems.GRAY_SLEEPING_BAG)
			.add(BloomItems.LIGHT_GRAY_SLEEPING_BAG)
			.add(BloomItems.CYAN_SLEEPING_BAG)
			.add(BloomItems.PURPLE_SLEEPING_BAG)
			.add(BloomItems.BLUE_SLEEPING_BAG)
			.add(BloomItems.BROWN_SLEEPING_BAG)
			.add(BloomItems.GREEN_SLEEPING_BAG)
			.add(BloomItems.RED_SLEEPING_BAG)
			.add(BloomItems.BLACK_SLEEPING_BAG);

		this.valueLookupBuilder(ItemTags.SMALL_FLOWERS)
			.add(BloomBlocks.PINK_ORCHID.asItem())
			.add(BloomBlocks.GOLDENROD.asItem())
			.add(BloomBlocks.CALLA_LILY.asItem())
			.add(BloomBlocks.ORANGE_DAISY.asItem())
			.add(BloomBlocks.HYACINTH.asItem())
			.add(BloomBlocks.QUEENCUP.asItem());

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

		tagWoodset(BloomBlocks.JACARANDA, BloomItemTags.JACARANDA_LOGS);
	}

	public void tagWoodset(WoodsetRegistry woodset, TagKey<Item> tag) {

		this.valueLookupBuilder(tag)
			.add(woodset.getLog().asItem(), woodset.getStrippedLog().asItem())
			.add(woodset.getWood().asItem(), woodset.getStrippedWood().asItem());

		this.valueLookupBuilder(ItemTags.LOGS)
			.addOptionalTag(tag);

		this.valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
			.addOptionalTag(tag);

		this.valueLookupBuilder(BloomItemTags.JACARANDA_LOGS)
			.add(woodset.getLog().asItem(), woodset.getStrippedLog().asItem())
			.add(woodset.getWood().asItem(), woodset.getStrippedWood().asItem());

		this.valueLookupBuilder(ItemTags.LOGS)
			.addOptionalTag(BloomItemTags.JACARANDA_LOGS);

		this.valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
			.addOptionalTag(BloomItemTags.JACARANDA_LOGS);

		this.valueLookupBuilder(ItemTags.LEAVES)
			.add(woodset.getLeaves().asItem());

		this.valueLookupBuilder(ItemTags.PLANKS)
			.add(woodset.getPlanks().asItem());

		this.valueLookupBuilder(ItemTags.SIGNS)
			.add(woodset.getSignItem());

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
