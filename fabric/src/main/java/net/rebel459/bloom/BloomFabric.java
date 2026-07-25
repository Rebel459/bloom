package net.rebel459.bloom;

import net.fabricmc.api.ModInitializer;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.unified.platform.UnifiedPlatform;
import net.rebel459.unified.util.builder.WoodSet;
import net.rebel459.unified.util.registry.SuppliedBlock;
import net.rebel459.unified.util.registry.SuppliedItem;

public class BloomFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Bloom.initRegistries();
		if (UnifiedPlatform.isDevelopmentEnvironment()) {
			for (WoodSet woodSet : BloomBlocks.getWoodSets()) {
				for (SuppliedBlock block : woodSet.getRegisteredBlocks()) {
					BloomBlocks.checkDatagen(block);
				}
				for (SuppliedItem item : woodSet.getRegisteredItems()) {
					BloomItems.checkDatagen(item);
				}
			}
		}
        Bloom.init();
    }
}
