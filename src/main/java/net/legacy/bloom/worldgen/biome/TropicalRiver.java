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

public final class TropicalRiver extends FrozenBiome {
    public static final float TEMP = 0.95F;
    public static final float DOWNFALL = 0.9F;
    public static final int WATER_COLOR = 2001635;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final TropicalRiver INSTANCE = new TropicalRiver();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "tropical_river";
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
        return null;
    }

    @Override
    public @Nullable Integer dryFoliageColorOverride() {
        return null;
    }

    @Override
    public @Nullable Integer grassColorOverride() {
        return null;
    }

    @Override
    public void fillEnvironmentAttributes(EnvironmentAttributeMap.Builder builder) {
        builder.set(EnvironmentAttributes.SKY_COLOR, SKY_COLOR);
        builder.set(EnvironmentAttributes.BACKGROUND_MUSIC, BiomeHelper.music(SoundEvents.MUSIC_BIOME_JUNGLE, true));
    }

    @Override
    public void addFeatures(BiomeGenerationSettings.Builder features) {
        OverworldBiomes.globalOverworldGeneration(features);
        BiomeDefaultFeatures.addDefaultOres(features);
        BiomeDefaultFeatures.addDefaultSoftDisks(features);
        BiomeDefaultFeatures.addWaterTrees(features);
        BiomeDefaultFeatures.addBushes(features);
        BiomeDefaultFeatures.addDefaultMushrooms(features);
        BiomeDefaultFeatures.addNearWaterVegetation(features);
        features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_RIVER);
    }

    @Override
    public void addSpawns(MobSpawnSettings.Builder spawns) {
        spawns.addSpawn(MobCategory.WATER_CREATURE, 2, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 1, 4));
        spawns.addSpawn(MobCategory.WATER_AMBIENT, 5, new MobSpawnSettings.SpawnerData(EntityType.TROPICAL_FISH, 1, 5));
        BiomeDefaultFeatures.baseJungleSpawns(spawns);
        spawns.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 1, 1));
    }

    @Override
    public void injectToOverworld(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {}
}
