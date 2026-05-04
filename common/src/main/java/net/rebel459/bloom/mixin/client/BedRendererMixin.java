package net.rebel459.bloom.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.blockentity.state.BedRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.util.Unit;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.rebel459.bloom.client.BloomModelLayers;
import net.rebel459.bloom.client.SleepingBagRenderHelper;
import net.rebel459.bloom.client.SleepingBagRenderStateAccess;
import net.rebel459.bloom.tag.BloomBlockTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BedRenderer.class)
public class BedRendererMixin {
	@Shadow
	@Final
	private SpriteGetter sprites;

	@Unique
	private Model.Simple bloom$sleepingBagHeadModel;
	@Unique
	private Model.Simple bloom$sleepingBagFootModel;

	@Inject(
		method = "<init>(Lnet/minecraft/client/resources/model/sprite/SpriteGetter;Lnet/minecraft/client/model/geom/EntityModelSet;)V",
		at = @At("TAIL")
	)
	private void bloom$init(SpriteGetter sprites, EntityModelSet modelSet, CallbackInfo info) {
		this.bloom$sleepingBagHeadModel = new Model.Simple(modelSet.bakeLayer(BloomModelLayers.SLEEPING_BAG_HEAD), RenderTypes::entitySolid);
		this.bloom$sleepingBagFootModel = new Model.Simple(modelSet.bakeLayer(BloomModelLayers.SLEEPING_BAG_FOOT), RenderTypes::entitySolid);
	}

	@Inject(
		method = "extractRenderState(Lnet/minecraft/world/level/block/entity/BedBlockEntity;Lnet/minecraft/client/renderer/blockentity/state/BedRenderState;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V",
		at = @At("HEAD")
	)
	private void bloom$markAsSleepingBag(
		BedBlockEntity blockEntity,
		BedRenderState renderState,
		float partialTicks,
		net.minecraft.world.phys.Vec3 cameraPosition,
		ModelFeatureRenderer.CrumblingOverlay breakProgress,
		CallbackInfo info
	) {
		((SleepingBagRenderStateAccess) renderState).bloom$setSleepingBag(blockEntity.getBlockState().is(BloomBlockTags.SLEEPING_BAGS));
	}

	@WrapOperation(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/BedRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/blockentity/BedRenderer;submitPiece(Lnet/minecraft/world/level/block/state/properties/BedPart;Lnet/minecraft/client/resources/model/sprite/SpriteId;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;I)V"
		)
	)
	private void bloom$submitSleepingBagPiece(
		BedRenderer instance,
		BedPart part,
		SpriteId sprite,
		PoseStack poseStack,
		SubmitNodeCollector submitNodeCollector,
		int lightCoords,
		int overlayCoords,
		ModelFeatureRenderer.CrumblingOverlay breakProgress,
		int outlineColor,
		Operation<Void> original,
		@Local(argsOnly = true) BedRenderState renderState
	) {
		if (!((SleepingBagRenderStateAccess) renderState).bloom$isSleepingBag()) {
			original.call(instance, part, sprite, poseStack, submitNodeCollector, lightCoords, overlayCoords, breakProgress, outlineColor);
			return;
		}

		Model.Simple model = part == BedPart.HEAD ? this.bloom$sleepingBagHeadModel : this.bloom$sleepingBagFootModel;
		SpriteId sleepingBagSprite = SleepingBagRenderHelper.getSleepingBagMaterial(renderState.color);
		submitNodeCollector.submitModel(
			model,
			Unit.INSTANCE,
			poseStack,
			lightCoords,
			overlayCoords,
			-1,
			sleepingBagSprite,
			this.sprites,
			outlineColor,
			breakProgress
		);
	}
}
