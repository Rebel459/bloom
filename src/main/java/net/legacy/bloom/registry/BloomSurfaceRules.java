package net.legacy.bloom.registry;

import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.frozenblock.lib.worldgen.surface.api.SurfaceRuleEvents;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class BloomSurfaceRules implements SurfaceRuleEvents.OverworldSurfaceRuleCallback, SurfaceRuleEvents.OverworldSurfaceRuleNoPrelimSurfaceCallback {

    public static SurfaceRules.RuleSource aridRiversAndShores() {
        return SurfaceRules.ifTrue(
                FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.IS_COARSE),
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
                        FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.MODIFIED_JUNGLES),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(SurfaceRules.waterBlockCheck(0, 0)),
                                        FrozenSurfaceRules.makeStateRule(Blocks.MUD)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.MODIFIED_JUNGLES),
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
                FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.IS_SANDY),
                sandyBiomeRules()
        );
    }
    public static SurfaceRules.RuleSource gravellyRiversAndBeaches() {
        return SurfaceRules.ifTrue(
                FrozenSurfaceRules.isBiomeTagOptimized(BloomBiomeTags.IS_GRAVELLY),
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

    @Override
    public void addOverworldSurfaceRules(List<SurfaceRules.RuleSource> context) {
        context.add(
                SurfaceRules.sequence(
                        aridRiversAndShores(),
                        tropicalRivers(),
                        modifiedJungles(),
                        beaches(),
                        gravellyRiversAndBeaches()
                )
        );
    }

    @Override
    public void addOverworldNoPrelimSurfaceRules(List<SurfaceRules.RuleSource> list) {}
}