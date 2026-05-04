package net.rebel459.bloom.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.rebel459.bloom.Bloom;
import net.rebel459.unified.platform.UnifiedPlatform;
import net.rebel459.unified.platform.UnifiedRegistries;
import net.rebel459.unified.util.LoaderType;
import net.rebel459.unified.util.SuppliedItem;

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
		Items.createBlockItemWithCustomItemName(BloomBlocks.COTTON.get()),
		() -> new Item.Properties()
			.stacksTo(64)
	);

	public static final SuppliedItem WHITE_SLEEPING_BAG = sleepingBagItem("white_sleeping_bag", BloomBlocks.WHITE_SLEEPING_BAG);
	public static final SuppliedItem ORANGE_SLEEPING_BAG = sleepingBagItem("orange_sleeping_bag", BloomBlocks.ORANGE_SLEEPING_BAG);
	public static final SuppliedItem MAGENTA_SLEEPING_BAG = sleepingBagItem("magenta_sleeping_bag", BloomBlocks.MAGENTA_SLEEPING_BAG);
	public static final SuppliedItem LIGHT_BLUE_SLEEPING_BAG = sleepingBagItem("light_blue_sleeping_bag", BloomBlocks.LIGHT_BLUE_SLEEPING_BAG);
	public static final SuppliedItem YELLOW_SLEEPING_BAG = sleepingBagItem("yellow_sleeping_bag", BloomBlocks.YELLOW_SLEEPING_BAG);
	public static final SuppliedItem LIME_SLEEPING_BAG = sleepingBagItem("lime_sleeping_bag", BloomBlocks.LIME_SLEEPING_BAG);
	public static final SuppliedItem PINK_SLEEPING_BAG = sleepingBagItem("pink_sleeping_bag", BloomBlocks.PINK_SLEEPING_BAG);
	public static final SuppliedItem GRAY_SLEEPING_BAG = sleepingBagItem("gray_sleeping_bag", BloomBlocks.GRAY_SLEEPING_BAG);
	public static final SuppliedItem LIGHT_GRAY_SLEEPING_BAG = sleepingBagItem("light_gray_sleeping_bag", BloomBlocks.LIGHT_GRAY_SLEEPING_BAG);
	public static final SuppliedItem CYAN_SLEEPING_BAG = sleepingBagItem("cyan_sleeping_bag", BloomBlocks.CYAN_SLEEPING_BAG);
	public static final SuppliedItem PURPLE_SLEEPING_BAG = sleepingBagItem("purple_sleeping_bag", BloomBlocks.PURPLE_SLEEPING_BAG);
	public static final SuppliedItem BLUE_SLEEPING_BAG = sleepingBagItem("blue_sleeping_bag", BloomBlocks.BLUE_SLEEPING_BAG);
	public static final SuppliedItem BROWN_SLEEPING_BAG = sleepingBagItem("brown_sleeping_bag", BloomBlocks.BROWN_SLEEPING_BAG);
	public static final SuppliedItem GREEN_SLEEPING_BAG = sleepingBagItem("green_sleeping_bag", BloomBlocks.GREEN_SLEEPING_BAG);
	public static final SuppliedItem RED_SLEEPING_BAG = sleepingBagItem("red_sleeping_bag", BloomBlocks.RED_SLEEPING_BAG);
	public static final SuppliedItem BLACK_SLEEPING_BAG = sleepingBagItem("black_sleeping_bag", BloomBlocks.BLACK_SLEEPING_BAG);

    public static void init() {}

	private static SuppliedItem sleepingBagItem(String path, Supplier<Block> sleepingBag) {
		return ITEMS.registerBlockItem(path, sleepingBag,
			() -> new Item.Properties()
				.stacksTo(1)
		);
	}

	public static <T extends Item> SuppliedItem register(String name, Function<Item.Properties, Item> function, Supplier<Item.Properties> properties) {
		SuppliedItem item = ITEMS.register(name, function, properties);
		checkDatagen(item);
		return item;
	}

	public static <T extends Item> SuppliedItem registerBlockItem(String name, Supplier<Block> block, Supplier<Item.Properties> properties) {
		SuppliedItem item = ITEMS.registerBlockItem(name, block, properties);
		checkDatagen(item);
		return item;
	}

	public static void checkDatagen(SuppliedItem suppliedItem) {
		checkDatagen(suppliedItem, false);
	}
	public static void checkDatagen(SuppliedItem suppliedItem, boolean skipNameGen) {
		if (UnifiedPlatform.getLoader() == LoaderType.FABRIC) {
			Item item = suppliedItem.get();
			if (!skipNameGen) TRANSLATABLE_ITEMS.add(item);
		}
	}
}
