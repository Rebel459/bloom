package net.rebel459.bloom.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.bloom.util.WoodsetRegistry;
import net.minecraft.world.item.Items;
import net.rebel459.unified.platform.UnifiedHelpers;
import net.rebel459.unified.util.CreativeModeTabs;

public class BloomCreativeInventory {
    public static void init() {
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(
			CreativeModeTabs.COLORED_BLOCKS,
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
		addSleepingBags(CreativeModeTabs.COLORED_BLOCKS);
		addSleepingBags(CreativeModeTabs.FUNCTIONAL_BLOCKS);
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(
			CreativeModeTabs.BUILDING_BLOCKS,
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
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.NATURAL_BLOCKS, Items.ANDESITE, BloomBlocks.DOLERITE);
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.NATURAL_BLOCKS, Items.BEETROOT_SEEDS, BloomItems.COTTON_SEEDS);
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(
			CreativeModeTabs.NATURAL_BLOCKS,
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
			BloomBlocks.QUEENCUP
		);
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(
			CreativeModeTabs.NATURAL_BLOCKS,
			Items.CACTUS_FLOWER,
			BloomBlocks.SUCCULENT
		);
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(
			CreativeModeTabs.NATURAL_BLOCKS,
			Items.PEONY,
			BloomBlocks.BELLFLOWER,
			BloomBlocks.HYDRANGEA
		);
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(
			CreativeModeTabs.NATURAL_BLOCKS,
			Items.LARGE_FERN,
			BloomBlocks.REEDS
		);
		addOres(BloomBlocks.TUFF_ORES);
		addOres(BloomBlocks.DOLERITE_ORES);
		addOres(BloomBlocks.GRANITE_ORES);
		addOres(BloomBlocks.DIORITE_ORES);
		addOres(BloomBlocks.ANDESITE_ORES);
		addOres(BloomBlocks.RED_SANDSTONE_ORES);
		addOres(BloomBlocks.SANDSTONE_ORES);
		UnifiedHelpers.CREATIVE_ENTRIES.insertBefore(CreativeModeTabs.INGREDIENTS, Items.STRING, BloomItems.COTTON, BloomItems.YARN);

		WoodsetRegistry.addToBuildingTab(Items.CHERRY_BUTTON, BloomBlocks.JACARANDA);
		WoodsetRegistry.addToNaturalTab(Items.CHERRY_SAPLING, BloomBlocks.JACARANDA, BloomBlocks.JACARANDA_SAPLING);
		WoodsetRegistry.addToFunctionalTab(Items.CHERRY_HANGING_SIGN, BloomBlocks.JACARANDA);
		WoodsetRegistry.addToUtilitiesTab(Items.CHERRY_CHEST_BOAT, BloomBlocks.JACARANDA);

		WoodsetRegistry.addToBuildingTab(Items.BIRCH_BUTTON, BloomBlocks.GOLDEN_BIRCH);
		WoodsetRegistry.addToNaturalTab(Items.BIRCH_SAPLING, BloomBlocks.GOLDEN_BIRCH, BloomBlocks.GOLDEN_BIRCH_SAPLING);
		WoodsetRegistry.addToFunctionalTab(Items.BIRCH_HANGING_SIGN, BloomBlocks.GOLDEN_BIRCH);
		WoodsetRegistry.addToUtilitiesTab(Items.BIRCH_CHEST_BOAT, BloomBlocks.GOLDEN_BIRCH);

		WoodsetRegistry.addToBuildingTab(Items.SPRUCE_BUTTON, BloomBlocks.PINE);
		WoodsetRegistry.addToNaturalTab(Items.SPRUCE_SAPLING, BloomBlocks.PINE, BloomBlocks.PINE_SAPLING);
		WoodsetRegistry.addToFunctionalTab(Items.SPRUCE_HANGING_SIGN, BloomBlocks.PINE);
		WoodsetRegistry.addToUtilitiesTab(Items.SPRUCE_CHEST_BOAT, BloomBlocks.PINE);
    }

    public static void addOres(StoneOresRegistry ores) {
        ores.getOresMap().forEach((type, block) -> {
			UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.NATURAL_BLOCKS, type.baseBlock.asItem(), block.asItem());
        });
    }

	public static void addSleepingBags(ResourceKey<CreativeModeTab> tab) {
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(
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
