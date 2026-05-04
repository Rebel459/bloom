package net.rebel459.bloom;

import net.rebel459.bloom.client.BloomEntityRenderLayers;
import net.rebel459.bloom.client.BloomModelLayers;
import net.rebel459.bloom.client.BloomParticleResources;

public final class BloomClient {

	public static void initRegistries() {
		BloomEntityRenderLayers.init();
		BloomModelLayers.init();
	}

    public static void init() {
        BloomParticleResources.init();
    }
}
