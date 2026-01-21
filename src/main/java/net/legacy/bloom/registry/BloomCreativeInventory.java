package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.legacy.bloom.util.StoneOresRegistry;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class BloomCreativeInventory {
    public static void init() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register(entries -> {
			entries.addAfter(
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
			addSleepingBags(entries);
		});
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(BloomCreativeInventory::addSleepingBags);
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
			entries.addAfter(
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
		});
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
			entries.addAfter(Items.ANDESITE, BloomBlocks.DOLERITE);
			entries.addAfter(Items.BEETROOT_SEEDS, BloomItems.COTTON_SEEDS);
			entries.addAfter(
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
			entries.addAfter(
				Items.CACTUS_FLOWER,
				BloomBlocks.SUCCULENT
			);
			entries.addAfter(
				Items.PEONY,
				BloomBlocks.BELLFLOWER,
				BloomBlocks.HYDRANGEA
			);
			entries.addAfter(
				Items.LARGE_FERN,
				BloomBlocks.REEDS
			);
            addOres(BloomBlocks.TUFF_ORES, entries);
			addOres(BloomBlocks.DOLERITE_ORES, entries);
            addOres(BloomBlocks.GRANITE_ORES, entries);
            addOres(BloomBlocks.DIORITE_ORES, entries);
            addOres(BloomBlocks.ANDESITE_ORES, entries);
            addOres(BloomBlocks.RED_SANDSTONE_ORES, entries);
            addOres(BloomBlocks.SANDSTONE_ORES, entries);
        });
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
			entries.addBefore(Items.STRING, BloomItems.COTTON, BloomItems.YARN);
		});
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

    public static void addOres(StoneOresRegistry ores, FabricItemGroupEntries entries) {
        ores.getOresMap().forEach((type, block) -> {
            entries.addAfter(type.baseBlock.asItem(), block.asItem());
        });
    }

	public static void addSleepingBags(FabricItemGroupEntries entries) {
		entries.addAfter(
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
