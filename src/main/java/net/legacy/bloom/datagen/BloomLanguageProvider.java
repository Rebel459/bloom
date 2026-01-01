package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.util.StoneOresRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;

public class BloomLanguageProvider extends FabricLanguageProvider {
	protected BloomLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
		for (StoneOresRegistry registry : StoneOresRegistry.ALL_REGISTRIES) {
			generateOreSet(translationBuilder, registry);
		}
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
}
