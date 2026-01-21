package net.legacy.bloom.mixin.item;

import net.legacy.bloom.config.BloomConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoeItem.class)
public class HoeItemMixin {

    @Unique
    public BlockPos add(int i, int j, int k, BlockPos blockPos) {
        return i == 0 && j == 0 && k == 0 ? blockPos : new BlockPos(blockPos.getX() + i, blockPos.getY() + j, blockPos.getZ() + k);
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void harvestCropsWithHoe(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
		if (!BloomConfig.get.farming.hoe_replanting) return;

        Level level = context.getLevel();
        if (level.isClientSide() || !(level instanceof ServerLevel)) return;

        BlockPos pos = context.getClickedPos();
        Block block = level.getBlockState(pos).getBlock();
        if (!(block instanceof CropBlock cropBlock) || !cropBlock.isMaxAge(level.getBlockState(pos))) return;

        Player player = context.getPlayer();
        if (!context.getItemInHand().is(ItemTags.HOES) || player == null) return;
        int radius = 0;

        ServerLevel serverLevel = (ServerLevel) level;
        boolean harvested = false;

        EquipmentSlot slot = context.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                BlockPos nearbyPos = add(dx, 0, dz, pos);
                BlockState blockState = level.getBlockState(nearbyPos);
                Block nearbyBlock = blockState.getBlock();

                if (!(nearbyBlock instanceof CropBlock crop) || !crop.isMaxAge(blockState))
                    continue;

                serverLevel.setBlock(nearbyPos, crop.defaultBlockState(), 512);
                Block.dropResources(blockState, serverLevel, nearbyPos);
                context.getItemInHand().hurtAndBreak(1, player, slot);

                harvested = true;
            }
        }

        if (harvested) {
            serverLevel.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
			cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
