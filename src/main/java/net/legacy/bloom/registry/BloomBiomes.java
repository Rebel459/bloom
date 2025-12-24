package net.legacy.bloom.registry;

import net.legacy.bloom.worldgen.biome.TropicalRiver;
import net.legacy.bloom.worldgen.biome.AridRiver;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class BloomBiomes {

    public static final ResourceKey<Biome> ARID_RIVER = AridRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> TROPICAL_RIVER = TropicalRiver.INSTANCE.getKey();

    public static void init() {}

    public static void bootstrap(BootstrapContext<Biome> context) {
        register(context, ARID_RIVER, AridRiver.INSTANCE.create(context));
        register(context, TROPICAL_RIVER, TropicalRiver.INSTANCE.create(context));
    }

    private static void register(BootstrapContext<Biome> entries, ResourceKey<Biome> key, Biome biome) {
        entries.register(key, biome);
    }
}