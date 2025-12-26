package net.legacy.bloom.tag;

import net.legacy.bloom.Bloom;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

public class BloomBiomeTags {
    public static final TagKey<Biome> IS_SANDY = bind("is_sandy");
    public static final TagKey<Biome> IS_GRAVELLY = bind("is_gravelly");
    public static final TagKey<Biome> IS_COARSE = bind("is_coarse");

    public static final TagKey<Biome> MODIFIED_DESERTS = bind("modified/deserts");
    public static final TagKey<Biome> MODIFIED_JUNGLES = bind("modified/jungles");
    public static final TagKey<Biome> MODIFIED_COLD_BIOMES = bind("modified/cold_biomes");
    public static final TagKey<Biome> MODIFIED_FROZEN_BIOMES = bind("modified/frozen_biomes");
    public static final TagKey<Biome> MODIFIED_TAIGAS = bind("modified/taigas");

    @NotNull
    private static TagKey<Biome> bind(@NotNull String path) {
        return TagKey.create(Registries.BIOME, Bloom.id(path));
    }
}