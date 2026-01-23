package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.util.StoneOresRegistry;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;

public class BloomLanguageProvider extends FabricLanguageProvider {
	protected BloomLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	private TranslationBuilder translation;

	@Override
	public void generateTranslations(HolderLookup.Provider lookup, TranslationBuilder translation) {
		this.translation = translation;

		for (StoneOresRegistry registry : StoneOresRegistry.ALL_REGISTRIES) {
			generateOreSet(translation, registry);
		}
		for (Block blocks : BloomBlocks.TRANSLATABLE_BLOCKS) {
			autoName(translation, blocks);
		}
		for (Item items : BloomItems.TRANSLATABLE_ITEMS) {
			autoName(translation, items);
		}

		this.music("overworld.lock_and_key", "Zhen - Lock and Key");

		translation.add("pack.bloom.pine_trees", "Pine Trees");
		translation.add("pack.bloom.ore_variants", "Ore Variants");
		translation.add("pack.bloom.wild_crops", "Wild Crops");

		translation.add("text.autoconfig.bloom.title", "Bloom");

		this.config("biomes", "Biomes");
		this.config("biomes.golden_forest", "Golden Forest");
		this.config("biomes.fen", "Fen");
		this.config("biomes.pine_taiga", "Pine Taiga");
		this.config("biomes.snowy_pine_taiga", "Snowy Pine Taiga");
		this.config("biomes.windswept_jungle", "Windswept Jungle");
		this.config("biomes.arid_shore", "Arid Shore");
		this.config("biomes.warm_river", "Warm River");
		this.config("biomes.cold_beach", "Cold Beach");
		this.config("biomes.cold_river", "Cold River");
		this.config("biomes.lukewarm_beach", "Lukewarm Beach");
		this.config("biomes.lukewarm_river", "Lukewarm River");
		this.config("biomes.snowy_shore", "Snowy Shore");
		this.config("biomes.tropical_beach", "Tropical Beach");
		this.config("biomes.tropical_river", "Tropical River");

		this.config("worldgen", "Worldgen");
		this.config("worldgen.pine_trees", "Pine Trees", "Whether new Pine Trees should be used");
		this.config("worldgen.stony_cliffs", "Stony Cliffs", "Whether certain biomes should have cliffs made of stone closer to the surface");
		this.config("worldgen.ore_variants", "Ore Variants", "Whether ore variants for all base stone variants should be generated");
		this.config("worldgen.stone_variant_depth", "Stone Variant Depth", "Whether certain regions should use Andesite, Diorite or Granite in place of Stone as the base underground block");
		this.config("worldgen.sandstone_depth", "Sandstone Depth", "Whether Sandstone and Red Sandstone should extend far deeper below Deserts and Badlands respectively");
		this.config("worldgen.dolerite_depth", "Dolerite Depth", "Whether Dolerite should replace Stone in frozen regions");

		this.config("farming", "Farming");
		this.config("farming.hoe_replanting", "Hoe Replanting", "Whether right-clicking crops with a Hoe should both harvest and replant the crop");
		this.config("farming.cotton", "Cotton", "Whether the new cotton crop and its related items should be obtainable in survival");
		this.config("farming.tradable_yarn", "Tradable Yarn", "Whether Shepherds should have a chance to purchase Yarn");
		this.config("farming.wild_crops", "Wild Crops", "Whether wild variants of Bloom crops should be added when Farmer's Delight is installed");
	}

	private void generateOreSet(TranslationBuilder translationBuilder, StoneOresRegistry registry){
		registry.getOresMap().values().forEach(block -> {
			autoName(translationBuilder, block);
		});
	}

	private void autoName(TranslationBuilder translationBuilder, Block block) {
		translationBuilder.add(block, autoNameInner(BuiltInRegistries.BLOCK.getKey(block).getPath()));
	}
	private void autoName(TranslationBuilder translationBuilder, ItemLike item) {
		translationBuilder.add(item.asItem(), autoNameInner(BuiltInRegistries.ITEM.getKey(item.asItem()).getPath()));
	}
	private void autoName(TranslationBuilder translationBuilder, EntityType<?> entityType) {
		translationBuilder.add(entityType, autoNameInner(BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath()));
	}

	private String autoNameInner(String id) {
		StringBuilder name = new StringBuilder();
		String[] split = id.split("_");
		for(String str : split) {
			if(!name.isEmpty()) {
				name.append(" ");
			}
			name.append(capitalize(str));
		}
		return name.toString();
	}

	public static String capitalize(String inputString) {

		// get the first character of the inputString
		char firstLetter = inputString.charAt(0);

		// return the output string by updating
		//the first char of the input string
		return inputString.replaceFirst(String.valueOf(firstLetter), String.valueOf(firstLetter).toUpperCase());
	}

	public void config(String path, String name) {
		this.translation.add("text.autoconfig." + Bloom.MOD_ID + ".option." + path, name);
	}
	public void config(String path, String name, String tooltip) {
		config(path, name);
		config(path + ".@Tooltip", tooltip);
	}

	public void music(String path, String name) {
		this.translation.add(Bloom.MOD_ID + ".music." + path, name);
	}
}
