package net.legacy.bloom.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import net.frozenblock.lib.worldgen.biome.api.parameters.Continentalness;
import net.frozenblock.lib.worldgen.biome.api.parameters.Humidity;
import net.frozenblock.lib.worldgen.biome.api.parameters.OverworldBiomeBuilderParameters;
import net.frozenblock.lib.worldgen.biome.api.parameters.Weirdness;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.util.BiomeHelper;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

public class BloomBiomePlacement {

    public static void init() {
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.WARM_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_4, BiomeHelper.TEMPERATURE_5 + 0.2F),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.STONY_SHORE,
                BloomBiomes.ARID_SHORE,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_4, BiomeHelper.TEMPERATURE_5 + 0.2F),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.TROPICAL_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                BloomBiomes.TROPICAL_BEACH,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.COLD_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_1, BiomeHelper.TEMPERATURE_2),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                BloomBiomes.COLD_BEACH,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_1, BiomeHelper.TEMPERATURE_2),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                BloomBiomes.LUKEWARM_RIVER,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_2)
                )
        );
        BiomePlacement.addSubOverworld(
                Biomes.BEACH,
                BloomBiomes.LUKEWARM_BEACH,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_0, BiomeHelper.HUMIDITY_2)
                )
        );
        BiomeHelper.surfaceBiome(
                BloomBiomes.WINDSWEPT_JUNGLE,
                Climate.Parameter.span(BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                Climate.Parameter.span(BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5),
                Climate.Parameter.span(BiomeHelper.CONTINENTALNESS_COAST, BiomeHelper.CONTINENTALNESS_MAX),
                Climate.Parameter.span(BiomeHelper.EROSION_5, BiomeHelper.EROSION_6),
                Climate.Parameter.span(BiomeHelper.WEIRDNESS_LOW_SLICE_VARIANT_ASCENDING, BiomeHelper.WEIRDNESS_MAX),
                0L
        );
        BiomeHelper.surfaceBiome(
                BloomBiomes.WINDSWEPT_JUNGLE,
                Climate.Parameter.span(BiomeHelper.TEMPERATURE_3, BiomeHelper.TEMPERATURE_4),
                Climate.Parameter.span(BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5),
                Climate.Parameter.span(BiomeHelper.CONTINENTALNESS_COAST, BiomeHelper.CONTINENTALNESS_MAX),
                Climate.Parameter.span(BiomeHelper.EROSION_5, BiomeHelper.EROSION_6),
                Climate.Parameter.span(BiomeHelper.WEIRDNESS_MID_SLICE_NORMAL_ASCENDING, BiomeHelper.WEIRDNESS_MID_SLICE_NORMAL_DESCENDING),
                0L
        );
        BiomePlacement.addSubOverworld(
                Biomes.WINDSWEPT_SAVANNA,
                BloomBiomes.WINDSWEPT_JUNGLE,
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, BiomeHelper.TEMPERATURE_2, BiomeHelper.TEMPERATURE_4),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, BiomeHelper.HUMIDITY_2, BiomeHelper.HUMIDITY_4),
                        CriterionBuilder.neighbor(BiomeTags.IS_JUNGLE)
                )
        );
    }
}
