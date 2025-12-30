package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.legacy.bloom.registry.data.StoneOresRegistry;
import net.minecraft.world.item.CreativeModeTabs;

public class BloomCreativeInventory {
    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            addOres(BloomBlocks.TUFF_ORES, entries);
            addOres(BloomBlocks.GRANITE_ORES, entries);
            addOres(BloomBlocks.DIORITE_ORES, entries);
            addOres(BloomBlocks.ANDESITE_ORES, entries);
            addOres(BloomBlocks.SANDSTONE_ORES, entries);
        });
    }


    public static void addOres(StoneOresRegistry ores, FabricItemGroupEntries entries) {
        ores.getOresMap().forEach((type, block) -> {
            entries.addAfter(type.baseBlock.asItem(), block.asItem());
        });
    }
}
