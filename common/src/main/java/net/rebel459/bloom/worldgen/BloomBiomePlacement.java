package net.rebel459.bloom.worldgen;

import com.mojang.datafixers.util.Pair;
import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.bloom.registry.BloomBiomes;
import net.rebel459.bloom.util.BiomeHelper;
import net.rebel459.bloom.util.Parameters;
import java.util.function.BiConsumer;

public class BloomBiomePlacement {

    public static void init() {
		AddBiomeInjectorsEvent.EVENT.register((registry, consumer) -> {
			Pair<RegistryAccess, BiConsumer<Identifier, BiomeInjector>> event = Pair.of(registry, consumer);
			if (BloomConfig.get().worldgen.improved_taiga_placement) {
				consumer.accept(
					Bloom.id("remove_base_pine_placement"),
					BiomeInjector.builder(Level.OVERWORLD).priority(500).replacePartially(
						registry.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
						registry.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
						ParameterBuilder.create()
					)
				);
				consumer.accept(
					Bloom.id("snowier_taiga_placement"),
					BiomeInjector.builder(Level.OVERWORLD).priority(500).replacePartially(
						registry.getOrThrow(Biomes.TAIGA),
						registry.getOrThrow(Biomes.SNOWY_TAIGA),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_0, Parameters.TEMPERATURE_1)
					)
				);
			}
			if (BloomConfig.get().biomes.warm_river) {
				consumer.accept(
					Bloom.id("warm_river"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.RIVER),
						registry.getOrThrow(BloomBiomes.WARM_RIVER),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_4, Parameters.TEMPERATURE_5 + 0.2F)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_5)
					)
				);
			}
			if (BloomConfig.get().biomes.arid_shore) {
				consumer.accept(
					Bloom.id("arid_shore"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.STONY_SHORE),
						registry.getOrThrow(BloomBiomes.ARID_SHORE),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_4, Parameters.TEMPERATURE_5 + 0.2F)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_5)
					)
				);
			}
			if (BloomConfig.get().biomes.tropical_river) {
				consumer.accept(
					Bloom.id("tropical_river"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.RIVER),
						registry.getOrThrow(BloomBiomes.TROPICAL_RIVER),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
					)
				);
			}
			if (BloomConfig.get().biomes.tropical_beach) {
				consumer.accept(
					Bloom.id("tropical_beach"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.BEACH),
						registry.getOrThrow(BloomBiomes.TROPICAL_BEACH),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
					)
				);
			}
			if (BloomConfig.get().biomes.cold_river) {
				consumer.accept(
					Bloom.id("cold_river"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.RIVER),
						registry.getOrThrow(BloomBiomes.COLD_RIVER),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
					)
				);
			}
			if (BloomConfig.get().biomes.cold_beach) {
				consumer.accept(
					Bloom.id("cold_beach"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.BEACH),
						registry.getOrThrow(BloomBiomes.COLD_BEACH),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
							.climateMax(BiomeInjector.ClimateParameter.EROSION, Parameters.EROSION_6)
					)
				);
			}
			if (BloomConfig.get().biomes.lukewarm_river) {
				consumer.accept(
					Bloom.id("lukewarm_river"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.RIVER),
						registry.getOrThrow(BloomBiomes.LUKEWARM_RIVER),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_2)
					)
				);
			}
			if (BloomConfig.get().biomes.lukewarm_beach) {
				consumer.accept(
					Bloom.id("lukewarm_beach"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.BEACH),
						registry.getOrThrow(BloomBiomes.LUKEWARM_BEACH),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_2)
					)
				);
			}
			if (BloomConfig.get().biomes.windswept_jungle) {
				BiomeHelper.surfaceBiome(
					"windswept_jungle_positive_weirdness",
					BloomBiomes.WINDSWEPT_JUNGLE,
					Climate.Parameter.span(Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
					Climate.Parameter.span(Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
					Climate.Parameter.span(Parameters.CONTINENTALNESS_COAST, Parameters.CONTINENTALNESS_MAX),
					Climate.Parameter.span(Parameters.EROSION_5, Parameters.EROSION_6),
					Climate.Parameter.span(Parameters.WEIRDNESS_MID_SLICE_VARIANT_ASCENDING, Parameters.WEIRDNESS_MAX),
					0L,
					event
				);
				BiomeHelper.surfaceBiome(
					"windswept_jungle_negative_weirdness",
					BloomBiomes.WINDSWEPT_JUNGLE,
					Climate.Parameter.span(Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
					Climate.Parameter.span(Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
					Climate.Parameter.span(Parameters.CONTINENTALNESS_COAST, Parameters.CONTINENTALNESS_MAX),
					Climate.Parameter.span(Parameters.EROSION_5, Parameters.EROSION_6),
					Climate.Parameter.span(Parameters.WEIRDNESS_HIGH_SLICE_NORMAL_ASCENDING, Parameters.WEIRDNESS_HIGH_SLICE_NORMAL_DESCENDING),
					0L,
					event
				);
				consumer.accept(
					Bloom.id("windswept_jungle_replaces_windswept_savanna"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.WINDSWEPT_SAVANNA),
						registry.getOrThrow(BloomBiomes.WINDSWEPT_JUNGLE),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_4)
					)
				);
			}
			if (BloomConfig.get().biomes.sparse_windswept_jungle) {
				consumer.accept(
					Bloom.id("sparse_windswept_jungle"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.SPARSE_JUNGLE),
						registry.getOrThrow(BloomBiomes.SPARSE_WINDSWEPT_JUNGLE),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
							.climateRange(BiomeInjector.ClimateParameter.EROSION, Parameters.EROSION_5, Parameters.EROSION_6)
							.climateRange(BiomeInjector.ClimateParameter.WEIRDNESS, Parameters.WEIRDNESS_HIGH_SLICE_VARIANT_ASCENDING, Parameters.WEIRDNESS_MAX)
					)
				);
			}
			if (BloomConfig.get().biomes.fen) {
				BiomeHelper.surfaceBiome(
					"fen",
					BloomBiomes.FEN,
					Climate.Parameter.span(Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2),
					Climate.Parameter.span(Parameters.HUMIDITY_3, Parameters.HUMIDITY_5),
					Climate.Parameter.span(Parameters.CONTINENTALNESS_COAST, Parameters.CONTINENTALNESS_MAX),
					Climate.Parameter.span(Parameters.EROSION_6, Parameters.EROSION_7),
					Climate.Parameter.span(Parameters.WEIRDNESS_MID_SLICE_NORMAL_DESCENDING, Parameters.WEIRDNESS_HIGH_SLICE_VARIANT_ASCENDING),
					0L,
					event
				);
				consumer.accept(
					Bloom.id("fen_replaces_swamp"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.SWAMP),
						registry.getOrThrow(BloomBiomes.FEN),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_1, Parameters.TEMPERATURE_2)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
					)
				);
			}
			if (BloomConfig.get().biomes.snowy_shore) {
				consumer.accept(
					Bloom.id("snowy_shore"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.STONY_SHORE),
						registry.getOrThrow(BloomBiomes.SNOWY_SHORE),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_0, Parameters.TEMPERATURE_1)
					)
				);
			}
			if (BloomConfig.get().worldgen.improved_taiga_placement) {
				consumer.accept(
					Bloom.id("old_growth_pine_taiga"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
						registry.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
						ParameterBuilder.create()
							.region(BloomRegions.PINE)
					)
				);
			}
			if (BloomConfig.get().biomes.pine_taiga) {
				consumer.accept(
					Bloom.id("pine_taiga"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.TAIGA),
						registry.getOrThrow(BloomBiomes.PINE_TAIGA),
						ParameterBuilder.create()
							.region(BloomRegions.PINE)
					)
				);
			}
			if (BloomConfig.get().biomes.snowy_pine_taiga) {
				consumer.accept(
					Bloom.id("snowy_pine_taiga"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.SNOWY_TAIGA),
						registry.getOrThrow(BloomBiomes.SNOWY_PINE_TAIGA),
						ParameterBuilder.create()
							.region(BloomRegions.PINE)
					)
				);
			}
			if (BloomConfig.get().biomes.golden_forest) {
				BiomeHelper.surfaceBiome(
					"golden_forest",
					BloomBiomes.GOLDEN_FOREST,
					Climate.Parameter.span(Parameters.TEMPERATURE_1, -0.2F),
					Climate.Parameter.span(Parameters.HUMIDITY_2, Parameters.HUMIDITY_3),
					Climate.Parameter.span(Parameters.CONTINENTALNESS_COAST, Parameters.CONTINENTALNESS_MAX),
					Climate.Parameter.span(0F, Parameters.EROSION_5),
					Climate.Parameter.span(Parameters.WEIRDNESS_MID_SLICE_NORMAL_ASCENDING, Parameters.WEIRDNESS_MAX),
					0L,
					event
				);
			}
			if (BloomConfig.get().biomes.golden_fields) {
				consumer.accept(
					Bloom.id("golden_fields"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						HolderSet.direct(
							registry.getOrThrow(Biomes.PLAINS),
							registry.getOrThrow(Biomes.SUNFLOWER_PLAINS)
						),
						registry.getOrThrow(BloomBiomes.GOLDEN_FIELDS),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_1, -0.2F)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -0.2F, Parameters.HUMIDITY_3)
							.climateRange(BiomeInjector.ClimateParameter.EROSION, 0F, Parameters.EROSION_5)
					)
				);
			}
			if (BloomConfig.get().biomes.golden_river) {
				consumer.accept(
					Bloom.id("golden_river"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(Biomes.RIVER),
						registry.getOrThrow(BloomBiomes.GOLDEN_RIVER),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.TEMPERATURE, Parameters.TEMPERATURE_1, -0.2F)
							.climateRange(BiomeInjector.ClimateParameter.HUMIDITY, -0.2F, Parameters.HUMIDITY_3)
							.climateRange(BiomeInjector.ClimateParameter.EROSION, 0F, Parameters.EROSION_5)
					)
				);
				consumer.accept(
					Bloom.id("golden_river_in_golden_forest"),
					BiomeInjector.builder(Level.OVERWORLD).replacePartially(
						registry.getOrThrow(BloomBiomes.GOLDEN_FOREST),
						registry.getOrThrow(BloomBiomes.GOLDEN_RIVER),
						ParameterBuilder.create()
							.climateRange(BiomeInjector.ClimateParameter.WEIRDNESS, Parameters.WEIRDNESS_VALLEY, Parameters.WEIRDNESS_LOW_SLICE_VARIANT_ASCENDING)
					)
				);
			}
		});
    }
}
