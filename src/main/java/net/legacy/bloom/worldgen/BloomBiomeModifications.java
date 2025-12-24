package net.legacy.bloom.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.worldgen.biome.AridRiver;
import net.legacy.bloom.worldgen.biome.ColdRiver;
import net.legacy.bloom.worldgen.biome.TropicalRiver;
import net.minecraft.world.attribute.EnvironmentAttributes;

public final class BloomBiomeModifications {

	//modificationContext.getAttributes().set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.createGameMusic(LaLSounds.SAVANNA_MUSIC)));

	public static void init() {
		BiomeModifications.create(Bloom.id("modified_deserts")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.MODIFIED_DESERTS),
				(selectionContext, modificationContext) -> {
					modificationContext.getEffects().setWaterColor(AridRiver.WATER_COLOR);
					modificationContext.getEffects().setGrassColor(AridRiver.GRASS_COLOR);
					modificationContext.getEffects().setFoliageColor(AridRiver.FOLIAGE_COLOR);
					modificationContext.getEffects().setDryFoliageColor(AridRiver.DRY_FOLIAGE_COLOR);
				}
		);
		BiomeModifications.create(Bloom.id("modified_jungles")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.MODIFIED_JUNGLES),
				(selectionContext, modificationContext) -> {
					modificationContext.getEffects().setWaterColor(TropicalRiver.WATER_COLOR);
				}
		);
		BiomeModifications.create(Bloom.id("modified_cold_biomes")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.MODIFIED_COLD_BIOMES),
				(selectionContext, modificationContext) -> {
					modificationContext.getEffects().setWaterColor(ColdRiver.WATER_COLOR);
					modificationContext.getEffects().setGrassColor(ColdRiver.GRASS_COLOR);
					modificationContext.getEffects().setFoliageColor(ColdRiver.FOLIAGE_COLOR);
					modificationContext.getEffects().setDryFoliageColor(ColdRiver.DRY_FOLIAGE_COLOR);
				}
		);
		BiomeModifications.create(Bloom.id("modified_frozen_biomes")).add(
				ModificationPhase.REPLACEMENTS,
				BiomeSelectors.tag(BloomBiomeTags.MODIFIED_FROZEN_BIOMES),
				(selectionContext, modificationContext) -> {
					modificationContext.getEffects().setWaterColor(1594256);
					modificationContext.getEffects().setGrassColor(8434839);
					modificationContext.getEffects().setFoliageColor(6332795);
					modificationContext.getEffects().setDryFoliageColor(10122831);
				}
		);
	}
}