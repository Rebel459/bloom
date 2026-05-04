package net.rebel459.bloom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;

public class BloomFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Bloom.initRegistries();
        Bloom.init();
    }
}
