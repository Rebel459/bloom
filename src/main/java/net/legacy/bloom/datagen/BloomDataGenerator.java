package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.legacy.bloom.registry.BloomBiomes;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.NotNull;

public final class BloomDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(@NotNull FabricDataGenerator dataGenerator) {

		final FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(BloomModelProvider::new);
		pack.addProvider(BloomItemTagProvider::new);
		pack.addProvider(BloomBlockTagProvider::new);
		pack.addProvider(BloomBlockLootProvider::new);
		pack.addProvider(BloomRegistryProvider::new);
		pack.addProvider(BloomRecipeProvider::new);

	}

	public void buildRegistry(RegistrySetBuilder registrySetBuilder) {
		BloomRegistryProvider.buildRegistry(registrySetBuilder);
	}
}