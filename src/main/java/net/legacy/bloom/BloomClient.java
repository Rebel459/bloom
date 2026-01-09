package net.legacy.bloom;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.legacy.bloom.client.BloomBlockRenderLayers;
import net.legacy.bloom.client.BloomEntityRenderLayers;
import net.legacy.bloom.client.BloomModelLayers;
import net.legacy.bloom.client.BloomParticleResources;
import net.legacy.bloom.client.SleepingBagSheets;
import net.legacy.bloom.client.SleepingBagSpecialRenderer;
import net.minecraft.client.renderer.special.BedSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.client.resources.model.AtlasManager;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public final class BloomClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BloomBlockRenderLayers.init();
		BloomEntityRenderLayers.init();
        BloomParticleResources.init();
		BloomModelLayers.init();
		SpecialModelRenderers.ID_MAPPER.put(Bloom.id("sleeping_bag"), SleepingBagSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
