package net.legacy.bloom.registry;

import net.frozenblock.lib.worldgen.surface.api.FrozenSurfaceRules;
import net.frozenblock.lib.worldgen.surface.api.SurfaceRuleEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public final class BloomSurfaceRules implements SurfaceRuleEvents.OverworldSurfaceRuleCallback, SurfaceRuleEvents.OverworldSurfaceRuleNoPrelimSurfaceCallback {
    public static SurfaceRules.RuleSource warmRivers() {
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(BloomBiomes.WARM_RIVER),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        FrozenSurfaceRules.makeStateRule(Blocks.COARSE_DIRT)
                )
        );
    }

    @Override
    public void addOverworldSurfaceRules(List<SurfaceRules.RuleSource> context) {
        context.add(
                SurfaceRules.sequence(
                        warmRivers()
                )
        );
    }

    @Override
    public void addOverworldNoPrelimSurfaceRules(List<SurfaceRules.RuleSource> list) {}
}