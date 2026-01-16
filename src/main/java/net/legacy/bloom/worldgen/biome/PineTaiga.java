package net.legacy.bloom.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import java.util.function.Consumer;
import net.frozenblock.lib.worldgen.biome.api.FrozenBiome;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.sound.BloomSounds;
import net.legacy.bloom.util.BiomeHelper;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.NotNull;

public final class PineTaiga extends FrozenBiome {
    public static final float TEMP = 0.8F;
    public static final float DOWNFALL = 0.9F;
    public static final int WATER_COLOR = BiomeHelper.Colors.COLD_WATER;
    public static final int FOLIAGE_COLOR = ColdRiver.FOLIAGE_COLOR;
    public static final int DRY_FOLIAGE_COLOR = ColdRiver.DRY_FOLIAGE_COLOR;
    public static final int GRASS_COLOR = ColdRiver.GRASS_COLOR;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final PineTaiga INSTANCE = new PineTaiga();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "pine_taiga";
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
        builder.set(EnvironmentAttributes.BACKGROUND_MUSIC, BiomeHelper.music(BloomSounds.MUSIC_BIOME_TAIGA, false));
    }

    @Override
    public void addFeatures(BiomeGenerationSettings.Builder features) {
        OverworldBiomes.globalOverworldGeneration(features);
		BiomeDefaultFeatures.addFerns(features);
		BiomeDefaultFeatures.addDefaultOres(features);
		BiomeDefaultFeatures.addDefaultSoftDisks(features);
		BiomeDefaultFeatures.addTaigaTrees(features);
		BiomeDefaultFeatures.addDefaultFlowers(features);
		BiomeDefaultFeatures.addTaigaGrass(features);
		BiomeDefaultFeatures.addDefaultExtraVegetation(features, true);
		BiomeDefaultFeatures.addCommonBerryBushes(features);
    }

    @Override
    public void addSpawns(MobSpawnSettings.Builder spawns) {
		BiomeDefaultFeatures.farmAnimals(spawns);
		spawns.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4))
			.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3))
			.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4));
		BiomeDefaultFeatures.commonSpawns(spawns);
    }

    @Override
    public void injectToOverworld(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {}
}
