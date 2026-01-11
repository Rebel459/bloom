package net.legacy.bloom.block.entity;

import net.legacy.bloom.Bloom;
import net.legacy.bloom.block.SleepingBagBlock;
import net.legacy.bloom.registry.BloomBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Set;

public class SleepingBagBlockEntity extends BlockEntity {
	private final DyeColor color;

	public SleepingBagBlockEntity(BlockPos blockPos, BlockState blockState) {
		this(blockPos, blockState, ((SleepingBagBlock)blockState.getBlock()).getColor());
	}

	public SleepingBagBlockEntity(BlockPos blockPos, BlockState blockState, DyeColor dyeColor) {
		super(BloomBlockEntities.SLEEPING_BAG, blockPos, blockState);
		this.color = dyeColor;
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public DyeColor getColor() {
		return this.color;
	}
}
