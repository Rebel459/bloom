package net.legacy.bloom.util;

import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import org.apache.commons.lang3.tuple.Triple;
import java.util.List;

public class SurfaceRuleHelper {

	private static final int defaultStartY = 0;
	private static final int defaultTransitionBlocks = 8;

	public static final float MIN = -10F;
	public static final float MAX = 10F;

	public static SurfaceRules.ConditionSource isFreezing() {
		return temperature(MIN, greaterThan(0.15F));
	}
	public static SurfaceRules.ConditionSource temperature(float point) {
		return ClimateRules.Temperature.point(point);
	}
	public static SurfaceRules.ConditionSource temperature(float min, float max) {
		return ClimateRules.Temperature.range(min, max);
	}
	public static SurfaceRules.ConditionSource temperatureAbove(float point) {
		return ClimateRules.Temperature.above(point);
	}
	public static SurfaceRules.ConditionSource temperatureBelow(float point) {
		return ClimateRules.Temperature.below(point);
	}

	public static SurfaceRules.ConditionSource noDownfall() {
		return downfall(0F);
	}
	public static SurfaceRules.ConditionSource downfall(float point) {
		return ClimateRules.Downfall.point(point);
	}
	public static SurfaceRules.ConditionSource downfall(float min, float max) {
		return ClimateRules.Downfall.range(min, max);
	}
	public static SurfaceRules.ConditionSource downfallAbove(float point) {
		return ClimateRules.Downfall.above(point);
	}
	public static SurfaceRules.ConditionSource downfallBelow(float point) {
		return ClimateRules.Downfall.below(point);
	}

	public static SurfaceRules.ConditionSource temperatureOffset(float min, float max) {
		return ClimateRules.TemperatureOffset.range(min, max);
	}
	public static SurfaceRules.ConditionSource temperatureOffsetAbove(float point) {
		return ClimateRules.TemperatureOffset.above(point);
	}
	public static SurfaceRules.ConditionSource temperatureOffsetBelow(float point) {
		return ClimateRules.TemperatureOffset.below(point);
	}

	public static SurfaceRules.ConditionSource heightmapDepth(float min, float max) {
		return NoiseRules.HeightmapDepth.range(min, max);
	}
	public static SurfaceRules.ConditionSource heightmapDepthAbove(float point) {
		return NoiseRules.HeightmapDepth.above(point);
	}
	public static SurfaceRules.ConditionSource heightmapDepthBelow(float point) {
		return NoiseRules.HeightmapDepth.below(point);
	}

	public static SurfaceRules.ConditionSource noise(NoiseRule.Type type, float point) {
		return switch (type) {
			case TEMPERATURE -> NoiseRules.Temperature.point(point);
			case HUMIDITY -> NoiseRules.Humidity.point(point);
			case EROSION -> NoiseRules.Erosion.point(point);
			case CONTINENTALNESS -> NoiseRules.Continentalness.point(point);
			case WEIRDNESS -> NoiseRules.Weirdness.point(point);
			case DEPTH -> NoiseRules.Depth.point(point);
			case HEIGHTMAP_DEPTH -> NoiseRules.HeightmapDepth.point(point);
		};
	}

	public static SurfaceRules.ConditionSource noise(NoiseRule.Type type, float min, float max) {
		return switch (type) {
			case TEMPERATURE -> NoiseRules.Temperature.range(min, max);
			case HUMIDITY -> NoiseRules.Humidity.range(min, max);
			case EROSION -> NoiseRules.Erosion.range(min, max);
			case CONTINENTALNESS -> NoiseRules.Continentalness.range(min, max);
			case WEIRDNESS -> NoiseRules.Weirdness.range(min, max);
			case DEPTH -> NoiseRules.Depth.range(min, max);
			case HEIGHTMAP_DEPTH -> NoiseRules.HeightmapDepth.range(min, max);
		};
	}
	
	public static SurfaceRules.ConditionSource noise(NoiseRule rule) {
		return noise(rule.getType(), rule.getMin(), rule.getMax());
	}

