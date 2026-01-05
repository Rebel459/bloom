package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.legacy.bloom.util.StoneOresRegistry;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class BloomCreativeInventory {
    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            addOres(BloomBlocks.TUFF_ORES, entries);
			addOres(BloomBlocks.DOLERITE_ORES, entries);
            addOres(BloomBlocks.GRANITE_ORES, entries);
            addOres(BloomBlocks.DIORITE_ORES, entries);
            addOres(BloomBlocks.ANDESITE_ORES, entries);
            addOres(BloomBlocks.RED_SANDSTONE_ORES, entries);
            addOres(BloomBlocks.SANDSTONE_ORES, entries);
        });
		WoodsetRegistry.addToBuildingTab(Items.CHERRY_BUTTON, BloomBlocks.JACARANDA);
		WoodsetRegistry.addToNaturalTab(Items.CHERRY_SAPLING, BloomBlocks.JACARANDA, BloomBlocks.JACARANDA_SAPLING);
		WoodsetRegistry.addToFunctionalTab(Items.CHERRY_HANGING_SIGN, BloomBlocks.JACARANDA);
		WoodsetRegistry.addToUtilitiesTab(Items.CHERRY_CHEST_BOAT, BloomBlocks.JACARANDA);
    }

    public static void addOres(StoneOresRegistry ores, FabricItemGroupEntries entries) {
        ores.getOresMap().forEach((type, block) -> {
            entries.addAfter(type.baseBlock.asItem(), block.asItem());
        });
    }
}
