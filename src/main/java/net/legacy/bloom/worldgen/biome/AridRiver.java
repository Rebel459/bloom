package net.legacy.bloom.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.frozenblock.lib.worldgen.biome.api.FrozenBiome;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.util.BiomeHelper;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class AridRiver extends FrozenBiome {
    public static final float TEMP = 1.75F;
    public static final float DOWNFALL = 0.05F;
    public static final int WATER_COLOR = 176357;
    public static final int FOLIAGE_COLOR = 5884220;
    public static final int DRY_FOLIAGE_COLOR = 10581064;
    public static final int GRASS_COLOR = 3193611;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final AridRiver INSTANCE = new AridRiver();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "arid_river";
    }

    @Override
    public float temperature() {
        return TEMP;
    }

    @Override
    public float downfall() {
        return DOWNFALL;
    }

    @Override
    public boolean hasPrecipitation() {
        return true;
    }

    @Override
    public int waterColor() {
        return WATER_COLOR;
    }

    @Override
    public @Nullable Integer foliageColorOverride() {
        return FOLIAGE_COLOR;
    }

    @Override
    public @Nullable Integer dryFoliageColorOverride() {
        return DRY_FOLIAGE_COLOR;
    }

    @Override
    public @Nullable Integer grassColorOverride() {
        return GRASS_COLOR;
    }

    @Override
    public void fillEnvironmentAttributes(EnvironmentAttributeMap.Builder builder) {
        builder.set(EnvironmentAttributes.SKY_COLOR, SKY_COLOR);
        builder.set(EnvironmentAttributes.BACKGROUND_MUSIC, BiomeHelper.music(SoundEvents.MUSIC_GAME, true));
        builder.set(EnvironmentAttributes.SNOW_GOLEM_MELTS, true);
        builder.set(EnvironmentAttributes.WATER_FOG_COLOR, 270131);
    }

    @Override
    public void addFeatures(BiomeGenerationSettings.Builder features) {
        OverworldBiomes.globalOverworldGeneration(features);
        BiomeDefaultFeatures.addDefaultOres(features);
        BiomeDefaultFeatures.addDefaultSoftDisks(features);
        BiomeDefaultFeatures.addNearWaterVegetation(features);
        BiomeDefaultFeatures.addDesertVegetation(features);
        BiomeDefaultFeatures.addDefaultMushrooms(features);
        features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_RIVER);
    }

    @Override
    public void addSpawns(MobSpawnSettings.Builder spawns) {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.WATER_CREATURE, 2, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 1, 4))
                .addSpawn(MobCategory.WATER_AMBIENT, 5, new MobSpawnSettings.SpawnerData(EntityType.SALMON, 1, 5));
        BiomeDefaultFeatures.commonSpawns(builder);
        builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 1, 1));
    }

    @Override
    public void injectToOverworld(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {}
}
