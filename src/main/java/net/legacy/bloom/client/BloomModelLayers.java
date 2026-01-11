package net.legacy.bloom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.legacy.bloom.Bloom;
import net.minecraft.client.model.geom.ModelLayerLocation;

@Environment(EnvType.CLIENT)
public final class BloomModelLayers {
	public static final ModelLayerLocation SLEEPING_BAG_HEAD = new ModelLayerLocation(Bloom.id("sleeping_bag"), "head");
	public static final ModelLayerLocation SLEEPING_BAG_FOOT = new ModelLayerLocation(Bloom.id("sleeping_bag"), "foot");

	public static void init() {
		EntityModelLayerRegistry.registerModelLayer(SLEEPING_BAG_FOOT, SleepingBagRenderHelper::createFootLayer);
		EntityModelLayerRegistry.registerModelLayer(SLEEPING_BAG_HEAD, SleepingBagRenderHelper::createHeadLayer);
	}
}
