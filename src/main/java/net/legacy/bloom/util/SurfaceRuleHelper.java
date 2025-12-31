package net.legacy.bloom.util;

import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.util.rules.DownfallRule;
import net.legacy.bloom.util.rules.TemperatureRule;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class SurfaceRuleHelper {

	public static SurfaceRules.RuleSource configuredRule(SurfaceRules.RuleSource ruleSource, boolean config) {
		if (config) return ruleSource;
		else return SurfaceRules.sequence();
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

	public static String getKey(int startY, int transitionBlocks) {
		if (startY == 0 && transitionBlocks == 8) return "deepslate";
		else return "depth";
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

	public static SurfaceRules.RuleSource higherStoneRule(Block block, TagKey<Biome> biomes) {
		final SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
		return SurfaceRules.ifTrue(
			FrozenSurfaceRules.isBiomeTagOptimized(biomes),
			SurfaceRules.ifTrue(
				SurfaceRules.not(SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR)),
				SurfaceRules.ifTrue(
					FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_HIGHER_DEPTH),
					SurfaceRules.ifTrue(
						SurfaceRules.UNDER_FLOOR,
						rule
					)
				)
			)
		);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, TagKey<Biome> biomes) {
		return depthRule(block, biomes, 0);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, TagKey<Biome> biomes, boolean config) {
		return depthRule(block, biomes, 0, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, TagKey<Biome> biomes, int startY) {
		return depthRule(block, biomes, startY, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, TagKey<Biome> biomes, int startY, boolean config) {
		return depthRule(block, biomes, startY, 8, config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, TagKey<Biome> biomes, int startY, int transitionBlocks) {
		return depthRule(block, biomes, startY, transitionBlocks, true);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, TagKey<Biome> biomes, int startY, int transitionBlocks, boolean config) {
		return depthRule(block, biomes, getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks), config);
	}

	public static SurfaceRules.RuleSource depthRule(Block block, TagKey<Biome> biomes, String key, VerticalAnchor startAnchor, VerticalAnchor transitionAnchor, boolean config) {
		final SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
		return configuredRule(
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					FrozenSurfaceRules.isBiomeTagOptimized(biomes),
					SurfaceRules.sequence(
						SurfaceRules.ifTrue(
							SurfaceRules.not(SurfaceRules.ON_FLOOR),
							SurfaceRules.ifTrue(
								SurfaceRules.not(SurfaceRules.UNDER_FLOOR),
								SurfaceRules.ifTrue(
									SurfaceRules.not(SurfaceRules.verticalGradient(key, startAnchor, transitionAnchor)),
									rule
								)
							)
						),
						SurfaceRules.ifTrue(
							SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
							SurfaceRules.ifTrue(
								SurfaceRules.not(SurfaceRules.verticalGradient(key, startAnchor, transitionAnchor)),
								rule
							)
						)
					)
				),
				higherStoneRule(block, biomes)
			),
			config
		);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float point) {
		return temperatureDepthRule(block, point, true);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float point, boolean config) {
		return temperatureDepthRule(block, point - 0.001F, point + 0.001F, config);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float min, float max) {
		return temperatureDepthRule(block, min, max, true);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float min, float max, boolean config) {
		int startY = 0;
		return temperatureDepthRule(block, min, max, startY, config);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float min, float max, int startY) {
		return climateDepthRule(block, min, max, -10F, 10F, startY);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float min, float max, int startY, boolean config) {
		return climateDepthRule(block, min, max, -10F, 10F, startY, config);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float point) {
		return downfallDepthRule(block, point, true);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float point, boolean config) {
		return downfallDepthRule(block, point - 0.001F, point + 0.001F, config);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float min, float max) {
		return downfallDepthRule(block, min, max, true);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float min, float max, boolean config) {
		int startY = 0;
		return downfallDepthRule(block, min, max, startY, config);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float min, float max, int startY) {
		return climateDepthRule(block, -10F, 10F, min, max, startY);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float min, float max, int startY, boolean config) {
		return climateDepthRule(block, -10F, 10F, min, max, startY, config);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperaturePoint, float downfallPoint) {
		return climateDepthRule(block, temperaturePoint, downfallPoint, true);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperaturePoint, float downfallPoint, boolean config) {
		return climateDepthRule(block, temperaturePoint - 0.001F, temperaturePoint + 0.001F, downfallPoint - 0.001F, downfallPoint + 0.001F, 0, config);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperatureMin, float temperatureMax, float downfallPoint) {
		return climateDepthRule(block, temperatureMin, temperatureMax, downfallPoint, true);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperatureMin, float temperatureMax, float downfallPoint, boolean config) {
		return climateDepthRule(block, temperatureMin, temperatureMax, downfallPoint - 0.001F, downfallPoint + 0.001F, 0, config);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperatureMin, float temperatureMax, float downfallMin, float downfallMax) {
		int startY = 0;
		return climateDepthRule(
			block,
			temperatureMin, temperatureMax,
			downfallMin, downfallMax,
			startY
		);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperatureMin, float temperatureMax, float downfallMin, float downfallMax, int startY) {
		return climateDepthRule(block, temperatureMin, temperatureMax, downfallMin, downfallMax, startY, true);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperatureMin, float temperatureMax, float downfallMin, float downfallMax, int startY, boolean config) {
		int transitionBlocks = 8;
		return climateDepthRule(block, temperatureMin, temperatureMax, downfallMin, downfallMax, startY, transitionBlocks, config);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperatureMin, float temperatureMax, float downfallMin, float downfallMax, int startY, int transitionBlocks) {
		return climateDepthRule(
			block,
			temperatureMin, temperatureMax,
			downfallMin, downfallMax,
			getKey(startY, transitionBlocks),
			VerticalAnchor.absolute(startY),
			VerticalAnchor.absolute(startY + transitionBlocks),
			true
		);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float temperatureMin, float temperatureMax, float downfallMin, float downfallMax, int startY, int transitionBlocks, boolean config) {
		return climateDepthRule(
			block,
			temperatureMin, temperatureMax,
			downfallMin, downfallMax,
			getKey(startY, transitionBlocks),
			VerticalAnchor.absolute(startY),
			VerticalAnchor.absolute(startY + transitionBlocks),
			config
		);
	}

	public static SurfaceRules.RuleSource climateDepthRule(
		Block block,
		float temperatureMin, float temperatureMax,
		float downfallMin, float downfallMax,
		String key,
		VerticalAnchor startAnchor,
		VerticalAnchor transitionAnchor,
		boolean config
	) {
		final SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
		return configuredRule(
			SurfaceRules.sequence(
				SurfaceRules.ifTrue(
					TemperatureRule.temperature(temperatureMin, temperatureMax),
					SurfaceRules.ifTrue(
						DownfallRule.downfall(downfallMin, downfallMax),
						SurfaceRules.sequence(
							SurfaceRules.ifTrue(
								SurfaceRules.not(SurfaceRules.ON_FLOOR),
								SurfaceRules.ifTrue(
									SurfaceRules.not(SurfaceRules.UNDER_FLOOR),
									SurfaceRules.ifTrue(
										SurfaceRules.not(SurfaceRules.verticalGradient(key, startAnchor, transitionAnchor)),
										rule
									)
								)
							),
							SurfaceRules.ifTrue(
								SurfaceRules.not(SurfaceRules.abovePreliminarySurface()),
								SurfaceRules.ifTrue(
									SurfaceRules.not(SurfaceRules.verticalGradient(key, startAnchor, transitionAnchor)),
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
							)
						)
					)
				)
			),
			config
		);
	}
}
