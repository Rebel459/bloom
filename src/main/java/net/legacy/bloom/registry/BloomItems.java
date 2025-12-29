package net.legacy.bloom.registry;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static net.minecraft.world.item.Items.registerBlock;

public final class BloomItems {

    // Entity Items
/*    public static final BoatItem CHORUS_RAFT = register("chorus_raft",
            properties -> new BoatItem(EREntityTypes.CHORUS_RAFT, properties),
            new Item.Properties()
    );
    public static final BoatItem CHORUS_CHEST_RAFT = register("chorus_chest_raft",
            properties -> new BoatItem(EREntityTypes.CHORUS_CHEST_RAFT, properties),
            new Item.Properties()
    );*/

    // Block Items
    public static final Item JACARANDA_SIGN = registerBlock(BloomBlocks.JACARANDA_SIGN,
            (block, properties) -> new SignItem(block, BloomBlocks.JACARANDA_WALL_SIGN, properties),
            new Item.Properties()
                    .stacksTo(16)
    );
    public static final Item JACARANDA_HANGING_SIGN = registerBlock(BloomBlocks.JACARANDA_HANGING_SIGN,
            (block, properties) -> new HangingSignItem(block, BloomBlocks.JACARANDA_WALL_HANGING_SIGN, properties),
            new Item.Properties()
                    .stacksTo(16)
    );

    public static void init() {
    }

    private static @NotNull <T extends Item> T register(String name, @NotNull Function<Item.Properties, Item> function, Item.@NotNull Properties properties) {
        return (T) Items.registerItem(ResourceKey.create(Registries.ITEM, Bloom.id(name)), function, properties);
    }
}