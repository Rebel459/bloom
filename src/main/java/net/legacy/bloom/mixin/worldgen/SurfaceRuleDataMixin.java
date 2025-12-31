package net.legacy.bloom.mixin.worldgen;

import net.legacy.bloom.util.SurfaceRuleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.SurfaceRuleData;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SurfaceRuleData.class)
public class SurfaceRuleDataMixin {

    @Inject(method = "makeStateRule", at = @At("TAIL"), cancellable = true)
    private static void replaceStone(Block block, CallbackInfoReturnable<SurfaceRules.RuleSource> cir) {
        if (block == Blocks.STONE) {
			cir.setReturnValue(
				SurfaceRules.sequence(
					SurfaceRuleHelper.STONE_REPLACEMENT_RULES,
					cir.getReturnValue()
				)
			);
		}
    }
}
