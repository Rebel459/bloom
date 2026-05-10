package net.rebel459.bloom.mixin.client.snow.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.BlockQuadOutput;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.chunk.RenderSectionRegion;
import net.minecraft.client.renderer.chunk.SectionCompiler;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.rebel459.bloom.client.snow.SnowOverlayBlockStateModel;
import net.rebel459.bloom.client.snow.SnowOverlayBlockStateModelPart;
import net.rebel459.bloom.client.snow.SnowOverlayHelper;
import net.rebel459.bloom.config.BloomConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SectionCompiler.class)
public abstract class SectionCompilerSnowMixin {

	@WrapOperation(
		method = "compile(Lnet/minecraft/core/SectionPos;Lnet/minecraft/client/renderer/chunk/RenderSectionRegion;Lcom/mojang/blaze3d/vertex/VertexSorting;Lnet/minecraft/client/renderer/SectionBufferBuilderPack;Ljava/util/List;)Lnet/minecraft/client/renderer/chunk/SectionCompiler$Results;",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/renderer/block/ModelBlockRenderer;tesselateBlock(Lnet/minecraft/client/renderer/block/BlockQuadOutput;FFFLnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;J)V"
		)
	)
	private void bloom$tessellateSnowOverlay(
		ModelBlockRenderer instance, BlockQuadOutput output, float x, float y, float z, BlockAndTintGetter level, BlockPos pos, BlockState blockState, BlockStateModel model, long seed, Operation<Void> original, @Local RenderSectionRegion region) {
		if (!SnowOverlayHelper.isOverlaySource(blockState)) {
			original.call(instance, output, x, y, z, level, pos, blockState, model, seed);
			return;
		}

		BlockState belowState = region.getBlockState(pos.below());
		BloomConfig.SnowType type = BloomConfig.get().misc.snowier_snow;

		if (!SnowOverlayHelper.isValidState(belowState) || !SnowOverlayBlockStateModelPart.wouldRender(region, pos, blockState) || (type == BloomConfig.SnowType.LEAVES && !belowState.is(BlockTags.LEAVES))) {
			original.call(instance, output, x, y, z, level, pos, blockState, model, seed);
			return;
		}

		if (blockState.is(Blocks.SNOW_BLOCK)) {
			original.call(instance, output, x, y, z, level, pos, blockState, model, seed);

			BlockState fakeSnowLayerState = Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, 8);

			BlockAndTintGetter fakeLevel = SnowOverlayHelper.fakeBlockStateView(level, pos, fakeSnowLayerState);

			Vec3 visualOffset = blockState.hasOffsetFunction() ? blockState.getOffset(pos) : Vec3.ZERO;

			BlockStateModel overlayOnlyModel = new SnowOverlayBlockStateModel(
				model,
				region,
				pos,
				fakeSnowLayerState,
				false,
				visualOffset
			);

			original.call(instance, output, x, y, z, fakeLevel, pos, fakeSnowLayerState, overlayOnlyModel, seed);
			return;
		}

		Vec3 visualOffset = blockState.hasOffsetFunction() ? blockState.getOffset(pos) : Vec3.ZERO;

		original.call(instance, output, x, y, z, level, pos, blockState, new SnowOverlayBlockStateModel(model, region, pos, blockState, true, visualOffset), seed);

	}
}
