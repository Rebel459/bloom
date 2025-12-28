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

public final class WindsweptJungle extends FrozenBiome {
    public static final float TEMP = 0.95F;
    public static final float DOWNFALL = 0.9F;
    public static final int WATER_COLOR = 2001635;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final WindsweptJungle INSTANCE = new WindsweptJungle();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "windswept_jungle";
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
        builder.set(EnvironmentAttributes.SNOW_GOLEM_MELTS, true);
    }

    @Override
    public void addFeatures(BiomeGenerationSettings.Builder features) {
        OverworldBiomes.globalOverworldGeneration(features);
        BiomeDefaultFeatures.addDefaultOres(features);
        BiomeDefaultFeatures.addDefaultSoftDisks(features);
        BiomeDefaultFeatures.addLightBambooVegetation(features);
        BiomeDefaultFeatures.addJungleTrees(features);
        BiomeDefaultFeatures.addWarmFlowers(features);
        BiomeDefaultFeatures.addJungleGrass(features);
        BiomeDefaultFeatures.addDefaultMushrooms(features);
        BiomeDefaultFeatures.addDefaultExtraVegetation(features, true);
        BiomeDefaultFeatures.addJungleVines(features);
        BiomeDefaultFeatures.addJungleMelons(features);
    }

    @Override
    public void addSpawns(MobSpawnSettings.Builder spawns) {
        BiomeDefaultFeatures.baseJungleSpawns(spawns);
        spawns.addSpawn(MobCategory.CREATURE, 40, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 1, 2))
                .addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 1, 3))
                .addSpawn(MobCategory.CREATURE, 1, new MobSpawnSettings.SpawnerData(EntityType.PANDA, 1, 2));
    }

    @Override
    public void injectToOverworld(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {}
}
