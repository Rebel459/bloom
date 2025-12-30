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
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos upper;
        BlockPos lower;
        if (blockState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            upper = blockPos;
            lower = blockPos.below();
        }
        else {
            upper = blockPos.above();
            lower = blockPos;
        }
        return levelReader.getFluidState(lower).is(Fluids.WATER) && levelReader.getFluidState(upper).isEmpty() && levelReader.getBlockState(lower.below()).is(BloomBlockTags.SUBMERGED_VEGETATION_MAY_PLACE_ON);
    }
}