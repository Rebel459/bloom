package net.legacy.bloom.worldgen;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.*;

public final class BloomFeatures {
	public static final ResourceKey<PlacedFeature> DISK_CLAY = register("disk_clay");
	public static final ResourceKey<PlacedFeature> DISK_SAND = register("disk_sand");
	public static final ResourceKey<PlacedFeature> DISK_GRAVEL = register("disk_gravel");

	public static final ResourceKey<PlacedFeature> FLOWER_HELLEBORE = register("flower_hellebore");
	public static final ResourceKey<PlacedFeature> FLOWER_LILY_OF_THE_VALLEY = register("flower_lily_of_the_valley");
	public static final ResourceKey<PlacedFeature> FLOWER_BROMELIAD = register("flower_bromeliad");
	public static final ResourceKey<PlacedFeature> FLOWER_PINK_ORCHID = register("flower_pink_orchid");
	public static final ResourceKey<PlacedFeature> FLOWER_BELLFLOWER = register("flower_bellflower");
	public static final ResourceKey<PlacedFeature> FLOWER_HYDRANGEA = register("flower_hydrangea");
	public static final ResourceKey<PlacedFeature> FLOWER_CALLA_LILY = register("flower_calla_lily");
	public static final ResourceKey<PlacedFeature> FLOWER_DIANTHUS = register("flower_dianthus");
	public static final ResourceKey<PlacedFeature> FLOWER_GOLDENROD = register("flower_goldenrod");
	public static final ResourceKey<PlacedFeature> FLOWER_ORANGE_DAISY = register("flower_orange_daisy");
	public static final ResourceKey<PlacedFeature> FLOWER_SCILLA = register("flower_scilla");

	public static final ResourceKey<PlacedFeature> PATCH_SUCCULENT = register("patch_succulent");

	public static ResourceKey<PlacedFeature> register(String string) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Bloom.id(string));
	}
}