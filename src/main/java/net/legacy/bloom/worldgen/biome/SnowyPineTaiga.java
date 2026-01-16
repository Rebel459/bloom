package net.legacy.bloom.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.frozenblock.lib.worldgen.biome.api.FrozenBiome;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.sound.BloomSounds;
import net.legacy.bloom.util.BiomeHelper;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
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
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public final class SnowyPineTaiga extends FrozenBiome {
    public static final float TEMP = -0.5F;
    public static final float DOWNFALL = 0.4F;
    public static final int WATER_COLOR = BiomeHelper.Colors.FROZEN_WATER;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final SnowyPineTaiga INSTANCE = new SnowyPineTaiga();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "snowy_pine_taiga";
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
		BiomeDefaultFeatures.addFerns(features);
		BiomeDefaultFeatures.addDefaultOres(features);
		BiomeDefaultFeatures.addDefaultSoftDisks(features);
		BiomeDefaultFeatures.addTaigaTrees(features);
		BiomeDefaultFeatures.addDefaultFlowers(features);
		BiomeDefaultFeatures.addTaigaGrass(features);
		BiomeDefaultFeatures.addDefaultExtraVegetation(features, true);
		BiomeDefaultFeatures.addRareBerryBushes(features);
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
