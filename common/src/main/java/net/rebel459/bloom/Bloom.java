package net.rebel459.bloom;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.bloom.registry.BloomBiomes;
import net.rebel459.bloom.registry.BloomBlockCodecs;
import net.rebel459.bloom.registry.BloomBlockStateProperties;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomConditionSources;
import net.rebel459.bloom.registry.BloomCreativeInventory;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.registry.BloomLootTables;
import net.rebel459.bloom.registry.BloomParticleTypes;
import net.rebel459.bloom.sound.BloomSounds;
import net.rebel459.bloom.tag.BloomBiomeTags;
import net.rebel459.bloom.util.BloomData;
import net.rebel459.bloom.util.ClimateCommand;
import net.rebel459.bloom.worldgen.BloomBiomeModifications;
import net.rebel459.bloom.worldgen.BloomBiomePlacement;
import net.rebel459.bloom.worldgen.BloomFeatures;
import net.rebel459.bloom.worldgen.BloomRegions;
import net.rebel459.bloom.worldgen.sapling.BloomTreeGrowers;
import net.rebel459.bloom.worldgen.BloomSurfaceRules;
import net.rebel459.unified.api.core.UnifiedData;
import net.rebel459.unified.api.core.UnifiedHelpers;
import net.rebel459.unified.api.core.UnifiedPlatform;

public class Bloom {

	public static final String MOD_ID = "bloom";

	public static final UnifiedData DATA = UnifiedData.create(Bloom.MOD_ID).autoName().build();

	public static void initRegistries() {
		BloomData.init();
		BloomBlockStateProperties.init();
		BloomBlockCodecs.init();
		BloomTreeGrowers.init();
		BloomBlocks.init();
		BloomItems.init();
		BloomBiomes.init();
		BloomRegions.init();
		BloomBiomePlacement.init();
		BloomBiomeModifications.init();
		BloomSounds.init();
		BloomConditionSources.init();
		BloomParticleTypes.init();

		BloomData.FARMERS_DELIGHT_AND_WILD_COTTON.helpers().biomeModifiers().create("has_wild_cotton", provider -> provider.getOrThrow(BloomBiomeTags.HAS_WILD_COTTON)).addFeature(BloomFeatures.PATCH_WILD_COTTON, GenerationStep.Decoration.VEGETAL_DECORATION);
	}

	public static void init() {
		BloomSurfaceRules.init();
		BloomBlocks.registerBlockProperties();
        BloomCreativeInventory.init();
		BloomLootTables.init();
		BloomFeatures.init();
		ClimateCommand.init();

		if (BloomConfig.get().worldgen.pine_trees) {
			UnifiedHelpers.DATA_PACKS.addRequired(Bloom.id("pine_trees"));
		}
		if (BloomConfig.get().worldgen.taiga_tweaks) {
			UnifiedHelpers.DATA_PACKS.addRequired(Bloom.id("taiga_tweaks"));
		}
		if (BloomConfig.get().worldgen.ore_variants) {
			UnifiedHelpers.DATA_PACKS.addRequired(Bloom.id("ore_variants"));
			if (UnifiedPlatform.isModLoaded("legacies_and_legends")) {
				UnifiedHelpers.DATA_PACKS.addRequired(Bloom.id("ore_variants_sapphire"));
			}
		}
		if (BloomConfig.get().misc.stone_variant_crafting) {
			UnifiedHelpers.DATA_PACKS.addRequired(Bloom.id("stone_variant_crafting"));
		}
		if (BloomConfig.get().farming.tradable_yarn) {
			UnifiedHelpers.DATA_PACKS.addRequired(Bloom.id("tradable_yarn"));
		}
		if (UnifiedPlatform.isModLoaded("farmersdelight") && BloomConfig.get().farming.wild_crops) {
			UnifiedHelpers.DATA_PACKS.addRequired(Bloom.id("wild_crops"));
		}
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
