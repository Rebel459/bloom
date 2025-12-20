package net.legacy.bloom.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import net.frozenblock.lib.worldgen.biome.api.parameters.Erosion;
import net.frozenblock.lib.worldgen.biome.api.parameters.Humidity;
import net.frozenblock.lib.worldgen.biome.api.parameters.Temperature;
import net.frozenblock.lib.worldgen.surface.api.SurfaceRuleEvents;
import net.legacy.bloom.worldgen.biome.WarmRiver;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class BloomBiomePlacement {

    static float TEMPERATURE_0 = -1F;
    static float TEMPERATURE_1 = -0.45F;
    static float TEMPERATURE_2 = -0.15F;
    static float TEMPERATURE_3 = 0.2F;
    static float TEMPERATURE_4 = 0.55F;
    static float TEMPERATURE_MAX = 1F;

    static float HUMIDITY_0 = -1F;
    static float HUMIDITY_1 = -0.35F;
    static float HUMIDITY_2 = -0.1F;
    static float HUMIDITY_3 = 0.1F;
    static float HUMIDITY_4 = 0.3F;
    static float HUMIDITY_MAX = 1F;

    static float EROSION_0 = -1F;
    static float EROSION_1 = -0.78F;
    static float EROSION_2 = -0.375F;
    static float EROSION_3 = -0.2225F;
    static float EROSION_4 = 0.05F;
    static float EROSION_5 = 0.45F;
    static float EROSION_6 = 0.55F;
    static float EROSION_MAX = 1F;

    public static void init() {
        BiomePlacement.addSubOverworld(
                Biomes.RIVER,
                WarmRiver.INSTANCE.getKey(),
                CriterionBuilder.allOf(
                        CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, TEMPERATURE_4, TEMPERATURE_MAX),
                        CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, HUMIDITY_0, HUMIDITY_4),
                        CriterionBuilder.value(BiomeParameterTargets.EROSION, EROSION_3, EROSION_5)
                )
        );
    }
}
