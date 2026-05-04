package net.rebel459.bloom.registry;

import com.mojang.serialization.MapCodec;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.util.ClimateRules;
import net.rebel459.bloom.util.BiomeRules;
import net.rebel459.bloom.util.NoiseRules;
import net.rebel459.bloom.util.SurfaceRuleHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.rebel459.unified.platform.UnifiedRegistries;
import java.util.function.BiConsumer;

public class BloomConditionSources {

	private static final UnifiedRegistries.DeferredRegistry MATERIAL_CONDITION = UnifiedRegistries.DeferredRegistry.create(Bloom.MOD_ID, BuiltInRegistries.MATERIAL_CONDITION);

	public static void init() {
		register((name, codec) -> register(MATERIAL_CONDITION, name, codec));
	}

	public static void register(BiConsumer<String, MapCodec<? extends SurfaceRules.ConditionSource>> consumer) {
		consumer.accept("configured", SurfaceRuleHelper.Configured.CODEC.codec());
		consumer.accept("biome_tag", BiomeRules.BiomeTag.CODEC.codec());
		consumer.accept("heightmap_biome", BiomeRules.HeightmapBiome.CODEC.codec());
		consumer.accept("heightmap_biome_tag", BiomeRules.HeightmapBiomeTag.CODEC.codec());
		consumer.accept("surface_biome", BiomeRules.SurfaceBiome.CODEC.codec());
		consumer.accept("surface_biome_tag", BiomeRules.SurfaceBiomeTag.CODEC.codec());
		consumer.accept("climate_temperature", ClimateRules.Temperature.CODEC.codec());
		consumer.accept("climate_temperature_offset", ClimateRules.TemperatureOffset.CODEC.codec());
		consumer.accept("climate_downfall", ClimateRules.Downfall.CODEC.codec());
		consumer.accept("noise_temperature", NoiseRules.Temperature.CODEC.codec());
		consumer.accept("noise_humidity", NoiseRules.Humidity.CODEC.codec());
		consumer.accept("noise_erosion", NoiseRules.Erosion.CODEC.codec());
		consumer.accept("noise_continentalness", NoiseRules.Continentalness.CODEC.codec());
		consumer.accept("noise_weirdness", NoiseRules.Weirdness.CODEC.codec());
		consumer.accept("noise_depth", NoiseRules.Depth.CODEC.codec());
		consumer.accept("noise_heightmap_depth", NoiseRules.HeightmapDepth.CODEC.codec());
	}

	public static <T> void register(UnifiedRegistries.DeferredRegistry registry, String name, T object) {
		registry.register(name, () -> object);
	}
}
