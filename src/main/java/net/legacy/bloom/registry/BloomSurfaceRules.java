package net.legacy.bloom.registry;

import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.frozenblock.lib.worldgen.surface.api.SurfaceRuleEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public final class BloomSurfaceRules implements SurfaceRuleEvents.OverworldSurfaceRuleCallback, SurfaceRuleEvents.OverworldSurfaceRuleNoPrelimSurfaceCallback {

    public static SurfaceRules.RuleSource warmRivers() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(BloomBiomes.ARID_RIVER),
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                FrozenSurfaceRules.makeStateRule(Blocks.COARSE_DIRT)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(BloomBiomes.ARID_RIVER),
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

    @Override
    public void addOverworldSurfaceRules(List<SurfaceRules.RuleSource> context) {
        context.add(
                SurfaceRules.sequence(
                        warmRivers(),
                        tropicalRivers()
                )
        );
    }

    @Override
    public void addOverworldNoPrelimSurfaceRules(List<SurfaceRules.RuleSource> list) {}
}