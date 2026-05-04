package net.rebel459.bloom.registry;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public final class BloomBlockStateProperties {

	public static IntegerProperty EXTENDED_DISTANCE = IntegerProperty.create("extended_distance", 1, 8);

	public static void init() {}
}
