package net.rebel459.bloom.datagen;

import net.minecraft.tags.ItemTags;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.tag.BloomItemTags;
import net.rebel459.unified.api.data.helper.TagGenerator;

public final class BloomItemTagProvider {

	private static final TagGenerator TAGS = Bloom.DATA.helpers().tags();

	public static void init() {
		TAGS.create(BloomItemTags.SLEEPING_BAGS)
			.add(BloomItems.WHITE_SLEEPING_BAG.key())
			.add(BloomItems.ORANGE_SLEEPING_BAG.key())
			.add(BloomItems.MAGENTA_SLEEPING_BAG.key())
			.add(BloomItems.LIGHT_BLUE_SLEEPING_BAG.key())
			.add(BloomItems.YELLOW_SLEEPING_BAG.key())
			.add(BloomItems.LIME_SLEEPING_BAG.key())
			.add(BloomItems.PINK_SLEEPING_BAG.key())
			.add(BloomItems.GRAY_SLEEPING_BAG.key())
			.add(BloomItems.LIGHT_GRAY_SLEEPING_BAG.key())
			.add(BloomItems.CYAN_SLEEPING_BAG.key())
			.add(BloomItems.PURPLE_SLEEPING_BAG.key())
			.add(BloomItems.BLUE_SLEEPING_BAG.key())
			.add(BloomItems.BROWN_SLEEPING_BAG.key())
			.add(BloomItems.GREEN_SLEEPING_BAG.key())
			.add(BloomItems.RED_SLEEPING_BAG.key())
			.add(BloomItems.BLACK_SLEEPING_BAG.key());

		TAGS.create(BloomItemTags.SLEEPING_BAG_MATERIALS)
			.add(BloomItems.YARN.key())
			.addOptional(ItemTags.WOOL);

		TAGS.create(BloomItemTags.WILD_CROPS)
			.add(BloomBlocks.WILD_COTTON.blockItemId().item());

		TAGS.create(ItemTags.SMALL_FLOWERS)
			.add(BloomBlocks.PINK_ORCHID.blockItemId().item())
			.add(BloomBlocks.GOLDENROD.blockItemId().item())
			.add(BloomBlocks.CALLA_LILY.blockItemId().item())
			.add(BloomBlocks.ORANGE_DAISY.blockItemId().item())
			.add(BloomBlocks.HYACINTH.blockItemId().item())
			.add(BloomBlocks.QUEENCUP.blockItemId().item())
			.add(BloomBlocks.WILD_COTTON.blockItemId().item())
			.add(BloomBlocks.LAVENDER.blockItemId().item());

		TAGS.create(ItemTags.FLOWERS)
			.add(BloomBlocks.DIANTHUS.blockItemId().item())
			.add(BloomBlocks.SCILLA.blockItemId().item())
			.add(BloomBlocks.BELLFLOWER.blockItemId().item())
			.add(BloomBlocks.BROMELIAD.blockItemId().item())
			.add(BloomBlocks.HELLEBORE.blockItemId().item())
			.add(BloomBlocks.HYDRANGEA.blockItemId().item());

		TAGS.create(ItemTags.STONE_TOOL_MATERIALS)
			.add(BloomBlocks.DOLERITE.blockItemId().item());

		TAGS.create(ItemTags.STONE_CRAFTING_MATERIALS)
			.add(BloomBlocks.DOLERITE.blockItemId().item());

		TAGS.create(ItemTags.VILLAGER_PLANTABLE_SEEDS)
			.add(BloomItems.COTTON_SEEDS.key());
	}
}
