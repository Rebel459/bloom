package net.rebel459.bloom;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.bloom.registry.BloomBiomes;
import net.rebel459.bloom.registry.BloomBlockStateProperties;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomConditionSources;
import net.rebel459.bloom.registry.BloomCreativeInventory;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.registry.BloomLootTables;
import net.rebel459.bloom.registry.BloomParticleTypes;
import net.rebel459.bloom.sound.BloomSounds;
import net.rebel459.bloom.tag.BloomBiomeTags;
import net.rebel459.bloom.util.BiomeHelper;
import net.rebel459.bloom.util.ClimateCommand;
import net.rebel459.bloom.worldgen.BloomBiomeModifications;
import net.rebel459.bloom.worldgen.BloomBiomePlacement;
import net.rebel459.bloom.worldgen.BloomFeatures;
import net.rebel459.bloom.worldgen.BloomRegions;
import net.rebel459.bloom.worldgen.BloomSurfaceRules;
import net.rebel459.unified.platform.UnifiedHelpers;
import net.rebel459.unified.platform.UnifiedPlatform;
import net.rebel459.unified.util.CreativeModeTabs;
import net.rebel459.unified.util.PackType;

public class Bloom {
	public static final String MOD_ID = "bloom";

	public static void initRegistries() {
		BloomBlockStateProperties.init();
		BloomBlocks.init();
		BloomItems.init();
		BloomBiomes.init();
		BloomRegions.init();
		BloomBiomePlacement.init();
		BloomBiomeModifications.init();
		BloomSounds.init();
		BloomConditionSources.init();
		BloomParticleTypes.init();
	}

	public static void init() {
		BloomSurfaceRules.init();
		BloomBlocks.registerBlockProperties();
        BloomCreativeInventory.init();
		BloomLootTables.init();
		BloomFeatures.init();
		ClimateCommand.init();

		if (BloomConfig.get().worldgen.pine_trees) {
			UnifiedHelpers.PACKS.add(Bloom.id("pine_trees"), PackType.REQUIRED_DATA);
		}
		if (BloomConfig.get().worldgen.taller_spruce_trees) {
			UnifiedHelpers.PACKS.add(Bloom.id("taller_spruce_trees"), PackType.REQUIRED_DATA);
		}
		if (BloomConfig.get().worldgen.ore_variants) {
			UnifiedHelpers.PACKS.add(Bloom.id("ore_variants"), PackType.REQUIRED_DATA);
			if (UnifiedPlatform.isModLoaded("legacies_and_legends")) {
				UnifiedHelpers.PACKS.add(Bloom.id("ore_variants_sapphire"), PackType.REQUIRED_DATA);
			}
		}
		if (BloomConfig.get().misc.stone_variant_crafting) {
			UnifiedHelpers.PACKS.add(Bloom.id("stone_variant_crafting"), PackType.REQUIRED_DATA);
		}
		if (BloomConfig.get().farming.tradable_yarn) {
			UnifiedHelpers.PACKS.add(Bloom.id("tradable_yarn"), PackType.REQUIRED_DATA);
		}
		if (UnifiedPlatform.isModLoaded("farmersdelight")) {
			if (BloomConfig.get().farming.wild_crops) {
				UnifiedHelpers.PACKS.add(Bloom.id("wild_crops"), PackType.REQUIRED_DATA);
				if (BloomConfig.get().farming.cotton) {
					UnifiedHelpers.BIOME_MODIFICATIONS.register(BloomBiomeTags.HAS_WILD_COTTON, context -> {
						BiomeHelper.addVegetation(context, BloomFeatures.PATCH_WILD_COTTON);
					});
					UnifiedHelpers.CREATIVE_ENTRIES.insertBefore(CreativeModeTabs.NATURAL_BLOCKS, Items.WHEAT_SEEDS, BloomBlocks.WILD_COTTON);
				}
			}
		}
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
