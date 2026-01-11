package net.legacy.bloom.registry;

import net.legacy.bloom.Bloom;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import java.util.Set;

public class BloomBlockEntities {

	public static void init() {
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.WHITE_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.ORANGE_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.MAGENTA_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.YELLOW_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.LIME_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.PINK_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.GRAY_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.CYAN_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.PURPLE_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.BLUE_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.BROWN_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.GREEN_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.RED_SLEEPING_BAG);
		BlockEntityType.BED.addSupportedBlock(BloomBlocks.BLACK_SLEEPING_BAG);
	}

	private static <T extends BlockEntity> BlockEntityType<T> init(String path, BlockEntityType.BlockEntitySupplier<T> builder, Block... blocks) {
		Util.fetchChoiceType(References.BLOCK_ENTITY, Bloom.id(path).toString());
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Bloom.id(path), new BlockEntityType<>(builder, Set.of(blocks)));
	}
}
