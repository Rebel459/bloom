package net.rebel459.bloom.client;

import net.rebel459.bloom.util.WoodsetRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.model.object.boat.RaftModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.minecraft.resources.Identifier;
import net.rebel459.unified.platform.client.UnifiedClientHelpers;
import java.util.Objects;

public class BloomEntityRenderLayers {

	public static void init() {
		WoodsetRegistry.WOODSETS.forEach(BloomEntityRenderLayers::registerBoatModels);
	}

	public static void registerBoatModels(WoodsetRegistry woodset){
		if (!woodset.getWoodsetSettings().hasBoats()){
			return;
		}
		Identifier layerName = woodset.getNameID().withPrefix("boat/");
		Identifier chestLayerName = woodset.getNameID().withPrefix("chest_boat/");

		final ModelLayerLocation BOAT_MODEL_LAYER = new ModelLayerLocation(layerName, "main");
		final ModelLayerLocation CHEST_BOAT_MODEL_LAYER = new ModelLayerLocation(chestLayerName, "main");

		final boolean raft = Objects.equals(woodset.getWoodsetSettings().getBoatType(), WoodsetRegistry.Settings.BoatType.RAFT);

		UnifiedClientHelpers.ENTITY_RENDERERS.addLayerDefinition(BOAT_MODEL_LAYER, raft ? RaftModel::createRaftModel : BoatModel::createBoatModel);
		UnifiedClientHelpers.ENTITY_RENDERERS.addEntityRenderer(woodset.getBoat()::get, ctx -> raft ? new RaftRenderer(ctx, BOAT_MODEL_LAYER) : new BoatRenderer(ctx, BOAT_MODEL_LAYER));

		UnifiedClientHelpers.ENTITY_RENDERERS.addLayerDefinition(CHEST_BOAT_MODEL_LAYER, raft ? RaftModel::createChestRaftModel : BoatModel::createChestBoatModel);
		UnifiedClientHelpers.ENTITY_RENDERERS.addEntityRenderer(woodset.getChestBoat()::get, ctx -> raft ? new RaftRenderer(ctx, CHEST_BOAT_MODEL_LAYER) : new BoatRenderer(ctx, CHEST_BOAT_MODEL_LAYER));
	}
}
