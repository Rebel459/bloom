package net.legacy.bloom.registry;

import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.legacy.bloom.worldgen.biome.WarmRiver;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class BloomBiomes {
    public static final int STOCK_FOG_COLOR = 12638463;
    public static final int COLD_WATER_COLOR = 4020182;
    public static final int COLD_WATER_FOG_COLOR = 329011;
    public static final int STOCK_WATER_COLOR = 4159204;
    public static final int STOCK_WATER_FOG_COLOR = 329011;
    public static final int WARM_WATER_COLOR = 4566514;
    public static final int WARM_WATER_FOG_COLOR = 267827;

    public static void addBasicFeatures(BiomeGenerationSettings.Builder builder, boolean commonSprings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        if (commonSprings) {
            builder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER);
        } else {
            BiomeDefaultFeatures.addDefaultSprings(builder);
        }
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static final ResourceKey<Biome> WARM_RIVER = WarmRiver.INSTANCE.getKey();

    public static void init() {}

    public static void bootstrap(BootstrapContext<Biome> context) {
        register(context, WARM_RIVER, WarmRiver.INSTANCE.create(context));
    }

    private static void register(BootstrapContext<Biome> entries, ResourceKey<Biome> key, Biome biome) {
        entries.register(key, biome);
    }
}