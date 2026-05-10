package net.rebel459.bloom.client.snow;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.rebel459.bloom.tag.BloomBlockTags;
import org.jspecify.annotations.Nullable;

public class SnowOverlayHelper {

	public static boolean isValidState(BlockState state) {
		return !(state.is(BlockTags.SNOW) || state.isAir() || state.getBlock() instanceof LiquidBlock || state.is(BloomBlockTags.NO_SNOW_OVERLAY));
	}

	public static boolean isOverlaySource(BlockState state) {
		return state.is(Blocks.SNOW) || state.is(Blocks.SNOW_BLOCK);
	}

	public static BlockAndTintGetter fakeBlockStateView(BlockAndTintGetter original, BlockPos fakePos, BlockState fakeState) {
		return new BlockAndTintGetter() {
			@Override
			public @Nullable BlockEntity getBlockEntity(BlockPos blockPos) {
				return original.getBlockEntity(blockPos);
			}

			@Override
			public BlockState getBlockState(BlockPos pos) {
				return pos.equals(fakePos) ? fakeState : original.getBlockState(pos);
			}

			@Override
			public FluidState getFluidState(BlockPos pos) {
				return original.getFluidState(pos);
			}

			@Override
			public int getMinY() {
				return original.getMinY();
			}

			@Override
			public int getHeight() {
				return original.getHeight();
			}

			@Override
			public LevelLightEngine getLightEngine() {
				return original.getLightEngine();
			}

			@Override
			public CardinalLighting cardinalLighting() {
				return original.cardinalLighting();
			}

			@Override
			public int getBlockTint(BlockPos pos, ColorResolver colorResolver) {
				return original.getBlockTint(pos, colorResolver);
			}
		};
	}
}
