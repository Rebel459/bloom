package net.legacy.bloom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;

public class SleepingBagRenderState extends BlockEntityRenderState {
	public DyeColor color = DyeColor.WHITE;
	public Direction facing = Direction.NORTH;
	public boolean isHead;
}
