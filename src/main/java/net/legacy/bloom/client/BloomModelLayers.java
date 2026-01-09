package net.legacy.bloom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.registry.BloomBlockEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public final class BloomModelLayers {
	public static final ModelLayerLocation SLEEPING_BAG_HEAD = new ModelLayerLocation(Bloom.id("sleeping_bag"), "head");
	public static final ModelLayerLocation SLEEPING_BAG_FOOT = new ModelLayerLocation(Bloom.id("sleeping_bag"), "foot");

	public static void init() {
		BlockEntityRenderers.register(BloomBlockEntities.SLEEPING_BAG, SleepingBagRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(SLEEPING_BAG_FOOT, SleepingBagRenderer::createFootLayer);
		EntityModelLayerRegistry.registerModelLayer(SLEEPING_BAG_HEAD, SleepingBagRenderer::createHeadLayer);
	}
}
