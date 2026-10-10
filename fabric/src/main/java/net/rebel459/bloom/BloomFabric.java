package net.rebel459.bloom;

import net.fabricmc.api.ModInitializer;
import net.rebel459.bloom.datagen.BloomBiomeTagProvider;
import net.rebel459.bloom.datagen.BloomBlockTagProvider;
import net.rebel459.bloom.datagen.BloomItemTagProvider;
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
