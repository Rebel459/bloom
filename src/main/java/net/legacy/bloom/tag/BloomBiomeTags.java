package net.legacy.bloom.tag;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

public class BloomBiomeTags {
    public static final TagKey<Biome> MODIFIED_DESERTS = bind("modification/deserts");
    public static final TagKey<Biome> MODIFIED_JUNGLES = bind("modification/jungles");
    public static final TagKey<Biome> GRAVELLY_BIOMES = bind("modification/gravelly_biomes");

    @NotNull
    private static TagKey<Biome> bind(@NotNull String path) {
        return TagKey.create(Registries.BIOME, Bloom.id(path));
    }
}