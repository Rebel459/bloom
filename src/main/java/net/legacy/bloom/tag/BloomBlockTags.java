package net.legacy.bloom.tag;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class BloomBlockTags {
    public static final TagKey<Block> ARID_VEGETATION_MAY_PLACE_ON = bind("arid_vegetation_may_place_on");
    public static final TagKey<Block> SUBMERGED_VEGETATION_MAY_PLACE_ON = bind("submerged_vegetation_may_place_on");

    public static final TagKey<Block> JACARANDA_LOGS = bind("jacaranda_logs");

    public static final TagKey<Block> SAPPHIRE_ORES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("legacies_and_legends", "sapphire_ores"));

    private static TagKey<Block> bind(String path) {
        return TagKey.create(Registries.BLOCK, Bloom.id(path));
    }
}
