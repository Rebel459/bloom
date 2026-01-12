package net.legacy.bloom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.util.WoodsetRegistry;
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
		BlockRenderLayerMap.putBlock(BloomBlocks.CALLA_LILY, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_CALLA_LILY, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.DIANTHUS, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_DIANTHUS, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.GOLDENROD, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_GOLDENROD, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.ORANGE_DAISY, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_ORANGE_DAISY, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.SCILLA, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_SCILLA, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.HYACINTH, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_HYACINTH, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.QUEENCUP, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_QUEENCUP, ChunkSectionLayer.CUTOUT);

		BlockRenderLayerMap.putBlock(BloomBlocks.BELLFLOWER, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BloomBlocks.HYDRANGEA, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BloomBlocks.REEDS, ChunkSectionLayer.CUTOUT);

		BlockRenderLayerMap.putBlock(BloomBlocks.SUCCULENT, ChunkSectionLayer.CUTOUT);

		BlockRenderLayerMap.putBlock(BloomBlocks.COTTON, ChunkSectionLayer.CUTOUT);

		WoodsetRegistry.WOODSETS.forEach(woodset -> {
			BlockRenderLayerMap.putBlock(woodset.getLeaves(), ChunkSectionLayer.CUTOUT);
		});

		BlockRenderLayerMap.putBlock(BloomBlocks.JACARANDA_SAPLING, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_JACARANDA_SAPLING, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.GOLDEN_BIRCH_SAPLING, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_GOLDEN_BIRCH_SAPLING, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.PINE_SAPLING, ChunkSectionLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(BloomBlocks.POTTED_PINE_SAPLING, ChunkSectionLayer.CUTOUT);
	}
}
