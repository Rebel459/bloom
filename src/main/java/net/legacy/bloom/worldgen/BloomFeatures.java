package net.legacy.bloom.worldgen;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BloomFeatures {

    public static final ResourceKey<PlacedFeature> DISK_CLAY = register("disk_clay");
    public static final ResourceKey<PlacedFeature> DISK_GRAVEL = register("disk_gravel");
    public static final ResourceKey<PlacedFeature> DISK_SAND = register("disk_sand");

    public static ResourceKey<PlacedFeature> register(String path) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Bloom.id(path));
    }
}