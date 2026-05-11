package net.rebel459.bloom.mixin.client.snow.sodium;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.tasks.ChunkBuilderMeshingTask;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.rebel459.bloom.client.snow.SnowOverlayBlockStateModel;
import net.rebel459.bloom.client.snow.SnowOverlayBlockStateModelPart;
import net.rebel459.bloom.client.snow.SnowOverlayHelper;
import net.rebel459.bloom.client.snow.SodiumSnowOverlayLightingContext;
import net.rebel459.bloom.config.BloomConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ChunkBuilderMeshingTask.class, remap = false)
public abstract class SodiumChunkBuilderMeshingTaskSnowMixin {
	@WrapOperation(
		method = "execute(Lnet/caffeinemc/mods/sodium/client/render/chunk/compile/ChunkBuildContext;Lnet/caffeinemc/mods/sodium/client/util/task/CancellationToken;)Lnet/caffeinemc/mods/sodium/client/render/chunk/compile/ChunkBuildOutput;",
		at = @At(
			value = "INVOKE",
			target = "Lnet/caffeinemc/mods/sodium/client/render/chunk/compile/pipeline/BlockRenderer;renderModel(Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)V"
		)
	)
	private void bloom$renderSnowOverlayWithSodium(
		BlockRenderer renderer,
		BlockStateModel model,
		BlockState blockState,
		BlockPos blockPos,
		BlockPos modelOffset,
		Operation<Void> original,
		@Local(name = "slice") LevelSlice slice
	) {
		if (!SnowOverlayHelper.isOverlaySource(blockState)) {
			original.call(renderer, model, blockState, blockPos, modelOffset);
			return;
		}

		BlockState belowState = slice.getBlockState(blockPos.below());
		BloomConfig.SnowType type = BloomConfig.get().misc.snowier_snow;

		if (!SnowOverlayHelper.isValidState(belowState) || (type == BloomConfig.SnowType.LEAVES && !belowState.is(BlockTags.LEAVES))) {
			original.call(renderer, model, blockState, blockPos, modelOffset);
			return;
		}

		if (!SnowOverlayBlockStateModelPart.wouldRender(slice, blockPos, blockState)) {
			original.call(renderer, model, blockState, blockPos, modelOffset);
			return;
		}

		if (blockState.is(Blocks.SNOW_BLOCK)) {
			original.call(renderer, model, blockState, blockPos, modelOffset);

			BlockState fakeSnowLayerState = Blocks.SNOW.defaultBlockState()
				.setValue(SnowLayerBlock.LAYERS, 8);

			Vec3 visualOffset = blockState.hasOffsetFunction()
				? blockState.getOffset(blockPos)
				: Vec3.ZERO;

			BlockStateModel overlayOnlyModel = new SnowOverlayBlockStateModel(
				model,
				slice,
				blockPos,
				fakeSnowLayerState,
				false,
				visualOffset
			);

			SodiumSnowOverlayLightingContext.push(blockPos, fakeSnowLayerState);

			try {
				original.call(renderer, overlayOnlyModel, fakeSnowLayerState, blockPos, modelOffset);
			} finally {
				SodiumSnowOverlayLightingContext.pop();
			}

			return;
		}

		int snowloggedLayers = SnowOverlayHelper.getSnowloggedLayers(blockState);
		if (snowloggedLayers > 0) {
			original.call(renderer, model, blockState, blockPos, modelOffset);

			BlockState fakeSnowLayerState = Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, snowloggedLayers);

			Vec3 visualOffset = fakeSnowLayerState.hasOffsetFunction() ? blockState.getOffset(blockPos) : Vec3.ZERO;

			BlockStateModel overlayOnlyModel = new SnowOverlayBlockStateModel(
				model,
				slice,
				blockPos,
				fakeSnowLayerState,
				false,
				visualOffset
			);

			SodiumSnowOverlayLightingContext.push(blockPos, fakeSnowLayerState);

			try {
				original.call(renderer, overlayOnlyModel, fakeSnowLayerState, blockPos, modelOffset);
			} finally {
				SodiumSnowOverlayLightingContext.pop();
			}

			return;
		}

		Vec3 visualOffset = blockState.hasOffsetFunction() ? blockState.getOffset(blockPos) : Vec3.ZERO;

		original.call(
			renderer,
			new SnowOverlayBlockStateModel(model, slice, blockPos, blockState, true, visualOffset),
			blockState,
			blockPos,
			modelOffset
		);
	}
}