	public static SurfaceRules.RuleSource noises(SurfaceRules.RuleSource rule, NoiseRules conditions) {
		if (conditions.rules().isEmpty()) {
			return rule;
		}

		List<NoiseRule> list = conditions.rules();
		SurfaceRules.RuleSource rules = SurfaceRules.ifTrue(noise(list.getFirst().getType(), list.getFirst().getMin(), list.getFirst().getMax()), rule);

		for (int i = 1; i < list.size(); i++) {
			NoiseRule noiseRule = list.get(i);

			SurfaceRules.ConditionSource noiseCondition = noise(noiseRule.getType(), noiseRule.getMin(), noiseRule.getMax());

			rules = SurfaceRules.ifTrue(noiseCondition, rules);
		}

		return rules;
	}

	public static SurfaceRules.RuleSource conditions(SurfaceRules.RuleSource rule, List<SurfaceRules.ConditionSource> conditions) {
		if (conditions.isEmpty()) {
			return rule;
		}

		SurfaceRules.RuleSource rules = SurfaceRules.ifTrue(conditions.getFirst(), rule);

		for (int i = 1; i < conditions.size(); i++) {
			rules = SurfaceRules.ifTrue(conditions.get(i), rules);
		}

		return rules;
	}

	public static SurfaceRules.RuleSource configuredRule(SurfaceRules.RuleSource ruleSource, boolean config) {
		if (config) return ruleSource;
		else return SurfaceRules.ifTrue(temperature(Float.MIN_VALUE), ruleSource);
	}

