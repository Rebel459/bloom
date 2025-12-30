package net.legacy.bloom.util;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import java.util.Optional;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.worldgen.BloomFeatures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.NotNull;

public class BiomeHelper {

    public static final float TEMPERATURE_0 = -1F;
    public static final float TEMPERATURE_1 = -0.45F;
    public static final float TEMPERATURE_2 = -0.15F;
    public static final float TEMPERATURE_3 = 0.2F;
    public static final float TEMPERATURE_4 = 0.55F;
    public static final float TEMPERATURE_5 = 1F;

    public static final float HUMIDITY_0 = -1F;
    public static final float HUMIDITY_1 = -0.35F;
    public static final float HUMIDITY_2 = -0.1F;
    public static final float HUMIDITY_3 = 0.1F;
    public static final float HUMIDITY_4 = 0.3F;
    public static final float HUMIDITY_5 = 1F;

    public static final float CONTINENTALNESS_MUSHROOM_FIELDS = -1.2F;
    public static final float CONTINENTALNESS_DEEP_OCEAN = -1.05F;
    public static final float CONTINENTALNESS_OCEAN = -0.455F;
    public static final float CONTINENTALNESS_COAST = -0.19F;
    public static final float CONTINENTALNESS_NEAR_INLAND = -0.11F;
    public static final float CONTINENTALNESS_MID_INLAND = 0.03F;
    public static final float CONTINENTALNESS_FAR_INLAND = 0.3F;
    public static final float CONTINENTALNESS_MAX = 1F;

    public static final float EROSION_0 = -1F;
    public static final float EROSION_1 = -0.78F;
    public static final float EROSION_2 = -0.375F;
    public static final float EROSION_3 = -0.2225F;
    public static final float EROSION_4 = 0.05F;
    public static final float EROSION_5 = 0.45F;
    public static final float EROSION_6 = 0.55F;
    public static final float EROSION_7 = 1F;

    public static final float WEIRDNESS_MID_SLICE_NORMAL_ASCENDING = -1.0F;
    public static final float WEIRDNESS_HIGH_SLICE_NORMAL_ASCENDING = -0.93333334F;
    public static final float WEIRDNESS_PEAK_NORMAL = -0.7666667F;
    public static final float WEIRDNESS_HIGH_SLICE_NORMAL_DESCENDING = -0.56666666F;
    public static final float WEIRDNESS_MID_SLICE_NORMAL_DESCENDING = -0.4F;
    public static final float WEIRDNESS_LOW_SLICE_NORMAL_DESCENDING = -0.26666668F;
    public static final float WEIRDNESS_VALLEY = -0.05F;
    public static final float WEIRDNESS_LOW_SLICE_VARIANT_ASCENDING = 0.05F;
    public static final float WEIRDNESS_MID_SLICE_VARIANT_ASCENDING = 0.26666668F;
    public static final float WEIRDNESS_HIGH_SLICE_VARIANT_ASCENDING = 0.4F;
    public static final float WEIRDNESS_PEAK_VARIANT = 0.56666666F;
    public static final float WEIRDNESS_HIGH_SLICE_VARIANT_DESCENDING = 0.7666667F;
    public static final float WEIRDNESS_MID_SLICE_VARIANT_DESCENDING = 0.93333334F;
    public static final float WEIRDNESS_MAX = 1F;

    public static BackgroundMusic music(Holder<SoundEvent> music, boolean underWater) {
        if (underWater) {
            return new BackgroundMusic(
				Optional.of(Musics.createGameMusic(music)),
				Optional.of(Musics.CREATIVE),
				Optional.of(Musics.UNDER_WATER)
            );
        }
        else {
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

    public static void addLessStrictDisks(BiomeModificationContext context) {
        context.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_SAND);
        context.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_CLAY);
        context.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BloomFeatures.DISK_GRAVEL);
    }

    public static void addUnderground(BiomeModificationContext context, ResourceKey<PlacedFeature> feature) {
        context.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, feature);
    }

    public static void addVegetation(BiomeModificationContext context, ResourceKey<PlacedFeature> feature) {
        context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
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

	public static SurfaceRules.RuleSource belowGrassAndDirtRule(SurfaceRules.RuleSource rule) {
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
					rule
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRules.waterBlockCheck(-1, 0),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
					rule
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
				rule
			)
		);
	}

    public static SurfaceRules.RuleSource depthRule(TagKey<Biome> biomes, Block block) {
        final SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
        return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(biomes),
			SurfaceRules.ifTrue(
				SurfaceRules.not(SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR)),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(
						FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_HIGHER_STONE),
						SurfaceRules.ifTrue(
							SurfaceRules.UNDER_FLOOR,
							rule
						)
					),
					SurfaceRules.ifTrue(
						SurfaceRules.not(SurfaceRules.UNDER_FLOOR),
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.VERY_DEEP_UNDER_FLOOR,
								rule
							),
							SurfaceRules.ifTrue(
								SurfaceRules.DEEP_UNDER_FLOOR,
								rule
							)
						)
					),
					SurfaceRules.ifTrue(
						FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_DEEPER_STONE),
						SurfaceRules.ifTrue(
							SurfaceRules.not(SurfaceRules.UNDER_FLOOR),
							SurfaceRules.ifTrue(
								SurfaceRules.not(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8))),
								rule
							)
						)
					)
				)
			)
        );
    }
}
