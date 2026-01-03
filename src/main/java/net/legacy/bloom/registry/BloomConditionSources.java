package net.legacy.bloom.registry;

import com.mojang.serialization.MapCodec;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.util.ClimateRules;
import net.legacy.bloom.util.NoiseRules;
import net.legacy.bloom.util.SurfaceRuleHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.SurfaceRules;
import java.util.function.BiConsumer;

public class BloomConditionSources {

	public static void init() {
		register((name, codec) -> register(BuiltInRegistries.MATERIAL_CONDITION, name, codec));
	}

	public static void register(BiConsumer<String, MapCodec<? extends SurfaceRules.ConditionSource>> consumer) {
		consumer.accept("configured", SurfaceRuleHelper.Configured.CODEC.codec());
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

	public static <T> void register(Registry<T> registry, String name, T object) {
		Registry.register(registry, Bloom.key(registry.key(), name), object);
	}
}
