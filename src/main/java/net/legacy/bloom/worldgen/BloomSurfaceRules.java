package net.legacy.bloom.worldgen;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.frozenblock.lib.worldgen.biome.api.parameters.Humidity;
import net.frozenblock.lib.worldgen.biome.api.parameters.Temperature;
import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.frozenblock.lib.worldgen.surface.api.SurfaceRuleEvents;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.util.BiomeHelper;
import net.legacy.bloom.util.NoiseRule;
import net.legacy.bloom.util.NoiseRules;
import net.legacy.bloom.util.SurfaceRuleHelper;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

public final class BloomSurfaceRules implements SurfaceRuleEvents.OverworldSurfaceRuleCallback, SurfaceRuleEvents.OverworldSurfaceRuleNoPrelimSurfaceCallback {

    public static SurfaceRules.RuleSource aridRiversAndShores() {
        return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_SURFACE_COARSE_DIRT),
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					FrozenSurfaceRules.makeStateRule(Blocks.COARSE_DIRT)
				),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					FrozenSurfaceRules.makeStateRule(Blocks.COARSE_DIRT)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource tropicalRivers() {
        return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.isBiome(BloomBiomes.TROPICAL_RIVER),
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					FrozenSurfaceRules.makeStateRule(Blocks.MUD)
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRules.isBiome(BloomBiomes.TROPICAL_RIVER),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					FrozenSurfaceRules.makeStateRule(Blocks.MUD)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource underwaterMud() {
        return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_UNDERWATER_MUD),
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					SurfaceRules.ifTrue(
						SurfaceRules.not(SurfaceRules.waterBlockCheck(0, 0)),
						FrozenSurfaceRules.makeStateRule(Blocks.MUD)
					)
				)
			),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_UNDERWATER_MUD),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					SurfaceRules.ifTrue(
						SurfaceRules.not(SurfaceRules.waterBlockCheck(0, 0)),
						FrozenSurfaceRules.makeStateRule(Blocks.MUD)
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
							FrozenSurfaceRules.SANDSTONE
						),
						FrozenSurfaceRules.SAND
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
								FrozenSurfaceRules.SANDSTONE
							),
							FrozenSurfaceRules.SAND
						)
					),
					SurfaceRules.ifTrue(
						SurfaceRules.VERY_DEEP_UNDER_FLOOR,
						FrozenSurfaceRules.SANDSTONE
					)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource beaches() {
        return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_SURFACE_SAND),
			sandyBiomeRules()
        );
    }

    public static SurfaceRules.RuleSource gravellyRiversAndBeaches() {
        return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_SURFACE_GRAVEL),
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					SurfaceRules.ON_FLOOR,
					FrozenSurfaceRules.makeStateRule(Blocks.GRAVEL)
				),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					FrozenSurfaceRules.makeStateRule(Blocks.GRAVEL)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource coarseDirtStrips() {
        final SurfaceRules.RuleSource coarseDirtRule = SurfaceRules.ifTrue(
			SurfaceRuleData.surfaceNoiseAbove(5.5),
			FrozenSurfaceRules.makeStateRule(Blocks.COARSE_DIRT)
        );
        return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_STRIP_COARSE_DIRT),
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

    public static SurfaceRules.RuleSource windsweptSavanna() {
        return SurfaceRules.ifTrue(
			SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_DEPTH_GRANITE),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(
						SurfaceRuleData.surfaceNoiseAbove(1.75),
						FrozenSurfaceRules.makeStateRule(Blocks.GRANITE)
					)
				)
			)
        );
    }

    public static SurfaceRules.RuleSource swampMud() {
        return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_SWAMP_MUD),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(64), 0)),
					SurfaceRules.ifTrue(
						SurfaceRules.yStartCheck(VerticalAnchor.absolute(60), 0),
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.ON_FLOOR,
								FrozenSurfaceRules.makeStateRule(Blocks.MUD)
							),
							SurfaceRules.ifTrue(
								SurfaceRules.UNDER_FLOOR,
								FrozenSurfaceRules.makeStateRule(Blocks.MUD)
							)
						)
					)
				)
			)
        );
    }

	public static SurfaceRules.RuleSource higherStoneRule() {
		return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_HIGHER_STONE),
			SurfaceRuleHelper.higherStoneRule(Blocks.STONE)
		);
	}

    @Override
    public void addOverworldSurfaceRules(List<SurfaceRules.RuleSource> context) {
        context.add(
			SurfaceRules.sequence(
				aridRiversAndShores(),
				tropicalRivers(),
				underwaterMud(),
				swampMud(),
				beaches(),
				gravellyRiversAndBeaches(),
				coarseDirtStrips(),
				windsweptSavanna()
			)
        );
    }

    @Override
    public void addOverworldNoPrelimSurfaceRules(List<SurfaceRules.RuleSource> context) {
		context.add(
			SurfaceRules.sequence(
				higherStoneRule(),
				SurfaceRuleHelper.depthRule(
					Blocks.RED_SANDSTONE,
					FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_DEPTH_RED_SANDSTONE),
					16
				),
				SurfaceRuleHelper.depthRule(
					Blocks.SANDSTONE,
					FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_DEPTH_SANDSTONE),
					16
				),
				SurfaceRuleHelper.depthRule(
					BloomBlocks.DOLERITE,
					SurfaceRuleHelper.isFreezing()
				),
				SurfaceRuleHelper.depthRule(
					Blocks.GRANITE,
					SurfaceRuleHelper.temperature(1.8F, SurfaceRuleHelper.MAX)
				),
				SurfaceRuleHelper.depthRule(
					Blocks.DIORITE,
					List.of(
						SurfaceRuleHelper.temperature(0.8F, 0.92F),
						SurfaceRuleHelper.downfall(0.8F, 1F)
					),
					NoiseRules.of(
						NoiseRule.create(NoiseRules.Type.TEMPERATURE, BiomeHelper.TEMPERATURE_2, BiomeHelper.TEMPERATURE_5),
						NoiseRule.create(NoiseRules.Type.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5)
					)
				),
				SurfaceRuleHelper.depthRule(
					Blocks.ANDESITE,
					SurfaceRuleHelper.temperature(0.15F, SurfaceRuleHelper.MAX),
					NoiseRules.of(
						NoiseRule.create(NoiseRules.Type.TEMPERATURE, BiomeHelper.TEMPERATURE_1, BiomeHelper.TEMPERATURE_2),
						NoiseRule.create(NoiseRules.Type.HUMIDITY, BiomeHelper.HUMIDITY_3, BiomeHelper.HUMIDITY_5)
					)
				)
			)
        );
    }
}
