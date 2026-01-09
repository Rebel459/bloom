package net.legacy.bloom.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.util.StoneOresRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import static net.minecraft.world.item.Items.registerBlock;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public final class BloomItems {

	public static List<Item> TRANSLATABLE_ITEMS = new ArrayList<>();

    // Block Items
	public static final Item WHITE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.WHITE_SLEEPING_BAG);
	public static final Item ORANGE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.ORANGE_SLEEPING_BAG);
	public static final Item MAGENTA_SLEEPING_BAG = sleepingBagItem(BloomBlocks.MAGENTA_SLEEPING_BAG);
	public static final Item LIGHT_BLUE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG);
	public static final Item YELLOW_SLEEPING_BAG = sleepingBagItem(BloomBlocks.YELLOW_SLEEPING_BAG);
	public static final Item LIME_SLEEPING_BAG = sleepingBagItem(BloomBlocks.LIME_SLEEPING_BAG);
	public static final Item PINK_SLEEPING_BAG = sleepingBagItem(BloomBlocks.PINK_SLEEPING_BAG);
	public static final Item GRAY_SLEEPING_BAG = sleepingBagItem(BloomBlocks.GRAY_SLEEPING_BAG);
	public static final Item LIGHT_GRAY_SLEEPING_BAG = sleepingBagItem(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG);
	public static final Item CYAN_SLEEPING_BAG = sleepingBagItem(BloomBlocks.CYAN_SLEEPING_BAG);
	public static final Item PURPLE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.PURPLE_SLEEPING_BAG);
	public static final Item BLUE_SLEEPING_BAG = sleepingBagItem(BloomBlocks.BLUE_SLEEPING_BAG);
	public static final Item BROWN_SLEEPING_BAG = sleepingBagItem(BloomBlocks.BROWN_SLEEPING_BAG);
	public static final Item GREEN_SLEEPING_BAG = sleepingBagItem(BloomBlocks.GREEN_SLEEPING_BAG);
	public static final Item RED_SLEEPING_BAG = sleepingBagItem(BloomBlocks.RED_SLEEPING_BAG);
	public static final Item BLACK_SLEEPING_BAG = sleepingBagItem(BloomBlocks.BLACK_SLEEPING_BAG);

    public static void init() {}

	private static Item sleepingBagItem(Block sleepingBag) {
		return registerBlock(sleepingBag,
			(block, properties) -> new BlockItem(sleepingBag, properties),
			new Item.Properties()
				.stacksTo(1)
		);
	}

    private static <T extends Item> T register(String name, Function<Item.Properties, Item> function, Item.Properties properties) {
        return (T) Items.registerItem(ResourceKey.create(Registries.ITEM, Bloom.id(name)), function, properties);
    }
}
