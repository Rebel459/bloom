package net.rebel459.bloom.block;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.rebel459.bloom.tag.BloomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class HalfSubmergedBlock extends TallFlowerBlock implements SimpleWaterloggedBlock {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public HalfSubmergedBlock(Properties properties) {
        super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
	}

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos upper;
        BlockPos lower;
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            upper = pos;
            lower = pos.below();
        } else {
            upper = pos.above();
            lower = pos;
        }
        return level.getFluidState(lower).is(Fluids.WATER)
			&& level.getFluidState(upper).isEmpty()
			&& level.getBlockState(lower.below()).is(BloomBlockTags.SUBMERGED_VEGETATION_MAY_PLACE_ON);
    }

	@Override
	protected BlockState updateShape(
		BlockState state,
		LevelReader level,
		ScheduledTickAccess tickAccess,
		BlockPos pos,
		Direction direction,
		BlockPos neighborPos,
		BlockState neighborState,
		RandomSource random
	) {
		if (state.getValue(WATERLOGGED)) tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		return super.updateShape(state, level, tickAccess, pos, direction, neighborPos, neighborState, random);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		final BlockPos pos = context.getClickedPos();
		final Level level = context.getLevel();
		final FluidState fluidState = level.getFluidState(pos);
		return !level.isOutsideBuildHeight(pos.getY() + 1) && level.getBlockState(pos.above()).canBeReplaced(context) ? this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER) : null;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(WATERLOGGED);
	}
}
