package net.legacy.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.legacy.Bloom;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public final class BloomBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Bloom.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> TEST_BLOCK = register(
            "test_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .noLootTable()
            ));

    public static void init() {
        BLOCKS.register();
    }

    public static RegistrySupplier<Block> register(String path, Supplier<Block> blockSupplier) {
        return BLOCKS.register(
                path,
                () -> {
                    Block block = blockSupplier.get();
                    block.properties()
                            .setId(ResourceKey.create(Registries.BLOCK, Bloom.id(path)));
                    return block;
                }
        );
    }
}