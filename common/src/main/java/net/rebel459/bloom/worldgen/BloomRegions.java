package net.rebel459.bloom.worldgen;

import dev.worldgen.lithostitched.api.event.AddRegionsEvent;
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.region.Region;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.tag.BloomBiomeTags;

public class BloomRegions {

	public static ResourceKey<Region> PINE = create("pine");
	public static ResourceKey<Region> LAVENDER = create("lavender");

	public static void init() {
		AddRegionsEvent.EVENT.register((registry, consumer) -> {
			consumer.accept(PINE, Level.OVERWORLD, registry.getOrThrow(BiomeTags.IS_TAIGA), 50);
			consumer.accept(LAVENDER, Level.OVERWORLD, HolderSet.direct(
					registry.getOrThrow(Biomes.PLAINS),
					registry.getOrThrow(Biomes.SUNFLOWER_PLAINS)
				), 50
			);
		});
	}

	private static ResourceKey<Region> create(String path) {
		return ResourceKey.create(LithostitchedRegistries.REGION, Bloom.id(path));
	}
}
