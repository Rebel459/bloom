package net.legacy.bloom.registry;

import net.legacy.bloom.worldgen.biome.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BloomBiomes {

    public static final ResourceKey<Biome> ARID_RIVER = AridRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> ARID_SHORE = AridShore.INSTANCE.getKey();
    public static final ResourceKey<Biome> TROPICAL_RIVER = TropicalRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> TROPICAL_BEACH = TropicalBeach.INSTANCE.getKey();
    public static final ResourceKey<Biome> COLD_RIVER = ColdRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> COLD_BEACH = ColdBeach.INSTANCE.getKey();

    public static void init() {}

    public static void bootstrap(BootstrapContext<Biome> context) {
        register(context, ARID_RIVER, AridRiver.INSTANCE.create(context));
        register(context, ARID_SHORE, AridShore.INSTANCE.create(context));
        register(context, TROPICAL_RIVER, TropicalRiver.INSTANCE.create(context));
        register(context, TROPICAL_BEACH, TropicalBeach.INSTANCE.create(context));
        register(context, COLD_RIVER, ColdRiver.INSTANCE.create(context));
        register(context, COLD_BEACH, ColdBeach.INSTANCE.create(context));
    }

    private static void register(BootstrapContext<Biome> entries, ResourceKey<Biome> key, Biome biome) {
        entries.register(key, biome);
    }
}