package net.rebel459.bloom.tag;

import net.minecraft.resources.Identifier;
import net.rebel459.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BloomBiomeTags {
    public static final TagKey<Biome> BLOOM_BIOMES = create("bloom_biomes");
	public static final TagKey<Biome> IS_NON_SNOWY_TAIGA = create("is_non_snowy_taiga");

	public static final TagKey<Biome> INTERNAL_DEPTH_ADAPTED = create("internal/depth_adapted");
	public static final TagKey<Biome> INTERNAL_STEEP = create("internal/steep");
	public static final TagKey<Biome> INTERNAL_MOUNTAIN = create("internal/mountain");
	public static final TagKey<Biome> INTERNAL_STONY = create("internal/stony");
	public static final TagKey<Biome> INTERNAL_STONY_SHORE = create("internal/stony_shore");
	public static final TagKey<Biome> INTERNAL_BADLANDS = create("internal/badlands");
	public static final TagKey<Biome> INTERNAL_WINDSWEPT_HILL = create("internal/windswept_hill");
	public static final TagKey<Biome> INTERNAL_WINDSWEPT_SAVANNA = create("internal/windswept_savanna");

	public static final TagKey<Biome> INTERNAL_LESS_STRICT_DISKS = create("internal/less_strict_disks");
	public static final TagKey<Biome> INTERNAL_WINDSWEPT_JUNGLE_FEATURES = create("internal/windswept_jungle_features");
	public static final TagKey<Biome> INTERNAL_SPARSE_WINDSWEPT_JUNGLE_FEATURES = create("internal/sparse_windswept_jungle_features");
	public static final TagKey<Biome> INTERNAL_FEN_FEATURES = create("internal/fen_features");
	public static final TagKey<Biome> INTERNAL_GOLDEN_FOREST_FEATURES = create("internal/golden_forest_features");
	public static final TagKey<Biome> INTERNAL_GOLDEN_FIELDS_FEATURES = create("internal/golden_fields_features");
	public static final TagKey<Biome> INTERNAL_LAVENDER_FIELDS_FEATURES = create("internal/lavender_fields_features");

    public static final TagKey<Biome> HAS_WARM_COLORS = create("effects/warm_colors");
    public static final TagKey<Biome> HAS_LUKEWARM_COLORS = create("effects/lukewarm_colors");
    public static final TagKey<Biome> HAS_TROPICAL_COLORS = create("effects/tropical_colors");
	public static final TagKey<Biome> HAS_COLD_COLORS = create("effects/cold_colors");
	public static final TagKey<Biome> HAS_FROZEN_COLORS = create("effects/frozen_colors");
	public static final TagKey<Biome> HAS_MUSHROOM_COLORS = create("effects/mushroom_colors");

    public static final TagKey<Biome> HAS_SURFACE_SAND = create("rules/surface_sand");
    public static final TagKey<Biome> HAS_SURFACE_GRAVEL = create("rules/surface_gravel");
    public static final TagKey<Biome> HAS_SURFACE_COARSE_DIRT = create("rules/surface_coarse_dirt");
    public static final TagKey<Biome> HAS_UNDERWATER_MUD = create("rules/underwater_mud");
    public static final TagKey<Biome> HAS_SWAMP_MUD = create("rules/swamp_mud");
	public static final TagKey<Biome> HAS_TAIGA_GRAVEL = create("rules/taiga_gravel");
	public static final TagKey<Biome> HAS_STRIP_COARSE_DIRT = create("rules/strip_coarse_dirt");
    public static final TagKey<Biome> HAS_HIGHER_DEPTH = create("rules/higher_depth");
	public static final TagKey<Biome> HAS_HIGHER_STONE = create("rules/higher_stone");
	public static final TagKey<Biome> HAS_DEPTH_SANDSTONE = create("rules/depth_sandstone");
	public static final TagKey<Biome> HAS_DEPTH_RED_SANDSTONE = create("rules/depth_red_sandstone");

    public static final TagKey<Biome> HAS_BROMELIAD = create("features/flower_bromeliad");
    public static final TagKey<Biome> HAS_PINK_ORCHID = create("features/flower_pink_orchid");
    public static final TagKey<Biome> HAS_HELLEBORE = create("features/flower_hellebore");
    public static final TagKey<Biome> HAS_BELLFLOWER = create("features/flower_bellflower");
    public static final TagKey<Biome> HAS_HYDRANGEA = create("features/flower_hydrangea");
    public static final TagKey<Biome> HAS_CALLA_LILY = create("features/flower_calla_lily");
    public static final TagKey<Biome> HAS_DIANTHUS = create("features/flower_dianthus");
    public static final TagKey<Biome> HAS_GOLDENROD = create("features/flower_goldenrod");
    public static final TagKey<Biome> HAS_ORANGE_DAISY = create("features/flower_orange_daisy");
	public static final TagKey<Biome> HAS_SCILLA = create("features/flower_scilla");
	public static final TagKey<Biome> HAS_HYACINTH = create("features/flower_hyacinth");
	public static final TagKey<Biome> HAS_QUEENCUP = create("features/flower_queencup");
    public static final TagKey<Biome> HAS_SUCCULENT = create("features/patch_succulent");
	public static final TagKey<Biome> HAS_REEDS = create("features/patch_reeds");
	public static final TagKey<Biome> HAS_WILD_COTTON = create("features/patch_wild_cotton");

	public static final TagKey<Biome> HAS_LILY_OF_THE_VALLEY = create("features/flower_lily_of_the_valley");
	public static final TagKey<Biome> HAS_ALLIUM = create("features/flower_allium");

	public static final TagKey<Biome> HAS_TAIGA_FOLIAGE = create("features/taiga_foliage");

	public static final TagKey<Biome> NO_DEFAULT_FLOWERS = create("features/no_default_flowers");
	public static final TagKey<Biome> NO_BADLANDS_GRASS = create("features/no_badlands_grass");
	public static final TagKey<Biome> NO_PINE_TREES = create("features/no_pine_trees");

	public static final TagKey<Biome> HAS_STONE_BLOBS = create("features/ore_stone");
	public static final TagKey<Biome> HAS_GRAVEL_BLOBS = create("features/ore_gravel");

	public static final TagKey<Biome> HAS_TAIGA_MUSIC = create("music/taiga");
	public static final TagKey<Biome> HAS_OLD_GROWTH_TAIGA_MUSIC = create("music/old_growth_taiga");

	public static final TagKey<Biome> IS_MOUNTAIN = createCommon("is_mountain");
	public static final TagKey<Biome> IS_CAVE = createCommon("is_cave");

	private static TagKey<Biome> create(String path) {
        return TagKey.create(Registries.BIOME, Bloom.id(path));
    }

	private static TagKey<Biome> createCommon(String path) {
		return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", path));
	}
}
