package net.legacy.bloom.util;

import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.util.rules.DownfallRule;
import net.legacy.bloom.util.rules.TemperatureRule;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class SurfaceRuleHelper {

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

	public static SurfaceRules.RuleSource higherStoneRule(TagKey<Biome> biomes) {
		return higherStoneRule(biomes, Blocks.STONE);
	}

	public static SurfaceRules.RuleSource higherStoneRule(TagKey<Biome> biomes, Block block) {
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

	public static SurfaceRules.RuleSource depthRule(TagKey<Biome> biomes, Block block) {
		return depthRule(biomes, block,0, 8);
	}

	public static SurfaceRules.RuleSource depthRule(TagKey<Biome> biomes, Block block, int startY) {
		return depthRule(biomes, block, startY, 8);
	}

	public static SurfaceRules.RuleSource depthRule(TagKey<Biome> biomes, Block block, int startY, int transitionBlocks) {
		return depthRule(biomes, block, getKey(startY, transitionBlocks), VerticalAnchor.absolute(startY), VerticalAnchor.absolute(startY + transitionBlocks));
	}

	public static SurfaceRules.RuleSource depthRule(TagKey<Biome> biomes, Block block, String key, VerticalAnchor startAnchor, VerticalAnchor transitionAnchor) {
		final SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
		return SurfaceRules.sequence(
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
			higherStoneRule(biomes, block)
		);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float min, float max) {
		int startY = 0;
		return temperatureDepthRule(block, min, max, startY);
	}

	public static SurfaceRules.RuleSource temperatureDepthRule(Block block, float min, float max, int startY) {
		return climateDepthRule(block, min, max, -10F, 10F, startY);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float min, float max) {
		int startY = 0;
		return downfallDepthRule(block, min, max, startY);
	}

	public static SurfaceRules.RuleSource downfallDepthRule(Block block, float min, float max, int startY) {
		return climateDepthRule(block, -10F, 10F, min, max, startY);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float tempMin, float tempMax, float downMin, float downMax) {
		int startY = 0;
		int transitionBlocks = 8;
		return climateDepthRule(
			block,
			tempMin, tempMax,
			downMin, downMax,
			getKey(startY, transitionBlocks),
			VerticalAnchor.absolute(startY),
			VerticalAnchor.absolute(startY + transitionBlocks)
		);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float tempMin, float tempMax, float downMin, float downMax, int startY) {
		int transitionBlocks = 8;
		return climateDepthRule(block, tempMin, tempMax, downMin, downMax, startY, transitionBlocks);
	}

	public static SurfaceRules.RuleSource climateDepthRule(Block block, float tempMin, float tempMax, float downMin, float downMax, int startY, int transitionBlocks) {
		return climateDepthRule(
			block,
			tempMin, tempMax,
			downMin, downMax,
			getKey(startY, transitionBlocks),
			VerticalAnchor.absolute(startY),
			VerticalAnchor.absolute(startY + transitionBlocks)
		);
	}

	public static SurfaceRules.RuleSource climateDepthRule(
		Block block,
		float tempMin, float tempMax,
		float downMin, float downMax,
		String key,
		VerticalAnchor startAnchor,
		VerticalAnchor transitionAnchor
	) {
		final SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
		return SurfaceRules.sequence(
			SurfaceRules.ifTrue(
				TemperatureRule.temperature(tempMin, tempMax),
				SurfaceRules.ifTrue(
					DownfallRule.downfall(downMin, downMax),
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
		);
	}
}
