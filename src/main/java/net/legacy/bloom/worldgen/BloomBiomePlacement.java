package net.legacy.bloom.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.util.BiomeHelper;
import net.legacy.bloom.util.Parameters;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

public class BloomBiomePlacement {

    public static void init() {
        BiomePlacement.addSubOverworld(
			Biomes.RIVER,
			BloomBiomes.WARM_RIVER,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_4, Parameters.TEMPERATURE_5 + 0.2F),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_5)
			)
        );
        BiomePlacement.addSubOverworld(
			Biomes.STONY_SHORE,
			BloomBiomes.ARID_SHORE,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_4, Parameters.TEMPERATURE_5 + 0.2F),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_5)
			)
        );
        BiomePlacement.addSubOverworld(
			Biomes.RIVER,
			BloomBiomes.TROPICAL_RIVER,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
			)
        );
        BiomePlacement.addSubOverworld(
			Biomes.BEACH,
			BloomBiomes.TROPICAL_BEACH,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
			)
        );
        BiomePlacement.addSubOverworld(
			Biomes.RIVER,
			BloomBiomes.COLD_RIVER,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
			)
        );
        BiomePlacement.addSubOverworld(
			Biomes.BEACH,
			BloomBiomes.COLD_BEACH,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
				CriterionBuilder.not(CriterionBuilder.value(BiomeParameterTargets.EROSION, Parameters.EROSION_6, Parameters.EROSION_7))
			)
        );
        BiomePlacement.addSubOverworld(
			Biomes.RIVER,
			BloomBiomes.LUKEWARM_RIVER,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_2)
			)
        );
        BiomePlacement.addSubOverworld(
			Biomes.BEACH,
			BloomBiomes.LUKEWARM_BEACH,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_2)
			)
        );
        BiomeHelper.surfaceBiome(
			BloomBiomes.WINDSWEPT_JUNGLE,
			Climate.Parameter.span(Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
			Climate.Parameter.span(Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
			Climate.Parameter.span(Parameters.CONTINENTALNESS_COAST, Parameters.CONTINENTALNESS_MAX),
			Climate.Parameter.span(Parameters.EROSION_5, Parameters.EROSION_6),
			Climate.Parameter.span(Parameters.WEIRDNESS_MID_SLICE_VARIANT_ASCENDING, Parameters.WEIRDNESS_MAX),
			0L
        );
		BiomeHelper.surfaceBiome(
			BloomBiomes.WINDSWEPT_JUNGLE,
			Climate.Parameter.span(Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
			Climate.Parameter.span(Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
			Climate.Parameter.span(Parameters.CONTINENTALNESS_COAST, Parameters.CONTINENTALNESS_MAX),
			Climate.Parameter.span(Parameters.EROSION_5, Parameters.EROSION_6),
			Climate.Parameter.span(Parameters.WEIRDNESS_PEAK_NORMAL, Parameters.WEIRDNESS_MID_SLICE_NORMAL_DESCENDING),
			0L
        );
        BiomePlacement.addSubOverworld(
			Biomes.WINDSWEPT_SAVANNA,
			BloomBiomes.WINDSWEPT_JUNGLE,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_4)
			)
        );
		BiomeHelper.surfaceBiome(
			BloomBiomes.FEN,
			Climate.Parameter.span(Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2),
			Climate.Parameter.span(Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
			Climate.Parameter.span(Parameters.CONTINENTALNESS_COAST, Parameters.CONTINENTALNESS_MAX),
			Climate.Parameter.span(Parameters.EROSION_6, Parameters.EROSION_7),
			Climate.Parameter.span(Parameters.WEIRDNESS_MID_SLICE_NORMAL_DESCENDING, Parameters.WEIRDNESS_HIGH_SLICE_VARIANT_ASCENDING),
			0L
        );
		BiomePlacement.addSubOverworld(
			Biomes.SWAMP,
			BloomBiomes.FEN,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
			)
		);
		BiomePlacement.addSubOverworld(
			Biomes.BEACH,
			BloomBiomes.FEN,
			CriterionBuilder.allOf(
				CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2),
				CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
				CriterionBuilder.value(BiomeParameterTargets.EROSION, Parameters.EROSION_6, Parameters.EROSION_7)
			)
		);
    }
}
