package net.legacy.bloom.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
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

	public static SurfaceRules.ConditionSource noise(NoiseRules.Type type, float point) {
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

	public static SurfaceRules.ConditionSource noise(NoiseRules.Type type, float min, float max) {
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

	public static SurfaceRules.ConditionSource aboveDeepslate() {
		return aboveDeepslate("deepslate");
	}

	public static SurfaceRules.ConditionSource aboveDeepslate(String key) {
		return aboveDeepslate(defaultStartY, key);
	}

	public static SurfaceRules.ConditionSource aboveDeepslate(int startY) {
		return aboveDeepslate(startY, getKey(startY, defaultTransitionBlocks));
	}

	public static SurfaceRules.ConditionSource aboveDeepslate(int startY, String key) {
		return aboveDeepslate(startY, defaultTransitionBlocks, key);
	}

	public static SurfaceRules.ConditionSource aboveDeepslate(int startY, int transitonBlocks) {
		return aboveDeepslate(startY, transitonBlocks, getKey(startY, transitonBlocks));
	}

	public static SurfaceRules.ConditionSource aboveDeepslate(int startY, int transitonBlocks, String key) {
		return aboveDeepslate(VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitonBlocks), key);
	}

	public static SurfaceRules.ConditionSource aboveDeepslate(VerticalAnchor startAnchor, VerticalAnchor transitionAnchor, boolean matchDeepslate) {
		String key;
		if (matchDeepslate) key = "deepslate";
		else key = "depth";
		return aboveDeepslate(startAnchor, transitionAnchor, key);
	}

	public static SurfaceRules.ConditionSource aboveDeepslate(VerticalAnchor startAnchor, VerticalAnchor transitionAnchor, String key) {
		return SurfaceRules.not(SurfaceRules.verticalGradient(key, startAnchor, transitionAnchor));
	}

	public static SurfaceRules.RuleSource collectedRule(List<SurfaceRules.ConditionSource> conditions, SurfaceRules.RuleSource rule) {
		if (conditions.isEmpty()) {
			return rule;
		}

		SurfaceRules.RuleSource rules = SurfaceRules.ifTrue(conditions.getFirst(), rule);

		for (int i = 1; i < conditions.size(); i++) {
			rules = SurfaceRules.ifTrue(conditions.get(i), rules);
		}

		return rules;
	}

	public static SurfaceRules.RuleSource configuredRule(boolean config, SurfaceRules.RuleSource ruleSource) {
		return SurfaceRules.ifTrue(
			Configured.pass(config),
			ruleSource
		);
	}

	public static float greaterThan(float value) {
		return value + 0.001F;
	}
	public static float lessThan(float value) {
		return value - 0.001F;
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

	public static SurfaceRules.RuleSource simpleDepthRule(Block block) {
		return simpleDepthRule(block, true);
	}

	public static SurfaceRules.RuleSource simpleDepthRule(Block block, boolean config) {
		return simpleDepthRule(block, defaultStartY, config);
	}

	public static SurfaceRules.RuleSource simpleDepthRule(Block block, int startY) {
		return simpleDepthRule(block, startY, true);
	}

	public static SurfaceRules.RuleSource simpleDepthRule(Block block, int startY, boolean config) {
		return simpleDepthRule(block, startY, defaultTransitionBlocks);
	}

	public static SurfaceRules.RuleSource simpleDepthRule(Block block, int startY, int transitionBlocks) {
		return simpleDepthRule(block, startY, transitionBlocks, true);
	}

	public static SurfaceRules.RuleSource simpleDepthRule(Block block, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, List.of(), startY, transitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition) {
		return depthRule(block, List.of(condition), defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, boolean config) {
		return depthRule(block, List.of(condition), defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, int startY) {
		return depthRule(block, List.of(condition), startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, int startY, boolean config) {
		return depthRule(block, List.of(condition), startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, int startY, int transitionBlocks) {
		return depthRule(block, List.of(condition), startY, transitionBlocks, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, SurfaceRules.ConditionSource condition, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, List.of(condition), getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions) {
		return depthRule(block, conditions, defaultStartY);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, boolean config) {
		return depthRule(block, conditions, defaultStartY, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, int startY) {
		return depthRule(block, conditions, startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, int startY, boolean config) {
		return depthRule(block, conditions, startY, defaultTransitionBlocks, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, int startY, int transitionBlocks) {
		return depthRule(block, conditions, startY, transitionBlocks,true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, List<SurfaceRules.ConditionSource> conditions, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, conditions, getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), startY >= 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(
		Block block,
		List<SurfaceRules.ConditionSource> conditions,
		String key,
		VerticalAnchor startAnchor,
		VerticalAnchor transitionAnchor,
		boolean aboveDeepslate,
		boolean config
	) {
		SurfaceRules.ConditionSource verticalGradient = SurfaceRules.verticalGradient(key, startAnchor, transitionAnchor);
		SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
		SurfaceRules.RuleSource depthRule = configuredRule(
			config,
			collectedRule(
				conditions,
				SurfaceRules.sequence(
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
				)
			)
		);
		if (aboveDeepslate) {
			depthRule = SurfaceRules.ifTrue(
				aboveDeepslate(),
				depthRule
			);
		}
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				SurfaceRules.not(FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_BADLANDS)),
				depthRule
			),
			SurfaceRules.ifTrue(
				FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.INTERNAL_BADLANDS),
				SurfaceRules.ifTrue(
					SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
					depthRule
				)
			)
		);
	}

	public static class Configured implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<Configured> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.BOOL.fieldOf("pass").forGetter(r -> r.config)
				).apply(instance, Configured::new)
			)
		);

		private final boolean config;

		public Configured(boolean config) {
			this.config = config;
		}

		@Override
		public SurfaceRules.Condition apply(SurfaceRules.Context context) {
			return new Condition(config);
		}

		private static final class Condition implements SurfaceRules.Condition {

			private final boolean value;

			Condition(boolean value) {
				this.value = value;
			}

			@Override
			public boolean test() {
				return value;
			}
		}

		public static SurfaceRules.ConditionSource pass(boolean config) {
			return new Configured(config);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}
}
