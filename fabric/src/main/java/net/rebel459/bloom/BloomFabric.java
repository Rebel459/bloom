package net.rebel459.bloom;

import net.fabricmc.api.ModInitializer;
import net.rebel459.bloom.datagen.BloomBiomeTagProvider;
import net.rebel459.bloom.datagen.BloomBlockTagProvider;
import net.rebel459.bloom.datagen.BloomItemTagProvider;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.unified.api.builder.WoodSet;
import net.rebel459.unified.api.core.SuppliedBlock;
import net.rebel459.unified.api.core.SuppliedItem;
import net.rebel459.unified.api.core.UnifiedPlatform;
import net.rebel459.unified.fabric.FabricUnifiedInitializer;

public class BloomFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Bloom.initRegistries();
		FabricUnifiedInitializer.register(this::onInitializeCommon);
    }

	private void onInitializeCommon() {
		Bloom.init();
		BloomBlockTagProvider.init();
		BloomItemTagProvider.init();
		BloomBiomeTagProvider.init();
	}
}
