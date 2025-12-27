package net.legacy.bloom.block;

import net.legacy.bloom.tag.BloomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DryVegetationBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AridVegetationBlock extends DryVegetationBlock {
    public AridVegetationBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(BloomBlockTags.ARID_VEGETATION_MAY_PLACE_ON);
    }
}