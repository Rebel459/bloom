package net.rebel459.bloom;

import net.fabricmc.api.ClientModInitializer;

public class BloomFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BloomClient.initRegistries();
		BloomClient.init();
    }
}
