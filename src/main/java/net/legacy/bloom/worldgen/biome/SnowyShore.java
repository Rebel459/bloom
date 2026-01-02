package net.legacy.bloom.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import java.util.function.Consumer;
import net.frozenblock.lib.worldgen.biome.api.FrozenBiome;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.util.BiomeHelper;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.NotNull;

public final class SnowyShore extends FrozenBiome {
    public static final float TEMP = 0.05F;
    public static final float DOWNFALL = 0.3F;
    public static final int WATER_COLOR = 1596048;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final SnowyShore INSTANCE = new SnowyShore();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "snowy_shore";
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
        return false;
    }

    @Override
    public int waterColor() {
        return WATER_COLOR;
    }

    @Override
    public Integer foliageColorOverride() {
        return null;
    }

    @Override
    public Integer dryFoliageColorOverride() {
        return null;
    }

    @Override
    public Integer grassColorOverride() {
        return null;
    }

    @Override
    public void fillEnvironmentAttributes(EnvironmentAttributeMap.Builder builder) {
        builder.set(EnvironmentAttributes.SKY_COLOR, SKY_COLOR);
        builder.set(EnvironmentAttributes.BACKGROUND_MUSIC, BiomeHelper.music(SoundEvents.MUSIC_GAME, false));
    }

    @Override
    public void addFeatures(BiomeGenerationSettings.Builder features) {
        OverworldBiomes.globalOverworldGeneration(features);
		BiomeDefaultFeatures.addDefaultOres(features);
		BiomeDefaultFeatures.addDefaultSoftDisks(features);
		BiomeDefaultFeatures.addDefaultFlowers(features);
		BiomeDefaultFeatures.addDefaultGrass(features);
		BiomeDefaultFeatures.addDefaultMushrooms(features);
		BiomeDefaultFeatures.addDefaultExtraVegetation(features, true);
    }

    @Override
    public void addSpawns(MobSpawnSettings.Builder spawns) {
        BiomeDefaultFeatures.snowySpawns(spawns, true);
    }

    @Override
    public void injectToOverworld(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {}
}
