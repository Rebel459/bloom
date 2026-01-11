package net.legacy.bloom.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.legacy.bloom.client.BloomModelLayers;
import net.legacy.bloom.client.SleepingBagRenderHelper;
import net.legacy.bloom.tag.BloomBlockTags;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.blockentity.state.BedRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(BedRenderer.class)
public class BedRendererMixin {
	@Unique
	private Model.Simple bloom$sleepingBagHeadModel;
	@Unique
	private Model.Simple bloom$sleepingBagFootModel;

	@Inject(
		method = "<init>(Lnet/minecraft/client/resources/model/MaterialSet;Lnet/minecraft/client/model/geom/EntityModelSet;)V",
		at = @At("TAIL")
	)
	public void bloom$init(MaterialSet materialSet, EntityModelSet modelSet, CallbackInfo info) {
		this.bloom$sleepingBagHeadModel = new Model.Simple(modelSet.bakeLayer(BloomModelLayers.SLEEPING_BAG_HEAD), RenderTypes::entitySolid);
		this.bloom$sleepingBagFootModel = new Model.Simple(modelSet.bakeLayer(BloomModelLayers.SLEEPING_BAG_FOOT), RenderTypes::entitySolid);
	}

	@Inject(
		method = "extractRenderState(Lnet/minecraft/world/level/block/entity/BedBlockEntity;Lnet/minecraft/client/renderer/blockentity/state/BedRenderState;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V",
		at = @At("HEAD")
	)
	public void bloom$markAsSleepingBag(
		CallbackInfo info,
		@Local(argsOnly = true) BedBlockEntity blockEntity,
		@Local(argsOnly = true) BedRenderState renderState
	) {
		renderState.setData(SleepingBagRenderHelper.IS_SLEEPING_BAG, blockEntity.getBlockState().is(BloomBlockTags.SLEEPING_BAGS));
	}

	@WrapOperation(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/BedRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/Sheets;getBedMaterial(Lnet/minecraft/world/item/DyeColor;)Lnet/minecraft/client/resources/model/Material;"
		)
	)
	public Material bloom$useSleepingBagMaterial(
		DyeColor color, Operation<Material> original,
		@Local(argsOnly = true) BedRenderState renderState
	) {
		// Has to be done this way for null safety...
		if (Boolean.TRUE.equals(renderState.getData(SleepingBagRenderHelper.IS_SLEEPING_BAG))) return SleepingBagRenderHelper.getSleepingBagMaterial(color);
		return original.call(color);
	}

	@ModifyExpressionValue(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/BedRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/renderer/blockentity/BedRenderer;headModel:Lnet/minecraft/client/model/Model$Simple;",
			opcode = Opcodes.GETFIELD
		)
	)
	public Model.Simple bloom$useSleepingBagHeadModel(
		Model.Simple original,
		@Local(argsOnly = true) BedRenderState renderState
	) {
		// Has to be done this way for null safety...
		if (Boolean.TRUE.equals(renderState.getData(SleepingBagRenderHelper.IS_SLEEPING_BAG))) return this.bloom$sleepingBagHeadModel;
		return original;
	}

	@ModifyExpressionValue(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/BedRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/renderer/blockentity/BedRenderer;footModel:Lnet/minecraft/client/model/Model$Simple;",
			opcode = Opcodes.GETFIELD
		)
	)
	public Model.Simple bloom$useSleepingBagFootModel(
		Model.Simple original,
		@Local(argsOnly = true) BedRenderState renderState
	) {
		// Has to be done this way for null safety...
		if (Boolean.TRUE.equals(renderState.getData(SleepingBagRenderHelper.IS_SLEEPING_BAG))) return this.bloom$sleepingBagFootModel;
		return original;
	}

}
