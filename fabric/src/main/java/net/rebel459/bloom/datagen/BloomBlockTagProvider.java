package net.rebel459.bloom.datagen;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.tag.BloomBlockTags;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.unified.api.data.helper.TagGenerator;

public final class BloomBlockTagProvider {

	private static final TagGenerator TAGS = Bloom.DATA.helpers().tags();

	public static void init() {

		TAGS.create(BloomBlockTags.RUGS)
			.add(BloomBlocks.WHITE_RUG.key())
			.add(BloomBlocks.ORANGE_RUG.key())
			.add(BloomBlocks.MAGENTA_RUG.key())
			.add(BloomBlocks.LIGHT_BLUE_RUG.key())
			.add(BloomBlocks.YELLOW_RUG.key())
			.add(BloomBlocks.LIME_RUG.key())
			.add(BloomBlocks.PINK_RUG.key())
			.add(BloomBlocks.GRAY_RUG.key())
			.add(BloomBlocks.LIGHT_GRAY_RUG.key())
			.add(BloomBlocks.CYAN_RUG.key())
			.add(BloomBlocks.PURPLE_RUG.key())
			.add(BloomBlocks.BLUE_RUG.key())
			.add(BloomBlocks.BROWN_RUG.key())
			.add(BloomBlocks.GREEN_RUG.key())
			.add(BloomBlocks.RED_RUG.key())
			.add(BloomBlocks.BLACK_RUG.key());

		TAGS.create(BloomBlockTags.OVERLAY)
			.add(BloomBlockTags.RUGS);

		TAGS.create(BloomBlockTags.SLEEPING_BAGS)
			.add(BloomBlocks.WHITE_SLEEPING_BAG.key())
			.add(BloomBlocks.ORANGE_SLEEPING_BAG.key())
			.add(BloomBlocks.MAGENTA_SLEEPING_BAG.key())
			.add(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG.key())
			.add(BloomBlocks.YELLOW_SLEEPING_BAG.key())
			.add(BloomBlocks.LIME_SLEEPING_BAG.key())
			.add(BloomBlocks.PINK_SLEEPING_BAG.key())
			.add(BloomBlocks.GRAY_SLEEPING_BAG.key())
			.add(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG.key())
			.add(BloomBlocks.CYAN_SLEEPING_BAG.key())
			.add(BloomBlocks.PURPLE_SLEEPING_BAG.key())
			.add(BloomBlocks.BLUE_SLEEPING_BAG.key())
			.add(BloomBlocks.BROWN_SLEEPING_BAG.key())
			.add(BloomBlocks.GREEN_SLEEPING_BAG.key())
			.add(BloomBlocks.RED_SLEEPING_BAG.key())
			.add(BloomBlocks.BLACK_SLEEPING_BAG.key());

		TAGS.create(BloomBlockTags.ARID_VEGETATION_MAY_PLACE_ON)
			.addOptional(BlockTags.SAND)
			.addOptional(BlockTags.DIRT)
			.add(Blocks.FARMLAND.defaultBlockState().typeHolder().unwrapKey().get());

		TAGS.create(BloomBlockTags.SUBMERGED_VEGETATION_MAY_PLACE_ON)
			.addOptional(BlockTags.SAND)
			.addOptional(BlockTags.DIRT)
			.add(Blocks.FARMLAND.defaultBlockState().typeHolder().unwrapKey().get())
			.add(Blocks.CLAY.defaultBlockState().typeHolder().unwrapKey().get());

		TAGS.create(BloomBlockTags.WILD_CROPS)
			.add(BloomBlocks.WILD_COTTON.key());

		TAGS.create(BlockTags.SMALL_FLOWERS)
			.add(BloomBlocks.PINK_ORCHID.key())
			.add(BloomBlocks.GOLDENROD.key())
			.add(BloomBlocks.CALLA_LILY.key())
			.add(BloomBlocks.ORANGE_DAISY.key())
			.add(BloomBlocks.HYACINTH.key())
			.add(BloomBlocks.QUEENCUP.key())
			.add(BloomBlocks.LAVENDER.key())
			.add(BloomBlocks.WILD_COTTON.key());

		TAGS.create(BlockTags.FLOWERS)
			.add(BloomBlocks.DIANTHUS.key())
			.add(BloomBlocks.SCILLA.key())
			.add(BloomBlocks.BELLFLOWER.key())
			.add(BloomBlocks.BROMELIAD.key())
			.add(BloomBlocks.HELLEBORE.key())
			.add(BloomBlocks.HYDRANGEA.key());

		TAGS.create(BlockTags.FLOWER_POTS)
			.add(BloomBlocks.POTTED_DIANTHUS.key())
			.add(BloomBlocks.POTTED_GOLDENROD.key())
			.add(BloomBlocks.POTTED_CALLA_LILY.key())
			.add(BloomBlocks.POTTED_ORANGE_DAISY.key())
			.add(BloomBlocks.POTTED_SCILLA.key())
			.add(BloomBlocks.POTTED_PINK_ORCHID.key())
			.add(BloomBlocks.POTTED_BROMELIAD.key())
			.add(BloomBlocks.POTTED_HELLEBORE.key())
			.add(BloomBlocks.POTTED_HYACINTH.key())
			.add(BloomBlocks.POTTED_QUEENCUP.key())
			.add(BloomBlocks.POTTED_LAVENDER.key());

		TAGS.create(BlockTags.LOGS)
			.addOptional(BloomBlockTags.JACARANDA_LOGS);

		TAGS.create(BlockTags.LOGS_THAT_BURN)
			.addOptional(BloomBlockTags.JACARANDA_LOGS);

		TAGS.create(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(BloomBlocks.DOLERITE.key())
			.add(BloomBlocks.POLISHED_DOLERITE.key())
			.add(BloomBlocks.POLISHED_DOLERITE_SLAB.key())
			.add(BloomBlocks.POLISHED_DOLERITE_STAIRS.key())
			.add(BloomBlocks.POLISHED_DOLERITE_WALL.key())
			.add(BloomBlocks.DOLERITE_BRICKS.key())
			.add(BloomBlocks.DOLERITE_BRICK_SLAB.key())
			.add(BloomBlocks.DOLERITE_BRICK_STAIRS.key())
			.add(BloomBlocks.DOLERITE_BRICK_WALL.key())
			.add(BloomBlocks.DOLERITE_TILES.key())
			.add(BloomBlocks.DOLERITE_TILE_SLAB.key())
			.add(BloomBlocks.DOLERITE_TILE_STAIRS.key())
			.add(BloomBlocks.DOLERITE_TILE_WALL.key());

		TAGS.create(BlockTags.STAIRS)
			.add(BloomBlocks.POLISHED_DOLERITE_STAIRS.key())
			.add(BloomBlocks.DOLERITE_BRICK_STAIRS.key())
			.add(BloomBlocks.DOLERITE_TILE_STAIRS.key());

		TAGS.create(BlockTags.SLABS)
			.add(BloomBlocks.POLISHED_DOLERITE_SLAB.key())
			.add(BloomBlocks.DOLERITE_BRICK_SLAB.key())
			.add(BloomBlocks.DOLERITE_TILE_SLAB.key());

		TAGS.create(BlockTags.STONE_ORE_REPLACEABLES)
			.add(BloomBlocks.DOLERITE.key());

		TAGS.create(BlockTags.DRIPSTONE_REPLACEABLE)
			.add(BloomBlocks.DOLERITE.key())
			.add(Blocks.SANDSTONE.defaultBlockState().typeHolder().unwrapKey().get())
			.add(Blocks.RED_SANDSTONE.defaultBlockState().typeHolder().unwrapKey().get());

		TAGS.create(BlockTags.MOSS_REPLACEABLE)
			.add(BloomBlocks.DOLERITE.key())
			.add(Blocks.SANDSTONE.defaultBlockState().typeHolder().unwrapKey().get())
			.add(Blocks.RED_SANDSTONE.defaultBlockState().typeHolder().unwrapKey().get());

		TAGS.create(BlockTags.SCULK_REPLACEABLE)
			.add(BloomBlocks.DOLERITE.key());

		TAGS.create(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
			.add(BloomBlockTags.SLEEPING_BAGS);

		TAGS.create(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
			.add(BloomBlockTags.RUGS);

		TAGS.create(BlockTags.CROPS)
			.add(BloomBlocks.COTTON.key());

		TAGS.create(BlockTags.WALLS)
			.add(BloomBlocks.POLISHED_DOLERITE_WALL.key())
			.add(BloomBlocks.DOLERITE_BRICK_WALL.key())
			.add(BloomBlocks.DOLERITE_TILE_WALL.key());

		TAGS.create(BlockTags.BASE_STONE_OVERWORLD)
			.add(BloomBlocks.DOLERITE.key());

		StoneOresRegistry.ALL_REGISTRIES.forEach(BloomBlockTagProvider::tagOres);
	}

    public static void tagOres(StoneOresRegistry ores) {
        ores.getOresMap().forEach((type, block) -> {
            String name = type.name;
			String blockName = block.get().getName().getString();

			addOptionalTags(block.key(), BlockTags.MINEABLE_WITH_PICKAXE);
			if (blockName.contains("sandstone")) {
				addOptionalTags(block.key(), getTag("wilderwild:sound/sandstone"));
			}

            if (Objects.equals(name, "coal")) addTags(block.key(), BlockTags.COAL_ORES);
            if (Objects.equals(name, "copper")) addTags(block.key(), BlockTags.COPPER_ORES, BlockTags.NEEDS_STONE_TOOL);
            if (Objects.equals(name, "iron")) addTags(block.key(), BlockTags.IRON_ORES, BlockTags.NEEDS_STONE_TOOL);
            if (Objects.equals(name, "redstone")) addTags(block.key(), BlockTags.REDSTONE_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "gold")) addTags(block.key(), BlockTags.GOLD_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "diamond")) addTags(block.key(), BlockTags.DIAMOND_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "emerald")) addTags(block.key(), BlockTags.EMERALD_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "lapis")) addTags(block.key(), BlockTags.LAPIS_ORES, BlockTags.NEEDS_STONE_TOOL);
            if (Objects.equals(name, "sapphire")) addOptionalTags(block.key(), BloomBlockTags.SAPPHIRE_ORES, BlockTags.NEEDS_IRON_TOOL);
        });
    }

	@SafeVarargs
	public static void addTags(ResourceKey<Block> block, TagKey<Block>... tags) {
		List<TagKey<Block>> tagList = Arrays.asList(tags);
		tagList.forEach((tag) -> {
			TAGS.create(tag).add(block);
		});
	}

	@SafeVarargs
	public static void addOptionalTags(ResourceKey<Block> block, TagKey<Block>... tags) {
		List<TagKey<Block>> tagList = Arrays.asList(tags);
		tagList.forEach((tag) -> {
			TAGS.create(tag).addOptional(block);
		});
	}

	private static TagKey<Block> getTag(String id) {
		return TagKey.create(Registries.BLOCK, Identifier.parse(id));
	}
}
