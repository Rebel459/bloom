package net.legacy.bloom.registry;

import net.legacy.bloom.Bloom;
import net.legacy.bloom.block.entity.SleepingBagBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import java.util.Set;

public class BloomBlockEntities {
	public static void init() {}

	public static BlockEntityType<SleepingBagBlockEntity> SLEEPING_BAG = register(
		"sleeping_bag",
		SleepingBagBlockEntity::new,
		BloomBlocks.WHITE_SLEEPING_BAG,
		BloomBlocks.ORANGE_SLEEPING_BAG,
		BloomBlocks.MAGENTA_SLEEPING_BAG,
		BloomBlocks.LIGHT_BLUE_SLEEPING_BAG,
		BloomBlocks.YELLOW_SLEEPING_BAG,
		BloomBlocks.LIME_SLEEPING_BAG,
		BloomBlocks.PINK_SLEEPING_BAG,
		BloomBlocks.GRAY_SLEEPING_BAG,
		BloomBlocks.LIGHT_GRAY_SLEEPING_BAG,
		BloomBlocks.CYAN_SLEEPING_BAG,
		BloomBlocks.PURPLE_SLEEPING_BAG,
		BloomBlocks.BLUE_SLEEPING_BAG,
		BloomBlocks.BROWN_SLEEPING_BAG,
		BloomBlocks.GREEN_SLEEPING_BAG,
		BloomBlocks.RED_SLEEPING_BAG,
		BloomBlocks.BLACK_SLEEPING_BAG
	);

	private static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityType.BlockEntitySupplier<T> builder, Block... blocks) {
		Util.fetchChoiceType(References.BLOCK_ENTITY, Bloom.id(path).toString());
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Bloom.id(path), new BlockEntityType<>(builder, Set.of(blocks)));
	}
}