	public static SurfaceRules.RuleSource includeTag(TagKey<Biome> biomes, SurfaceRules.RuleSource ruleSource) {
		return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(biomes),
			ruleSource
		);
	}
	public static SurfaceRules.RuleSource excludeTag(TagKey<Biome> biomes, SurfaceRules.RuleSource ruleSource) {
		return SurfaceRules.ifTrue(
			SurfaceRules.not(FrozenSurfaceRules.isBiomeTagOptimized(biomes)),
			ruleSource
		);
	}

	public static float greaterThan(float value) {
		return value + 0.01F;
	}
	public static float lessThan(float value) {
		return value - 0.01F;
	}

	public static String getKey(int startY, int transitionBlocks) {
		if (startY == 0 && transitionBlocks == 8) return "deepslate";
		else return "depth";
	}

	public static SurfaceRules.RuleSource higherStoneRule(Block block) {
		final SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
		return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_HIGHER_DEPTH),
			SurfaceRules.ifTrue(
				SurfaceRules.not(SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR)),
				SurfaceRules.ifTrue(
					SurfaceRules.UNDER_FLOOR,
					rule
				)
			)
		);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRule noiseRule) {
		return depthRule(block, List.of(), NoiseRules.of(noiseRule), defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRule noiseRule, boolean config) {
		return depthRule(block, List.of(), NoiseRules.of(noiseRule), defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRule noiseRule, int startY) {
		return depthRule(block, List.of(), NoiseRules.of(noiseRule), startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRule noiseRule, int startY, boolean config) {
		return depthRule(block, List.of(), NoiseRules.of(noiseRule), startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRule noiseRule, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, List.of(), NoiseRules.of(noiseRule), getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition) {
		return depthRule(block, List.of(condition), NoiseRules.of(), defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, boolean config) {
		return depthRule(block, List.of(condition), NoiseRules.of(), defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, int startY) {
		return depthRule(block, List.of(condition), NoiseRules.of(), startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, int startY, boolean config) {
		return depthRule(block, List.of(condition), NoiseRules.of(), startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, List.of(condition), NoiseRules.of(), getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRules noiseRules) {
		return depthRule(block, List.of(condition), noiseRules, defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRules noiseRules, boolean config) {
		return depthRule(block, List.of(condition), noiseRules, defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRules noiseRules, int startY) {
		return depthRule(block, List.of(condition), noiseRules, startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRules noiseRules, int startY, boolean config) {
		return depthRule(block, List.of(condition), noiseRules, startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRules noiseRules, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, List.of(condition), noiseRules, getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRule noiseRule) {
		return depthRule(block, conditions, NoiseRules.of(noiseRule), defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRule noiseRule, boolean config) {
		return depthRule(block, conditions, NoiseRules.of(noiseRule), defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRule noiseRule, int startY) {
		return depthRule(block, conditions, NoiseRules.of(noiseRule), startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRule noiseRule, int startY, boolean config) {
		return depthRule(block, conditions, NoiseRules.of(noiseRule), startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRule noiseRule, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, conditions, NoiseRules.of(noiseRule), getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRule noiseRule) {
		return depthRule(block, List.of(condition), NoiseRules.of(noiseRule), defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRule noiseRule, boolean config) {
		return depthRule(block, List.of(condition), NoiseRules.of(noiseRule), defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRule noiseRule, int startY) {
		return depthRule(block, List.of(condition), NoiseRules.of(noiseRule), startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRule noiseRule, int startY, boolean config) {
		return depthRule(block, List.of(condition), NoiseRules.of(noiseRule), startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, NoiseRule noiseRule, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, List.of(condition), NoiseRules.of(noiseRule), getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRules noiseRules) {
		return depthRule(block, List.of(), noiseRules, defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRules noiseRules, boolean config) {
		return depthRule(block, List.of(), noiseRules, defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRules noiseRules, int startY) {
		return depthRule(block, List.of(), noiseRules, startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRules noiseRules, int startY, boolean config) {
		return depthRule(block, List.of(), noiseRules, startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, NoiseRules noiseRules, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, List.of(), noiseRules, getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions) {
		return depthRule(block, conditions, NoiseRules.of(), defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, boolean config) {
		return depthRule(block, conditions, NoiseRules.of(), defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, int startY) {
		return depthRule(block, conditions, NoiseRules.of(), startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, int startY, boolean config) {
		return depthRule(block, conditions, NoiseRules.of(), startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, conditions, NoiseRules.of(), getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRules noiseRules) {
		return depthRule(block, conditions, noiseRules, defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRules noiseRules, boolean config) {
		return depthRule(block, conditions, noiseRules, defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRules noiseRules, int startY) {
		return depthRule(block, conditions, noiseRules, startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRules noiseRules, int startY, boolean config) {
		return depthRule(block, conditions, noiseRules, startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, NoiseRules noiseRules, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, conditions, noiseRules, getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(
		Block block,
		List<SurfaceRules.ConditionSource> conditions,
		NoiseRules noiseRules,
		String key,
		VerticalAnchor startAnchor,
		VerticalAnchor transitionAnchor,
		boolean aboveDeepslate,
		boolean config
	) {
		SurfaceRules.ConditionSource verticalGradient = SurfaceRules.verticalGradient(key, startAnchor, transitionAnchor);
		SurfaceRules.RuleSource ruleSource = configuredRule(conditions(noises(internalDepthRule(FrozenSurfaceRules.makeStateRule(block), verticalGradient, block), noiseRules), conditions), config);
		if (aboveDeepslate) {
			return SurfaceRules.ifTrue(
				SurfaceRules.not(verticalGradient),
				ruleSource
			);
		}
		else return ruleSource;
	}

	private static SurfaceRules.RuleSource internalDepthRule(SurfaceRules.RuleSource rule, SurfaceRules.ConditionSource verticalGradient, Block block) {
		SurfaceRules.RuleSource finalRule = SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.not(SurfaceRules.ON_FLOOR),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.UNDER_FLOOR),
					SurfaceRules.ifTrue(
						SurfaceRules.not(verticalGradient),
						rule
					)
				)
			),
			SurfaceRules.ifTrue(
				SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
				SurfaceRules.ifTrue(
					SurfaceRules.not(verticalGradient),
					rule
				)
			),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_STEEP),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(
						SurfaceRules.steep(),
						rule
					),
					SurfaceRules.ifTrue(
						SurfaceRules.not(SurfaceRules.ON_FLOOR),
						SurfaceRules.ifTrue(
							SurfaceRules.stoneDepthCheck(-1, false, CaveSurface.FLOOR),
							rule
						)
					)
				)
			),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_MOUNTAIN),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.ON_FLOOR),
					SurfaceRules.ifTrue(
						SurfaceRules.UNDER_FLOOR,
						rule
					)
				)
			),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_STONY),
				SurfaceRules.sequence(
					SurfaceRules.ifTrue(
						SurfaceRules.UNDER_FLOOR,
						rule
					),
					SurfaceRules.ifTrue(
						SurfaceRules.ON_FLOOR,
						rule
					)
				)
			),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_WINDSWEPT_HILL),
				SurfaceRules.ifTrue(
					SurfaceRuleData.surfaceNoiseAbove(1.0),
					rule
				)
			),
			higherStoneRule(block)
		);
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.not(FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_BADLANDS)),
				finalRule
			),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_BADLANDS),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
					finalRule
				)
			)
		);
	}
}
