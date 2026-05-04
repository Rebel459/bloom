package net.rebel459.bloom.util;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rebel459.bloom.worldgen.BloomFeatures;
import net.rebel459.unified.platform.HelpersImpl;

public class BiomeHelper {

	public static class Colors {
		public static int DEFAULT_WATER = 4159204;

		public static int WARM_WATER = 3586498;

		public static int LUKEWARM_WATER = 2197679;
		public static int LUKEWARM_FOLIAGE = 9743914;
		public static int LUKEWARM_GRASS = 12561493;

		public static int TROPICAL_WATER = 2001635;

		public static int COLD_WATER = 2650242;

		public static int FROZEN_WATER = 15960;
	}

	public static BackgroundMusic music(Holder<SoundEvent> music) {
		return music(music, false);
	}
	public static BackgroundMusic music(Holder<SoundEvent> music, boolean underWater) {
		if (underWater) {
			return new BackgroundMusic(
				Optional.of(Musics.createGameMusic(music)),
				Optional.of(Musics.CREATIVE),
				Optional.of(Musics.UNDER_WATER)
			);
		} else {
			return new BackgroundMusic(
				Optional.of(Musics.createGameMusic(music)),
				Optional.of(Musics.CREATIVE),
				Optional.empty()
			);
		}
	}

	public static float waterFogMultiplier(float multiplier) {
		return 96F * multiplier;
	}

	public static void addLessStrictDisks(HelpersImpl.BiomeModifications.Context context) {
		context.getFeatures().addFeature(BloomFeatures.DISK_SAND, GenerationStep.Decoration.UNDERGROUND_ORES);
		context.getFeatures().addFeature(BloomFeatures.DISK_CLAY, GenerationStep.Decoration.UNDERGROUND_ORES);
		context.getFeatures().addFeature(BloomFeatures.DISK_GRAVEL, GenerationStep.Decoration.UNDERGROUND_ORES);
	}

	public static void addUnderground(HelpersImpl.BiomeModifications.Context context, ResourceKey<PlacedFeature> feature) {
		context.getFeatures().addFeature(feature, GenerationStep.Decoration.UNDERGROUND_DECORATION);
	}

	public static void addVegetation(HelpersImpl.BiomeModifications.Context context, ResourceKey<PlacedFeature> feature) {
		context.getFeatures().addFeature(feature, GenerationStep.Decoration.VEGETAL_DECORATION);
	}

	public static void surfaceBiome(
		ResourceKey<Biome> biome,
		Climate.Parameter temperature,
		Climate.Parameter humidity,
		Climate.Parameter continentalness,
		Climate.Parameter erosion,
		Climate.Parameter weirdness,
		long offset
	) {
		BiomePlacement.addOverworld(
			biome,
			new Climate.ParameterPoint(
				temperature,
				humidity,
				continentalness,
				erosion,
				Climate.Parameter.point(0F),
				weirdness,
				offset
			)
		);
		BiomePlacement.addOverworld(
			biome,
			new Climate.ParameterPoint(
				temperature,
				humidity,
				continentalness,
				erosion,
				Climate.Parameter.point(1F),
				weirdness,
				offset
			)
		);
	}

	public static void caveBiome(
		ResourceKey<Biome> biome,
		Climate.Parameter temperature,
		Climate.Parameter humidity,
		Climate.Parameter continentalness,
		Climate.Parameter erosion,
		Climate.Parameter depth,
		Climate.Parameter weirdness, long offset
	) {
		BiomePlacement.addOverworld(
			biome,
			new Climate.ParameterPoint(
				temperature,
				humidity,
				continentalness,
				erosion,
				depth,
				weirdness,
				offset
			)
		);
	}

	public static Biome getBiome(ResourceKey<Biome> identifier) {
		return VanillaRegistries.createLookup()
			.lookupOrThrow(Registries.BIOME)
			.get(identifier)
			.get()
			.value();
	}
}
