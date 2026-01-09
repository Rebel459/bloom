package net.legacy.bloom.client;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.block.SleepingBagBlock;
import net.legacy.bloom.block.entity.SleepingBagBlockEntity;
import net.legacy.bloom.registry.BloomBlockEntities;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

public class SleepingBagRenderer implements BlockEntityRenderer<SleepingBagBlockEntity, SleepingBagRenderState> {

	private final MaterialSet materials;
	private final Model.Simple headModel;
	private final Model.Simple footModel;

	public SleepingBagRenderer(BlockEntityRendererProvider.Context context) {
		this(context.materials(), context.entityModelSet());
	}

	public SleepingBagRenderer(SpecialModelRenderer.BakingContext bakingContext) {
		this(bakingContext.materials(), bakingContext.entityModelSet());
	}

	public SleepingBagRenderer(MaterialSet materialSet, EntityModelSet entityModelSet) {
		this.materials = materialSet;
		this.headModel = new Model.Simple(entityModelSet.bakeLayer(BloomModelLayers.SLEEPING_BAG_HEAD), RenderTypes::entitySolid);
		this.footModel = new Model.Simple(entityModelSet.bakeLayer(BloomModelLayers.SLEEPING_BAG_FOOT), RenderTypes::entitySolid);
	}

	public static LayerDefinition createHeadLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 8.0F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	public static LayerDefinition createFootLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 17).addBox(0.0F, 0.0F, 8.0F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	public SleepingBagRenderState createRenderState() {
		return new SleepingBagRenderState();
	}

	public void extractRenderState(SleepingBagBlockEntity entity, SleepingBagRenderState renderState, float f, Vec3 vec3, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
		BlockEntityRenderer.super.extractRenderState(entity, renderState, f, vec3, crumblingOverlay);
		renderState.color = entity.getColor();
		renderState.facing = entity.getBlockState().getValue(SleepingBagBlock.FACING);
		renderState.isHead = entity.getBlockState().getValue(SleepingBagBlock.PART) == SleepingBagBlock.Part.HEAD;
		if (entity.getLevel() != null) {
			DoubleBlockCombiner.NeighborCombineResult<? extends SleepingBagBlockEntity> neighborCombineResult = DoubleBlockCombiner.combineWithNeigbour(
				BloomBlockEntities.SLEEPING_BAG,
				SleepingBagBlock::getBlockType,
				SleepingBagBlock::getConnectedDirection,
				ChestBlock.FACING,
				entity.getBlockState(),
				entity.getLevel(),
				entity.getBlockPos(),
				(levelAccessor, blockPos) -> false
			);
			renderState.lightCoords = neighborCombineResult.apply(new BrightnessCombiner<>()).get(renderState.lightCoords);
		}
	}

	public void submit(SleepingBagRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		Material material = SleepingBagSheets.getSleepingBagMaterial(renderState.color);
		this.submitPiece(
			poseStack,
			submitNodeCollector,
			renderState.isHead ? this.headModel : this.footModel,
			renderState.facing,
			material,
			renderState.lightCoords,
			OverlayTexture.NO_OVERLAY,
			false,
			renderState.breakProgress,
			0
		);
	}

	public void submitSpecial(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, Material material, int k) {
		this.submitPiece(poseStack, submitNodeCollector, this.headModel, Direction.SOUTH, material, i, j, false, null, k);
		this.submitPiece(poseStack, submitNodeCollector, this.footModel, Direction.SOUTH, material, i, j, true, null, k);
	}

	private void submitPiece(
		PoseStack poseStack,
		SubmitNodeCollector submitNodeCollector,
		Model.Simple simple,
		Direction direction,
		Material material,
		int i,
		int j,
		boolean bl,
		ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay,
		int k
	) {
		poseStack.pushPose();
		preparePose(poseStack, bl, direction);
		submitNodeCollector.submitModel(
			simple, Unit.INSTANCE, poseStack, material.renderType(RenderTypes::entitySolid), i, j, -1, this.materials.get(material), k, crumblingOverlay
		);
		poseStack.popPose();
	}

	private static void preparePose(PoseStack poseStack, boolean bl, Direction direction) {
		poseStack.translate(0.0F, 0.5625F, bl ? -1.0F : 0.0F);
		poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
		poseStack.translate(0.5F, 0.5F, 0.5F);
		poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()));
		poseStack.translate(-0.5F, -0.5F, -0.5F);
	}

	public void getExtents(Consumer<Vector3fc> consumer) {
		PoseStack poseStack = new PoseStack();
		preparePose(poseStack, false, Direction.SOUTH);
		this.headModel.root().getExtentsForGui(poseStack, consumer);
		poseStack.setIdentity();
		preparePose(poseStack, true, Direction.SOUTH);
		this.footModel.root().getExtentsForGui(poseStack, consumer);
	}
}
