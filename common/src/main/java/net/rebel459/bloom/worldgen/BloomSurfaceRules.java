package net.rebel459.bloom.worldgen;

import dev.worldgen.lithostitched.api.event.AddWorldgenModifiersEvent;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import java.util.List;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.bloom.registry.BloomBiomes;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.tag.BloomBiomeTags;
import net.rebel459.bloom.util.BiomeRules;
import net.rebel459.bloom.util.NoiseRules;
import net.rebel459.bloom.util.Parameters;
import net.rebel459.bloom.util.SurfaceRuleHelper;

public final class BloomSurfaceRules {

    public static SurfaceRules.RuleSource aridRiversAndShores() {
        return SurfaceRules.ifTrue(
			SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_SURFACE_COARSE_DIRT),
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					SurfaceRuleHelper.makeStateRule(Blocks.COARSE_DIRT)
				),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					SurfaceRuleHelper.makeStateRule(Blocks.COARSE_DIRT)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource tropicalRivers() {
        return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRuleHelper.isBiome(BloomBiomes.TROPICAL_RIVER),
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					SurfaceRuleHelper.makeStateRule(Blocks.MUD)
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRuleHelper.isBiome(BloomBiomes.TROPICAL_RIVER),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					SurfaceRuleHelper.makeStateRule(Blocks.MUD)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource underwaterMud() {
        return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_UNDERWATER_MUD),
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					SurfaceRules.ifTrue(
						SurfaceRules.not(SurfaceRules.waterBlockCheck(0, 0)),
						SurfaceRuleHelper.makeStateRule(Blocks.MUD)
					)
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_UNDERWATER_MUD),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					SurfaceRules.ifTrue(
						SurfaceRules.not(SurfaceRules.waterBlockCheck(0, 0)),
						SurfaceRuleHelper.makeStateRule(Blocks.MUD)
					)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource sandyBiomeRules() {
        return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue(
					SurfaceRules.waterBlockCheck(-1, 0),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.ON_CEILING,
							SurfaceRuleHelper.makeStateRule(Blocks.SANDSTONE)
						),
						SurfaceRuleHelper.makeStateRule(Blocks.SAND)
					)
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRules.waterStartCheck(-6, -1),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(
						SurfaceRules.UNDER_FLOOR,
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.ON_CEILING,
								SurfaceRuleHelper.makeStateRule(Blocks.SANDSTONE)
							),
							SurfaceRuleHelper.makeStateRule(Blocks.SAND)
						)
					),
					SurfaceRules.ifTrue(
						SurfaceRules.VERY_DEEP_UNDER_FLOOR,
						SurfaceRuleHelper.makeStateRule(Blocks.SANDSTONE)
					)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource beaches() {
        return SurfaceRules.ifTrue(
			SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_SURFACE_SAND),
			sandyBiomeRules()
        );
    }

    public static SurfaceRules.RuleSource gravellyRiversAndBeaches() {
        return SurfaceRules.ifTrue(
			SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_SURFACE_GRAVEL),
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					SurfaceRuleHelper.makeStateRule(Blocks.GRAVEL)
				),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					SurfaceRuleHelper.makeStateRule(Blocks.GRAVEL)
				)
			)
        );
    }

	public static SurfaceRules.RuleSource coarseDirtStrips() {
		final SurfaceRules.RuleSource coarseDirtRule = SurfaceRules.ifTrue(
			SurfaceRuleData.surfaceNoiseAbove(4.5),
			SurfaceRuleHelper.makeStateRule(Blocks.COARSE_DIRT)
		);
		return SurfaceRules.ifTrue(
			SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_STRIP_COARSE_DIRT),
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					coarseDirtRule
				),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					coarseDirtRule
				)
			)
		);
	}

	public static SurfaceRules.RuleSource swampMud() {
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_SWAMP_MUD),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(64), 0)),
					SurfaceRules.ifTrue(
						SurfaceRules.yStartCheck(VerticalAnchor.absolute(60), 0),
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.ON_FLOOR,
								SurfaceRuleHelper.makeStateRule(Blocks.MUD)
							),
							SurfaceRules.ifTrue(
								SurfaceRules.UNDER_FLOOR,
								SurfaceRuleHelper.makeStateRule(Blocks.MUD)
							)
						)
					)
				)
			)
		);
	}

	public static SurfaceRules.RuleSource taigaGravel() {
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_TAIGA_GRAVEL),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(64), 0)),
					SurfaceRules.ifTrue(
						SurfaceRules.yStartCheck(VerticalAnchor.absolute(60), 0),
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.ON_FLOOR,
								SurfaceRuleHelper.makeStateRule(Blocks.GRAVEL)
							),
							SurfaceRules.ifTrue(
								SurfaceRules.UNDER_FLOOR,
								SurfaceRuleHelper.makeStateRule(Blocks.GRAVEL)
							)
						)
					)
				)
			)
		);
	}

	public static SurfaceRules.RuleSource higherStoneRule() {
		return SurfaceRuleHelper.configuredRule(
			BloomConfig.get().worldgen.stony_cliffs,
			SurfaceRules.ifTrue(
				SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_HIGHER_STONE),
				SurfaceRuleHelper.higherStoneRule(Blocks.STONE)
			)
		);
	}

	public static SurfaceRules.RuleSource frozenPeaksRule() {
		return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5, 0.2), SurfaceRuleHelper.makeStateRule(Blocks.PACKED_ICE)),
				SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.0625, 0.025), SurfaceRuleHelper.makeStateRule(Blocks.ICE))
			)
		);
	}

    public static void init() {
		AddWorldgenModifiersEvent.EVENT.register((registry, consumer) -> {
			consumer.accept(
				Bloom.id("surface_rules"),
				WorldgenModifier.builder().addSurfaceRule(LevelStem.OVERWORLD, InjectionType.PREPEND,
					SurfaceRules.sequence(
						frozenPeaksRule(),
						SurfaceRuleHelper.depthRule(
							Blocks.RED_SANDSTONE,
							SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE, BiomeRules.Type.SURFACE),
							16,
							BloomConfig.get().worldgen.sandstone_depth
						),
						SurfaceRuleHelper.depthRule(
							Blocks.SANDSTONE,
							SurfaceRuleHelper.isBiomeTag(BloomBiomeTags.HAS_DEPTH_SANDSTONE, BiomeRules.Type.SURFACE),
							16,
							BloomConfig.get().worldgen.sandstone_depth
						),
						SurfaceRuleHelper.depthRule(
							BloomBlocks.DOLERITE.get(),
							SurfaceRuleHelper.isFreezing(),
							BloomConfig.get().worldgen.dolerite_depth
						),
						SurfaceRuleHelper.depthRule(
							Blocks.GRANITE,
							List.of(
								SurfaceRuleHelper.noise(NoiseRules.Type.TEMPERATURE, Parameters.TEMPERATURE_3, Parameters.TEMPERATURE_4),
								SurfaceRuleHelper.noise(NoiseRules.Type.HUMIDITY, Parameters.HUMIDITY_0, Parameters.HUMIDITY_2)
							),
							BloomConfig.get().worldgen.stone_variant_depth
						),
						SurfaceRuleHelper.depthRule(
							Blocks.DIORITE,
							List.of(
								SurfaceRuleHelper.temperature(0.8F, 0.92F),
								SurfaceRuleHelper.downfall(0.8F, 1F),
								SurfaceRuleHelper.noise(NoiseRules.Type.TEMPERATURE, Parameters.TEMPERATURE_2, Parameters.TEMPERATURE_5),
								SurfaceRuleHelper.noise(NoiseRules.Type.HUMIDITY, Parameters.HUMIDITY_3, Parameters.HUMIDITY_5)
							),
							BloomConfig.get().worldgen.stone_variant_depth
						),
						SurfaceRuleHelper.depthRule(
							Blocks.ANDESITE,
							List.of(
								SurfaceRuleHelper.temperatureBelow(0.6F),
								SurfaceRuleHelper.noise(NoiseRules.Type.TEMPERATURE, Parameters.TEMPERATURE_0, Parameters.TEMPERATURE_2),
								SurfaceRuleHelper.noise(NoiseRules.Type.HUMIDITY, Parameters.HUMIDITY_2, Parameters.HUMIDITY_5)
							),
							BloomConfig.get().worldgen.stone_variant_depth
						),
						higherStoneRule()
					)
				));
			consumer.accept(
				Bloom.id("preliminary_surface_rules"),
				WorldgenModifier.builder().addSurfaceRule(LevelStem.OVERWORLD, InjectionType.PREPEND,
					SurfaceRules.ifTrue(
						SurfaceRules.abovePreliminarySurface(),
						SurfaceRules.sequence(
							aridRiversAndShores(),
							tropicalRivers(),
							underwaterMud(),
							swampMud(),
							taigaGravel(),
							beaches(),
							gravellyRiversAndBeaches(),
							coarseDirtStrips()
						)
					)
				));
		});
    }
}
