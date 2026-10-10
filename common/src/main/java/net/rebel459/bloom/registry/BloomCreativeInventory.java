package net.rebel459.bloom.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.util.BloomData;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.unified.api.data.helper.CreativeEntryGenerator;
import net.rebel459.unified.api.registry.CreativeModeTabIds;

public class BloomCreativeInventory {
    public static void init() {
		var creativeEntries = Bloom.DATA.helpers().creativeEntries().create("bloom_additions");
		creativeEntries.insertAfter(
			CreativeModeTabIds.COLORED_BLOCKS,
			Items.PINK_CARPET,
			BloomBlocks.WHITE_RUG,
			BloomBlocks.LIGHT_GRAY_RUG,
			BloomBlocks.GRAY_RUG,
			BloomBlocks.BLACK_RUG,
			BloomBlocks.BROWN_RUG,
			BloomBlocks.RED_RUG,
			BloomBlocks.ORANGE_RUG,
			BloomBlocks.YELLOW_RUG,
			BloomBlocks.LIME_RUG,
			BloomBlocks.GREEN_RUG,
			BloomBlocks.CYAN_RUG,
			BloomBlocks.LIGHT_BLUE_RUG,
			BloomBlocks.BLUE_RUG,
			BloomBlocks.PURPLE_RUG,
			BloomBlocks.MAGENTA_RUG,
			BloomBlocks.PINK_RUG
		);
		addSleepingBags(creativeEntries, CreativeModeTabIds.COLORED_BLOCKS);
		addSleepingBags(creativeEntries, CreativeModeTabIds.FUNCTIONAL_BLOCKS);
		creativeEntries.insertAfter(
			CreativeModeTabIds.BUILDING_BLOCKS,
			Items.POLISHED_ANDESITE_SLAB,
			BloomBlocks.DOLERITE,
			BloomBlocks.POLISHED_DOLERITE,
			BloomBlocks.POLISHED_DOLERITE_STAIRS,
			BloomBlocks.POLISHED_DOLERITE_SLAB,
			BloomBlocks.POLISHED_DOLERITE_WALL,
			BloomBlocks.DOLERITE_BRICKS,
			BloomBlocks.DOLERITE_BRICK_STAIRS,
			BloomBlocks.DOLERITE_BRICK_SLAB,
			BloomBlocks.DOLERITE_BRICK_WALL,
			BloomBlocks.DOLERITE_TILES,
			BloomBlocks.DOLERITE_TILE_STAIRS,
			BloomBlocks.DOLERITE_TILE_SLAB,
			BloomBlocks.DOLERITE_TILE_WALL
		);
		creativeEntries.insertAfter(CreativeModeTabIds.NATURAL_BLOCKS, Items.ANDESITE, BloomBlocks.DOLERITE);
		creativeEntries.insertAfter(CreativeModeTabIds.NATURAL_BLOCKS, Items.BEETROOT_SEEDS, BloomItems.COTTON_SEEDS);
		creativeEntries.insertAfter(
			CreativeModeTabIds.NATURAL_BLOCKS,
			Items.LILY_OF_THE_VALLEY,
			BloomBlocks.HELLEBORE,
			BloomBlocks.BROMELIAD,
			BloomBlocks.PINK_ORCHID,
			BloomBlocks.CALLA_LILY,
			BloomBlocks.DIANTHUS,
			BloomBlocks.GOLDENROD,
			BloomBlocks.ORANGE_DAISY,
			BloomBlocks.SCILLA,
			BloomBlocks.HYACINTH,
			BloomBlocks.QUEENCUP,
			BloomBlocks.LAVENDER
		);
		creativeEntries.insertAfter(
			CreativeModeTabIds.NATURAL_BLOCKS,
			Items.CACTUS_FLOWER,
			BloomBlocks.SUCCULENT
		);
		creativeEntries.insertAfter(
			CreativeModeTabIds.NATURAL_BLOCKS,
			Items.PEONY,
			BloomBlocks.BELLFLOWER,
			BloomBlocks.HYDRANGEA
		);
		creativeEntries.insertAfter(
			CreativeModeTabIds.NATURAL_BLOCKS,
			Items.LARGE_FERN,
			BloomBlocks.REEDS
		);
		addOres(creativeEntries, BloomBlocks.TUFF_ORES);
		addOres(creativeEntries, BloomBlocks.DOLERITE_ORES);
		addOres(creativeEntries, BloomBlocks.GRANITE_ORES);
		addOres(creativeEntries, BloomBlocks.DIORITE_ORES);
		addOres(creativeEntries, BloomBlocks.ANDESITE_ORES);
		addOres(creativeEntries, BloomBlocks.RED_SANDSTONE_ORES);
		addOres(creativeEntries, BloomBlocks.SANDSTONE_ORES);
		creativeEntries.insertBefore(CreativeModeTabIds.INGREDIENTS, Items.STRING, BloomItems.COTTON, BloomItems.YARN);

		var farmersDelight = BloomData.FARMERS_DELIGHT.helpers().creativeEntries().create("farmers_delight_integration_additions");
		farmersDelight.insertBefore(CreativeModeTabIds.NATURAL_BLOCKS, Items.WHEAT_SEEDS, BloomBlocks.WILD_COTTON);
	}

    public static void addOres(CreativeEntryGenerator.Builder creativeEntries, StoneOresRegistry ores) {
        ores.getOresMap().forEach((type, block) -> {
			creativeEntries.insertAfter(CreativeModeTabIds.NATURAL_BLOCKS, type.baseBlock.asItem(), block.asItem());
        });
    }

	public static void addSleepingBags(CreativeEntryGenerator.Builder creativeEntries, ResourceKey<CreativeModeTab> tab) {
		creativeEntries.insertAfter(
			tab,
			Items.PINK_BED,
			BloomItems.WHITE_SLEEPING_BAG,
			BloomItems.LIGHT_GRAY_SLEEPING_BAG,
			BloomItems.GRAY_SLEEPING_BAG,
			BloomItems.BLACK_SLEEPING_BAG,
			BloomItems.BROWN_SLEEPING_BAG,
			BloomItems.RED_SLEEPING_BAG,
			BloomItems.ORANGE_SLEEPING_BAG,
			BloomItems.YELLOW_SLEEPING_BAG,
			BloomItems.LIME_SLEEPING_BAG,
			BloomItems.GREEN_SLEEPING_BAG,
			BloomItems.CYAN_SLEEPING_BAG,
			BloomItems.LIGHT_BLUE_SLEEPING_BAG,
			BloomItems.BLUE_SLEEPING_BAG,
			BloomItems.PURPLE_SLEEPING_BAG,
			BloomItems.MAGENTA_SLEEPING_BAG,
			BloomItems.PINK_SLEEPING_BAG
		);
	}
}
