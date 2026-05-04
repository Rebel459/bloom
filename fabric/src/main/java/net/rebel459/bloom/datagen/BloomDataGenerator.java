package net.rebel459.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.rebel459.bloom.Bloom;
import net.minecraft.core.RegistrySetBuilder;

public final class BloomDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		final FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(BloomModelProvider::new);
		pack.addProvider(BloomItemTagProvider::new);
		pack.addProvider(BloomBiomeTagProvider::new);
		pack.addProvider(BloomBlockTagProvider::new);
		pack.addProvider(BloomBlockLootProvider::new);
		pack.addProvider(BloomRegistryProvider::new);
		pack.addProvider(BloomRecipeProvider::new);
		pack.addProvider(BloomLanguageProvider::new);

	}

	@Override
	public void buildRegistry(RegistrySetBuilder builder) {
		BloomRegistryProvider.buildRegistry(builder);
	}

	@Override
	public String getEffectiveModId() {
		return Bloom.MOD_ID;
	}
}
