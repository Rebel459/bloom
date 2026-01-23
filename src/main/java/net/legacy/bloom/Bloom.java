package net.legacy.bloom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.legacy.bloom.config.BloomConfig;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.registry.BloomBlockEntities;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomConditionSources;
import net.legacy.bloom.registry.BloomCreativeInventory;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.registry.BloomLootTables;
import net.legacy.bloom.registry.BloomVillagerTrades;
import net.legacy.bloom.sound.BloomSounds;
import net.legacy.bloom.tag.BloomBiomeTags;
import net.legacy.bloom.util.BiomeHelper;
import net.legacy.bloom.util.ClimateCommand;
import net.legacy.bloom.worldgen.BloomBiomeModifications;
import net.legacy.bloom.worldgen.BloomBiomePlacement;
import net.legacy.bloom.worldgen.BloomFeatures;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class Bloom implements ModInitializer {
	public static final String MOD_ID = "bloom";

	@Override
	public void onInitialize() {
		final ModContainer modContainer = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();
		BloomConfig.init();
        BloomBlocks.init();
        BloomItems.init();
        BloomBiomes.init();
        BloomBiomePlacement.init();
        BloomBiomeModifications.init();
        BloomSounds.init();
        BloomCreativeInventory.init();
		BloomConditionSources.init();
		BloomBlockEntities.init();
		BloomLootTables.init();
		BloomVillagerTrades.init();
		ClimateCommand.init();

		if (BloomConfig.get.worldgen.pine_trees) {
			ResourceLoader.registerBuiltinPack(
				Bloom.id("pine_trees"), modContainer,
				Component.translatable("pack.bloom.pine_trees"),
				PackActivationType.ALWAYS_ENABLED
			);
		}
		if (BloomConfig.get.worldgen.ore_variants) {
			ResourceLoader.registerBuiltinPack(
				Bloom.id("ore_variants"), modContainer,
				Component.translatable("pack.bloom.ore_variants"),
				PackActivationType.ALWAYS_ENABLED
			);
		}
		if (FabricLoader.getInstance().isModLoaded("farmersdelight")) {
			if (BloomConfig.get.farming.wild_crops) {
				ResourceLoader.registerBuiltinPack(
					Bloom.id("wild_crops"), modContainer,
					Component.translatable("pack.bloom.wild_crops"),
					PackActivationType.ALWAYS_ENABLED
				);
				if (BloomConfig.get.farming.cotton) {
					BiomeModifications.create(Bloom.id("has_wild_cotton")).add(
						ModificationPhase.ADDITIONS,
						BiomeSelectors.tag(BloomBiomeTags.HAS_WILD_COTTON),
						(selectionContext, modificationContext) -> {
							BiomeHelper.addVegetation(modificationContext, BloomFeatures.PATCH_WILD_COTTON);
						}
					);
					ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
						entries.addBefore(Items.WHEAT_SEEDS, BloomBlocks.WILD_COTTON);
					});
				}
			}
		}
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> resourceKey, String path) {
		return ResourceKey.create(resourceKey, id(path));
	}
}
