package net.legacy.bloom.worldgen;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class BloomFeatures {
	public static final ResourceKey<PlacedFeature> DISK_CLAY = register("disk_clay");
	public static final ResourceKey<PlacedFeature> DISK_SAND = register("disk_sand");
	public static final ResourceKey<PlacedFeature> DISK_GRAVEL = register("disk_gravel");

	public static final ResourceKey<PlacedFeature> ORE_STONE = register("ore_stone");
	public static final ResourceKey<PlacedFeature> ORE_GRAVEL = register("ore_gravel");

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
	public static final ResourceKey<PlacedFeature> FLOWER_HYACINTH = register("flower_hyacinth");
	public static final ResourceKey<PlacedFeature> FLOWER_QUEENCUP = register("flower_queencup");

    public static final ResourceKey<PlacedFeature> PATCH_SUCCULENT = register("patch_succulent");
    public static final ResourceKey<PlacedFeature> PATCH_REEDS = register("patch_reeds");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_JACARANDA_TREE = registerConfigured("tree_jacaranda");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_JACARANDA_TREE_BEES = registerConfigured("tree_jacaranda_bees");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_GOLDEN_BIRCH_TREE = registerConfigured("tree_golden_birch");

    public static final ResourceKey<PlacedFeature> TREES_TAIGA = register("trees_taiga");

    public static final ResourceKey<PlacedFeature> WINDSWEPT_JUNGLE_TREES = register("trees_windswept_jungle");
    public static final ResourceKey<PlacedFeature> WINDSWEPT_JUNGLE_FLOWERS = register("flowers_windswept_jungle");

    public static final ResourceKey<PlacedFeature> FEN_FLOWERS = register("flowers_fen");
    public static final ResourceKey<PlacedFeature> FEN_TREES = register("trees_fen");
    public static final ResourceKey<PlacedFeature> FEN_WILDFLOWERS = register("wildflowers_fen");

	public static final ResourceKey<PlacedFeature> GOLDEN_FOREST_TREES = register("trees_golden_forest");
	public static final ResourceKey<PlacedFeature> GOLDEN_FOREST_FLOWERS = register("flowers_golden_forest");

    public static ResourceKey<PlacedFeature> register(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Bloom.id(string));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfigured(String string) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Bloom.id(string));
    }
}
