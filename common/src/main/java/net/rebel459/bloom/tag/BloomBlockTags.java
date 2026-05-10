package net.rebel459.bloom.tag;

import net.rebel459.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BloomBlockTags {
    public static final TagKey<Block> ARID_VEGETATION_MAY_PLACE_ON = create("arid_vegetation_may_place_on");
	public static final TagKey<Block> SUBMERGED_VEGETATION_MAY_PLACE_ON = create("submerged_vegetation_may_place_on");

	public static final TagKey<Block> NO_SNOW_OVERLAY = create("no_snow_overlay");

	public static final TagKey<Block> JACARANDA_LOGS = create("jacaranda_logs");
	public static final TagKey<Block> GOLDEN_BIRCH_LOGS = create("golden_birch_logs");
	public static final TagKey<Block> PINE_LOGS = create("pine_logs");

	public static final TagKey<Block> SLEEPING_BAGS = create("sleeping_bags");
	public static final TagKey<Block> RUGS = create("rugs");

	public static final TagKey<Block> OVERLAY = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("underlay", "overlay"));
    public static final TagKey<Block> SAPPHIRE_ORES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("legacies_and_legends", "sapphire_ores"));
	public static final TagKey<Block> WILD_CROPS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("farmersdelight", "wild_crops"));

    private static TagKey<Block> create(String path) {
        return TagKey.create(Registries.BLOCK, Bloom.id(path));
    }
}
