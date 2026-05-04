package net.rebel459.bloom.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class BloomBiome {
	private static final List<BloomBiome> BIOMES = new ArrayList<>();
	private final ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(this.modID(), this.biomeID()));

	protected BloomBiome() {
		BIOMES.add(this);
	}

	public abstract String modID();

	public abstract String biomeID();

	public abstract float temperature();

	public abstract float downfall();

	public abstract boolean hasPrecipitation();

	public Biome.TemperatureModifier temperatureModifier() {
		return Biome.TemperatureModifier.NONE;
	}

	public abstract int waterColor();

	@Nullable
	public abstract Integer foliageColorOverride();

	@Nullable
	public abstract Integer dryFoliageColorOverride();

	@Nullable
	public abstract Integer grassColorOverride();

	public BiomeSpecialEffects.GrassColorModifier grassColorModifier() {
		return BiomeSpecialEffects.GrassColorModifier.NONE;
	}

	public abstract void fillEnvironmentAttributes(EnvironmentAttributeMap.Builder attributes);

	public final Biome create(BootstrapContext<Biome> entries) {
		final Biome.BiomeBuilder biomeBuilder = new Biome.BiomeBuilder();
		biomeBuilder.temperature(this.temperature())
			.temperatureAdjustment(this.temperatureModifier())
			.downfall(this.downfall())
			.hasPrecipitation(this.hasPrecipitation());

		var placedFeatures = entries.lookup(Registries.PLACED_FEATURE);
		var worldCarvers = entries.lookup(Registries.CONFIGURED_CARVER);
		final BiomeGenerationSettings.Builder featureBuilder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		this.addFeatures(featureBuilder);
		biomeBuilder.generationSettings(featureBuilder.build());

		final MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
		this.addSpawns(spawnBuilder);
		biomeBuilder.mobSpawnSettings(spawnBuilder.build());

		final BiomeSpecialEffects.Builder specialEffectsBuilder = new BiomeSpecialEffects.Builder()
			.waterColor(this.waterColor())
			.grassColorModifier(this.grassColorModifier());
		if (this.foliageColorOverride() != null) specialEffectsBuilder.foliageColorOverride(this.foliageColorOverride());
		if (this.dryFoliageColorOverride() != null) specialEffectsBuilder.dryFoliageColorOverride(this.dryFoliageColorOverride());
		if (this.grassColorOverride() != null) specialEffectsBuilder.grassColorOverride(this.grassColorOverride());
		biomeBuilder.specialEffects(specialEffectsBuilder.build());

		final EnvironmentAttributeMap.Builder attributes = EnvironmentAttributeMap.builder();
		this.fillEnvironmentAttributes(attributes);

		return biomeBuilder.putAttributes(attributes).build();
	}

	public abstract void addFeatures(BiomeGenerationSettings.Builder features);

	public abstract void addSpawns(MobSpawnSettings.Builder spawns);

	public ResourceKey<Biome> getKey() {
		return this.key;
	}

	public static ImmutableList<BloomBiome> getBloomBiomes() {
		return ImmutableList.copyOf(BIOMES);
	}

}
