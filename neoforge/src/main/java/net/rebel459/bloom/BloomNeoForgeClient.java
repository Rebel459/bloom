package net.rebel459.bloom;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(value = Bloom.MOD_ID, dist = Dist.CLIENT)
public class BloomNeoForgeClient {

    public BloomNeoForgeClient(IEventBus modEventBus) {
        BloomClient.initRegistries();
		modEventBus.addListener(BloomNeoForgeClient::commonSetup);
	}

	private static void commonSetup(final FMLCommonSetupEvent event) {
		BloomClient.init();
	}
}
