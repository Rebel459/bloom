package net.legacy.bloom.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.util.BiomeHelper;
import net.legacy.bloom.worldgen.biome.AridRiver;
import net.minecraft.world.level.biome.Biomes;

public class BloomBiomePlacement {

    public static void init() {
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.ARID_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_4, BiomeHelper.TEMPERATURE_MAX),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_MAX),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                BloomBiomes.ARID_BEACH,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_4, BiomeHelper.TEMPERATURE_MAX),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_MAX),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.TROPICAL_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_MAX),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_6)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                BloomBiomes.TROPICAL_BEACH,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_MAX),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_6)
                )
        );
    }
}
