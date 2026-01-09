package net.legacy.bloom.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.legacy.bloom.registry.BloomBlockEntities;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.model.object.boat.RaftModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.minecraft.resources.Identifier;
import java.util.Objects;

public class BloomEntityRenderLayers {

	public static void init() {
		registerBoatModels(BloomBlocks.JACARANDA);
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

		EntityModelLayerRegistry.registerModelLayer(BOAT_MODEL_LAYER, raft ? RaftModel::createRaftModel : BoatModel::createBoatModel);
		EntityRendererRegistry.register(woodset.getBoat(), ctx -> raft ? new RaftRenderer(ctx, BOAT_MODEL_LAYER) : new BoatRenderer(ctx, BOAT_MODEL_LAYER));

		EntityModelLayerRegistry.registerModelLayer(CHEST_BOAT_MODEL_LAYER, raft ? RaftModel::createChestRaftModel : BoatModel::createChestBoatModel);
		EntityRendererRegistry.register(woodset.getChestBoat(), ctx -> raft ? new RaftRenderer(ctx, CHEST_BOAT_MODEL_LAYER) : new BoatRenderer(ctx, CHEST_BOAT_MODEL_LAYER));
	}
}
