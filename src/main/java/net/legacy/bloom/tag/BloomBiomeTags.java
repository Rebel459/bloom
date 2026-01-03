package net.legacy.bloom.tag;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BloomBiomeTags {
    public static final TagKey<Biome> BLOOM_BIOMES = bind("bloom_biomes");
    public static final TagKey<Biome> IS_NON_BAMBOO_JUNGLE = bind("is_non_bamboo_jungle");
	public static final TagKey<Biome> IS_NON_SNOWY_TAIGA = bind("is_non_snowy_taiga");

	public static final TagKey<Biome> INTERNAL_DEPTH_ADAPTED = bind("internal/depth_adapted");
	public static final TagKey<Biome> INTERNAL_STEEP = bind("internal/steep");
	public static final TagKey<Biome> INTERNAL_MOUNTAIN = bind("internal/mountain");
	public static final TagKey<Biome> INTERNAL_STONY = bind("internal/stony");
	public static final TagKey<Biome> INTERNAL_STONY_SHORE = bind("internal/stony_shore");
	public static final TagKey<Biome> INTERNAL_BADLANDS = bind("internal/badlands");
	public static final TagKey<Biome> INTERNAL_WINDSWEPT_HILL = bind("internal/windswept_hill");
	public static final TagKey<Biome> INTERNAL_WINDSWEPT_SAVANNA = bind("internal/windswept_savanna");

	public static final TagKey<Biome> INTERNAL_LESS_STRICT_DISKS = bind("internal/less_strict_disks");
	public static final TagKey<Biome> INTERNAL_WINDSWEPT_JUNGLE_FEATURES = bind("internal/windswept_jungle_features");
	public static final TagKey<Biome> INTERNAL_FEN_FEATURES = bind("internal/fen_features");

    public static final TagKey<Biome> HAS_WARM_COLORS = bind("effects/warm_colors");
    public static final TagKey<Biome> HAS_LUKEWARM_COLORS = bind("effects/lukewarm_colors");
    public static final TagKey<Biome> HAS_TROPICAL_COLORS = bind("effects/tropical_colors");
    public static final TagKey<Biome> HAS_COLD_COLORS = bind("effects/cold_colors");
    public static final TagKey<Biome> HAS_FROZEN_COLORS = bind("effects/frozen_colors");

    public static final TagKey<Biome> HAS_SURFACE_SAND = bind("rules/surface_sand");
    public static final TagKey<Biome> HAS_SURFACE_GRAVEL = bind("rules/surface_gravel");
    public static final TagKey<Biome> HAS_SURFACE_COARSE_DIRT = bind("rules/surface_coarse_dirt");
    public static final TagKey<Biome> HAS_UNDERWATER_MUD = bind("rules/underwater_mud");
    public static final TagKey<Biome> HAS_SWAMP_MUD = bind("rules/swamp_mud");
    public static final TagKey<Biome> HAS_STRIP_COARSE_DIRT = bind("rules/strip_coarse_dirt");
    public static final TagKey<Biome> HAS_HIGHER_DEPTH = bind("rules/higher_depth");
	public static final TagKey<Biome> HAS_HIGHER_STONE = bind("rules/higher_stone");
	public static final TagKey<Biome> HAS_MODIFIED_STONE_TYPE = bind("rules/internal/modified_stone_type"); // redundant
	public static final TagKey<Biome> HAS_DEPTH_GRANITE = bind("rules/depth_granite");
	public static final TagKey<Biome> HAS_DEPTH_DIORITE = bind("rules/depth_diorite");
	public static final TagKey<Biome> HAS_DEPTH_ANDESITE = bind("rules/depth_andesite");
	public static final TagKey<Biome> HAS_DEPTH_SANDSTONE = bind("rules/depth_sandstone");
	public static final TagKey<Biome> HAS_DEPTH_RED_SANDSTONE = bind("rules/depth_red_sandstone");
	public static final TagKey<Biome> HAS_DEPTH_DOLERITE = bind("rules/depth_dolerite");

    public static final TagKey<Biome> HAS_BROMELIAD = bind("features/flower_bromeliad");
    public static final TagKey<Biome> HAS_PINK_ORCHID = bind("features/flower_pink_orchid");
    public static final TagKey<Biome> HAS_HELLEBORE = bind("features/flower_hellebore");
    public static final TagKey<Biome> HAS_LILY_OF_THE_VALLEY = bind("features/flower_lily_of_the_valley");
    public static final TagKey<Biome> HAS_BELLFLOWER = bind("features/flower_bellflower");
    public static final TagKey<Biome> HAS_HYDRANGEA = bind("features/flower_hydrangea");
    public static final TagKey<Biome> HAS_CALLA_LILY = bind("features/flower_calla_lily");
    public static final TagKey<Biome> HAS_DIANTHUS = bind("features/flower_dianthus");
    public static final TagKey<Biome> HAS_GOLDENROD = bind("features/flower_goldenrod");
    public static final TagKey<Biome> HAS_ORANGE_DAISY = bind("features/flower_orange_daisy");
	public static final TagKey<Biome> HAS_SCILLA = bind("features/flower_scilla");
	public static final TagKey<Biome> HAS_HYACINTH = bind("features/flower_hyacinth");
	public static final TagKey<Biome> HAS_QUEENCUP = bind("features/flower_queencup");
    public static final TagKey<Biome> HAS_SUCCULENT = bind("features/patch_succulent");
	public static final TagKey<Biome> HAS_REEDS = bind("features/patch_reeds");

	public static final TagKey<Biome> NO_DEFAULT_FLOWERS = bind("features/no_default_flowers");

	public static final TagKey<Biome> HAS_STONE_BLOBS = bind("features/ore_stone");
	public static final TagKey<Biome> HAS_GRAVEL_BLOBS = bind("features/ore_gravel");

    private static TagKey<Biome> bind(String path) {
        return TagKey.create(Registries.BIOME, Bloom.id(path));
    }
}
