package net.rebel459.bloom;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.rebel459.unified.platform.NeoForgeUnifiedRegistries;

@Mod(Bloom.MOD_ID)
public class BloomNeoForge {

    public BloomNeoForge(IEventBus modEventBus) {
        NeoForgeUnifiedRegistries.registerBus(Bloom.MOD_ID, modEventBus);
        Bloom.initRegistries();
        modEventBus.addListener(BloomNeoForge::commonSetup);
    }

    private static void commonSetup(final FMLCommonSetupEvent event) {
        Bloom.init();
    }
}
