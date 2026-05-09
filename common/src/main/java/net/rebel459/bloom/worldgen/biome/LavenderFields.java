package net.rebel459.bloom.worldgen.biome;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.sound.BloomSounds;
import net.rebel459.bloom.util.BiomeHelper;
import net.rebel459.bloom.util.BloomBiome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LavenderFields extends BloomBiome {
	public static final float TEMP = 0.8F;
	public static final float DOWNFALL = 0.4F;
    public static final int WATER_COLOR = BiomeHelper.Colors.DEFAULT_WATER;
    public static final int SKY_COLOR = OverworldBiomes.calculateSkyColor(TEMP);
    public static final LavenderFields INSTANCE = new LavenderFields();

    public String modID() {
        return Bloom.MOD_ID;
    }

    @Override
    public String biomeID() {
        return "lavender_fields";
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
    }

    @Override
    public void addFeatures(BiomeGenerationSettings.Builder features) {
        OverworldBiomes.globalOverworldGeneration(features);
		BiomeDefaultFeatures.addPlainGrass(features);
		BiomeDefaultFeatures.addBushes(features);
		BiomeDefaultFeatures.addDefaultOres(features);
		BiomeDefaultFeatures.addDefaultSoftDisks(features);
		features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);
		features.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
		BiomeDefaultFeatures.addDefaultMushrooms(features);
		BiomeDefaultFeatures.addDefaultExtraVegetation(features, true);
    }

    @Override
    public void addSpawns(MobSpawnSettings.Builder spawns) {
		BiomeDefaultFeatures.plainsSpawns(spawns);
		BiomeDefaultFeatures.commonSpawns(spawns);
    }
}
