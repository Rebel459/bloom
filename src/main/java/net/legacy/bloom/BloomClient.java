package net.legacy.bloom;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.legacy.bloom.client.BloomBlockRenderLayers;

@Environment(EnvType.CLIENT)
public final class BloomClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BloomBlockRenderLayers.init();
    }
}