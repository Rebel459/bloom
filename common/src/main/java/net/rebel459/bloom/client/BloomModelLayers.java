package net.rebel459.bloom.client;

import net.rebel459.bloom.Bloom;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.rebel459.unified.platform.client.UnifiedClientHelpers;

public final class BloomModelLayers {
	public static final ModelLayerLocation SLEEPING_BAG_HEAD = new ModelLayerLocation(Bloom.id("sleeping_bag"), "head");
	public static final ModelLayerLocation SLEEPING_BAG_FOOT = new ModelLayerLocation(Bloom.id("sleeping_bag"), "foot");

	public static void init() {
		UnifiedClientHelpers.ENTITY_RENDERERS.addLayerDefinition(SLEEPING_BAG_FOOT, SleepingBagRenderHelper::createFootLayer);
		UnifiedClientHelpers.ENTITY_RENDERERS.addLayerDefinition(SLEEPING_BAG_HEAD, SleepingBagRenderHelper::createHeadLayer);
	}
}
