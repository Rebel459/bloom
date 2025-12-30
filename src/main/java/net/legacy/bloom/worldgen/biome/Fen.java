package net.legacy.bloom.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.frozenblock.lib.worldgen.biome.api.FrozenBiome;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.sound.BloomSounds;
import net.legacy.bloom.util.BiomeHelper;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.modifier.FloatModifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class Fen extends FrozenBiome {
    public static final float TEMP = 0.8F;
    public static final float DOWNFALL = 0.9F;
    public static final int WATER_COLOR = 3177591;
    public static final int FOLIAGE_COLOR = 6975545;
    public static final int DRY_FOLIAGE_COLOR = 8082228;
    public static final int GRASS_COLOR = 4415799;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final Fen INSTANCE = new Fen();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "fen";
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
    public @NotNull Integer foliageColorOverride() {
        return FOLIAGE_COLOR;
    }

    @Override
    public @NotNull Integer dryFoliageColorOverride() {
        return DRY_FOLIAGE_COLOR;
    }

    @Override
    public @NotNull Integer grassColorOverride() {
        return GRASS_COLOR;
    }

    @Override
    public void fillEnvironmentAttributes(EnvironmentAttributeMap.Builder builder) {
        builder.set(EnvironmentAttributes.SKY_COLOR, SKY_COLOR);
        builder.set(EnvironmentAttributes.BACKGROUND_MUSIC, BiomeHelper.music(BloomSounds.MUSIC_BIOME_FEN, false));
        builder.set(EnvironmentAttributes.WATER_FOG_END_DISTANCE, BiomeHelper.waterFogMultiplier(0.85F));
    }

    @Override
    public void addFeatures(BiomeGenerationSettings.Builder features) {
        BiomeDefaultFeatures.addFossilDecoration(features);
        OverworldBiomes.globalOverworldGeneration(features);
        BiomeDefaultFeatures.addDefaultOres(features);
        BiomeDefaultFeatures.addSwampClayDisk(features);
        features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
        BiomeDefaultFeatures.addFerns(features);
        BiomeDefaultFeatures.addSwampExtraVegetation(features);
        features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
    }

    @Override
    public void addSpawns(MobSpawnSettings.Builder spawns) {
        BiomeDefaultFeatures.farmAnimals(spawns);
        BiomeDefaultFeatures.swampSpawns(spawns, 70);
    }

    @Override
    public void injectToOverworld(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {}
}
