package net.legacy.bloom.worldgen;

import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.frozenblock.lib.worldgen.surface.api.SurfaceRuleEvents;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import org.jetbrains.annotations.NotNull;

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
    public static SurfaceRules.RuleSource modifiedJungles() {
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
        var rule = SurfaceRules.ifTrue(
                SurfaceRuleData.surfaceNoiseAbove(5.5),
                FrozenSurfaceRules.makeStateRule(Blocks.COARSE_DIRT)
        );
        return SurfaceRules.ifTrue(
                FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_STRIP_COARSE_DIRT),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                rule
                        ),
                        SurfaceRules.ifTrue(
                                SurfaceRules.UNDER_FLOOR,
                                rule
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

    public static SurfaceRules.RuleSource windsweptJungle() {
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(BloomBiomes.WINDSWEPT_JUNGLE),
                SurfaceRules.ifTrue(
                        SurfaceRuleData.surfaceNoiseAbove(2.5),
                        FrozenSurfaceRules.makeStateRule(Blocks.DIORITE)
                )
        );
    }

    // Presets

    public static SurfaceRules.RuleSource depthRule(TagKey<@NotNull Biome> biomes, Block block) {
        SurfaceRules.RuleSource rule = FrozenSurfaceRules.makeStateRule(block);
        return SurfaceRules.ifTrue(
                SurfaceRules.not(SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR)),
                SurfaceRules.ifTrue(
                        FrozenSurfaceRules.isBiomeTagOptimized(biomes),
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
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.DEEP_UNDER_FLOOR,
                                                rule
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.HAS_DEEPER_STONE),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(SurfaceRules.UNDER_FLOOR),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.VERY_DEEP_UNDER_FLOOR,
                                                        rule
                                                )
                                        )
                                )
                        )
                )
        );
    }

    @Override
    public void addOverworldSurfaceRules(List<SurfaceRules.RuleSource> context) {
        context.add(
                SurfaceRules.sequence(
                        aridRiversAndShores(),
                        tropicalRivers(),
                        modifiedJungles(),
                        beaches(),
                        gravellyRiversAndBeaches(),
                        coarseDirtStrips(),
                        windsweptJungle(),
                        windsweptSavanna(),
                        depthRule(BloomBiomeTags.HAS_DEPTH_STONE, Blocks.STONE),
                        depthRule(BloomBiomeTags.HAS_DEPTH_GRANITE, Blocks.GRANITE)
                )
        );
    }

    @Override
    public void addOverworldNoPrelimSurfaceRules(List<SurfaceRules.RuleSource> list) {}
}