package net.legacy.bloom.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.util.StoneOresRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import static net.minecraft.world.item.Items.registerBlock;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public final class BloomItems {

	public static List<Item> TRANSLATABLE_ITEMS = new ArrayList<>();

    // Entity Items
/*    public static final BoatItem CHORUS_RAFT = init("chorus_raft",
            properties -> new BoatItem(EREntityTypes.CHORUS_RAFT, properties),
            new Item.Properties()
    );
    public static final BoatItem CHORUS_CHEST_RAFT = init("chorus_chest_raft",
            properties -> new BoatItem(EREntityTypes.CHORUS_CHEST_RAFT, properties),
            new Item.Properties()
    );*/

    public static void init() {
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, Item> function, Item.Properties properties) {
        return (T) Items.registerItem(ResourceKey.create(Registries.ITEM, Bloom.id(name)), function, properties);
    }
}
