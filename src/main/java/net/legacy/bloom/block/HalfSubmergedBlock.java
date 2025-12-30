package net.legacy.bloom.block;

import net.frozenblock.lib.block.api.WaterloggableTallFlowerBlock;
import net.legacy.bloom.tag.BloomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluids;

public class HalfSubmergedBlock extends WaterloggableTallFlowerBlock {

	public HalfSubmergedBlock(Properties properties) {
        super(properties);
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
}
