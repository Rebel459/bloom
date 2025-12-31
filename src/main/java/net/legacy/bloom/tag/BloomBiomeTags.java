package net.legacy.bloom.tag;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BloomBiomeTags {
    public static final TagKey<Biome> BLOOM_BIOMES = bind("bloom_biomes");
    public static final TagKey<Biome> IS_NON_BAMBOO_JUNGLE = bind("is_non_bamboo_jungle");
	public static final TagKey<Biome> IS_NON_SNOWY_TAIGA = bind("is_non_snowy_taiga");

	public static final TagKey<Biome> INTERNAL_STEEP = bind("internal/steep");
	public static final TagKey<Biome> INTERNAL_MOUNTAIN = bind("internal/mountain");

    public static final TagKey<Biome> HAS_WARM_COLORS = bind("has_effect/warm_colors");
    public static final TagKey<Biome> HAS_LUKEWARM_COLORS = bind("has_effect/lukewarm_colors");
    public static final TagKey<Biome> HAS_TROPICAL_COLORS = bind("has_effect/tropical_colors");
    public static final TagKey<Biome> HAS_COLD_COLORS = bind("has_effect/cold_colors");
    public static final TagKey<Biome> HAS_FROZEN_COLORS = bind("has_effect/frozen_colors");

    public static final TagKey<Biome> HAS_SURFACE_SAND = bind("has_rule/surface_sand");
    public static final TagKey<Biome> HAS_SURFACE_GRAVEL = bind("has_rule/surface_gravel");
    public static final TagKey<Biome> HAS_SURFACE_COARSE_DIRT = bind("has_rule/surface_coarse_dirt");
    public static final TagKey<Biome> HAS_UNDERWATER_MUD = bind("has_rule/underwater_mud");
    public static final TagKey<Biome> HAS_SWAMP_MUD = bind("has_rule/swamp_mud");
    public static final TagKey<Biome> HAS_STRIP_COARSE_DIRT = bind("has_rule/strip_coarse_dirt");
    public static final TagKey<Biome> HAS_HIGHER_DEPTH = bind("has_rule/higher_depth");
	public static final TagKey<Biome> HAS_HIGHER_STONE = bind("has_rule/higher_stone");
	public static final TagKey<Biome> HAS_MODIFIED_STONE_TYPE = bind("has_rule/internal/modified_stone_type"); // redundant
	public static final TagKey<Biome> HAS_DEPTH_GRANITE = bind("has_rule/depth_granite");
	public static final TagKey<Biome> HAS_DEPTH_DIORITE = bind("has_rule/depth_diorite");
	public static final TagKey<Biome> HAS_DEPTH_ANDESITE = bind("has_rule/depth_andesite");
	public static final TagKey<Biome> HAS_DEPTH_SANDSTONE = bind("has_rule/depth_sandstone");
	public static final TagKey<Biome> HAS_DEPTH_RED_SANDSTONE = bind("has_rule/depth_red_sandstone");
	public static final TagKey<Biome> HAS_DEPTH_DOLERITE = bind("has_rule/depth_dolerite");

	public static final TagKey<Biome> HAS_LESS_STRICT_DISKS = bind("has_feature/internal/less_strict_disks");
    public static final TagKey<Biome> HAS_WINDSWEPT_JUNGLE_FEATURES = bind("has_feature/internal/windswept_jungle_features");
    public static final TagKey<Biome> HAS_FEN_FEATURES = bind("has_feature/internal/fen_features");

    public static final TagKey<Biome> HAS_BROMELIAD = bind("has_feature/flower_bromeliad");
    public static final TagKey<Biome> HAS_PINK_ORCHID = bind("has_feature/flower_pink_orchid");
    public static final TagKey<Biome> HAS_HELLEBORE = bind("has_feature/flower_hellebore");
    public static final TagKey<Biome> HAS_LILY_OF_THE_VALLEY = bind("has_feature/flower_lily_of_the_valley");
    public static final TagKey<Biome> HAS_BELLFLOWER = bind("has_feature/flower_bellflower");
    public static final TagKey<Biome> HAS_HYDRANGEA = bind("has_feature/flower_hydrangea");
    public static final TagKey<Biome> HAS_CALLA_LILY = bind("has_feature/flower_calla_lily");
    public static final TagKey<Biome> HAS_DIANTHUS = bind("has_feature/flower_dianthus");
    public static final TagKey<Biome> HAS_GOLDENROD = bind("has_feature/flower_goldenrod");
    public static final TagKey<Biome> HAS_ORANGE_DAISY = bind("has_feature/flower_orange_daisy");
    public static final TagKey<Biome> HAS_SCILLA = bind("has_feature/flower_scilla");
    public static final TagKey<Biome> HAS_SUCCULENT = bind("has_feature/patch_succulent");
	public static final TagKey<Biome> HAS_REEDS = bind("has_feature/patch_reeds");

	public static final TagKey<Biome> HAS_STONE_BLOBS = bind("has_feature/ore_stone");

    private static TagKey<Biome> bind(String path) {
        return TagKey.create(Registries.BIOME, Bloom.id(path));
    }
}
