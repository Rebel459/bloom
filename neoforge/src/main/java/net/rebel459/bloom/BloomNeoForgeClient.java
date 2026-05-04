package net.rebel459.bloom;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.rebel459.bloom.config.BloomConfig;

@Mod(value = Bloom.MOD_ID, dist = Dist.CLIENT)
public class BloomNeoForgeClient {

    public BloomNeoForgeClient(IEventBus modEventBus) {
        BloomClient.initRegistries();
		ModLoadingContext.get().registerExtensionPoint(
			IConfigScreenFactory.class,
			() -> (modContainer, parent) ->
				AutoConfigClient.getConfigScreen(BloomConfig.class, parent).get()
		);
		modEventBus.addListener(BloomNeoForgeClient::commonSetup);
	}

	private static void commonSetup(final FMLCommonSetupEvent event) {
		BloomClient.init();
	}
}
