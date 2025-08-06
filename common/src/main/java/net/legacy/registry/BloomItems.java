package net.legacy.registry;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.legacy.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public final class BloomItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Bloom.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> TEST_ITEM = register(
            "test_item",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
    ));

    public static void init() {
        ITEMS.register();
    }

    public static RegistrySupplier<Item> register(String path, Supplier<Item> itemSupplier) {
        return ITEMS.register(
                path,
                () -> {
                    Item item = itemSupplier.get();
                    new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, Bloom.id(path)));
                    return item;
                }
        );
    }
}