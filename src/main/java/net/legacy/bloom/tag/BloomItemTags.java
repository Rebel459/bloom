package net.legacy.bloom.tag;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BloomItemTags {
	public static final TagKey<Item> JACARANDA_LOGS = bind("jacaranda_logs");

	public static final TagKey<Item> SLEEPING_BAG_MATERIALS = bind("sleeping_bag_materials");

	public static final TagKey<Item> SLEEPING_BAGS = bind("sleeping_bags");
	public static final TagKey<Item> RUGS = bind("sleeping_bags");

    public static final TagKey<Item> SAPPHIRE_ORES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("legacies_and_legends", "sapphire_ores"));

    private static TagKey<Item> bind(String path) {
        return TagKey.create(Registries.ITEM, Bloom.id(path));
    }
}
