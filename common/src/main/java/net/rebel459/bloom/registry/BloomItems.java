package net.rebel459.bloom.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.rebel459.bloom.Bloom;
import net.rebel459.unified.api.core.SuppliedBlock;
import net.rebel459.unified.api.core.SuppliedItem;
import net.rebel459.unified.api.core.UnifiedPlatform;
import net.rebel459.unified.api.core.UnifiedRegistries;
import net.rebel459.unified.api.platform.ModLoader;

public final class BloomItems {

	private static final UnifiedRegistries.Items ITEMS = UnifiedRegistries.Items.create(Bloom.MOD_ID);

	public static List<Item> TRANSLATABLE_ITEMS = new ArrayList<>();

	public static final SuppliedItem COTTON = register("cotton",
		Item::new,
		() -> new Item.Properties()
			.stacksTo(64)
	);
	public static final SuppliedItem YARN = register("yarn",
		Item::new,
		() -> new Item.Properties()
			.stacksTo(64)
	);

    // Block Items
	public static final SuppliedItem COTTON_SEEDS = register("cotton_seeds",
		(properties) -> new BlockItem(BloomBlocks.COTTON.get(), properties.useItemDescriptionPrefix()),
		() -> new Item.Properties()
			.stacksTo(64)
	);

	public static final SuppliedItem WHITE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.WHITE_SLEEPING_BAG);
	public static final SuppliedItem ORANGE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.ORANGE_SLEEPING_BAG);
	public static final SuppliedItem MAGENTA_SLEEPING_BAG = sleepingBagItem(BloomBlocks.MAGENTA_SLEEPING_BAG);
	public static final SuppliedItem LIGHT_BLUE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG);
	public static final SuppliedItem YELLOW_SLEEPING_BAG = sleepingBagItem(BloomBlocks.YELLOW_SLEEPING_BAG);
	public static final SuppliedItem LIME_SLEEPING_BAG = sleepingBagItem(BloomBlocks.LIME_SLEEPING_BAG);
	public static final SuppliedItem PINK_SLEEPING_BAG = sleepingBagItem(BloomBlocks.PINK_SLEEPING_BAG);
	public static final SuppliedItem GRAY_SLEEPING_BAG = sleepingBagItem(BloomBlocks.GRAY_SLEEPING_BAG);
	public static final SuppliedItem LIGHT_GRAY_SLEEPING_BAG = sleepingBagItem(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG);
	public static final SuppliedItem CYAN_SLEEPING_BAG = sleepingBagItem(BloomBlocks.CYAN_SLEEPING_BAG);
	public static final SuppliedItem PURPLE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.PURPLE_SLEEPING_BAG);
	public static final SuppliedItem BLUE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.BLUE_SLEEPING_BAG);
	public static final SuppliedItem BROWN_SLEEPING_BAG = sleepingBagItem(BloomBlocks.BROWN_SLEEPING_BAG);
	public static final SuppliedItem GREEN_SLEEPING_BAG = sleepingBagItem(BloomBlocks.GREEN_SLEEPING_BAG);
	public static final SuppliedItem RED_SLEEPING_BAG = sleepingBagItem(BloomBlocks.RED_SLEEPING_BAG);
	public static final SuppliedItem BLACK_SLEEPING_BAG = sleepingBagItem(BloomBlocks.BLACK_SLEEPING_BAG);

    public static void init() {}

	private static SuppliedItem sleepingBagItem(SuppliedBlock sleepingBag) {
		return ITEMS.registerBlockItem(
			sleepingBag,
			BlockItem::new,
			() -> new Item.Properties()
				.stacksTo(1)
		);
	}

	public static <T extends Item> SuppliedItem register(String name, Function<Item.Properties, Item> function, Supplier<Item.Properties> properties) {
		SuppliedItem item = ITEMS.register(name, function, properties);
		checkDatagen(item);
		return item;
	}

	public static void checkDatagen(SuppliedItem suppliedItem) {
		checkDatagen(suppliedItem, false);
	}
	public static void checkDatagen(SuppliedItem suppliedItem, boolean skipNameGen) {
		if (UnifiedPlatform.getModLoader() == ModLoader.FABRIC) {
			Item item = suppliedItem.get();
			if (!skipNameGen) TRANSLATABLE_ITEMS.add(item);
		}
	}
}
