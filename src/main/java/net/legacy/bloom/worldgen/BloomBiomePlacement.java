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
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_4, BiomeHelper.TEMPERATURE_5 + 0.2F),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_5),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_1, BiomeHelper.EROSION_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.STONY_SHORE,
                BloomBiomes.ARID_SHORE,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_4, BiomeHelper.TEMPERATURE_5 + 0.2F),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_5),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_1, BiomeHelper.EROSION_3)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.TROPICAL_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_6)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                BloomBiomes.TROPICAL_BEACH,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_2, BiomeHelper.HUMIDITY_5),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_6)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.COLD_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_1, BiomeHelper.TEMPERATURE_2),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                BloomBiomes.COLD_BEACH,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_1, BiomeHelper.TEMPERATURE_2),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, BiomeHelper.EROSION_3, BiomeHelper.EROSION_5)
                )
        );
    }
}
