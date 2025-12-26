package net.legacy.bloom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

@Environment(EnvType.CLIENT)
public final class BloomBlockRenderLayers {

	public static void init() {
		BlockRenderLayerMap.putBlock(BloomBlocks.HELLEBORE, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_HELLEBORE, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.BROMELIAD, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_BROMELIAD, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.PINK_ORCHID, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_PINK_ORCHID, ChunkSectionLayer.CUTOUT);

		BlockRenderLayerMap.putBlock(BloomBlocks.BELLFLOWER, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.HYDRANGEA, ChunkSectionLayer.CUTOUT);
	}
}