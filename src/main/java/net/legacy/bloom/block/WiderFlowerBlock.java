package net.legacy.bloom.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WiderFlowerBlock extends FlowerBlock implements SuspiciousEffectHolder {
    private static final VoxelShape SHAPE = Block.column(10.0, 0.0, 10.0);

    public WiderFlowerBlock(Holder<MobEffect> holder, float f, Properties properties) {
        super(holder, f, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE.move(blockState.getOffset(blockPos));
    }
}
