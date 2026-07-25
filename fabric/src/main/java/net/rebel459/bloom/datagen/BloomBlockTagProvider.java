package net.rebel459.bloom.datagen;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.tag.BloomBlockTags;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.unified.util.builder.WoodSet;

public final class BloomBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

	public BloomBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		this.valueLookupBuilder(BloomBlockTags.RUGS)
			.add(BloomBlocks.WHITE_RUG.get())
			.add(BloomBlocks.ORANGE_RUG.get())
			.add(BloomBlocks.MAGENTA_RUG.get())
			.add(BloomBlocks.LIGHT_BLUE_RUG.get())
			.add(BloomBlocks.YELLOW_RUG.get())
			.add(BloomBlocks.LIME_RUG.get())
			.add(BloomBlocks.PINK_RUG.get())
			.add(BloomBlocks.GRAY_RUG.get())
			.add(BloomBlocks.LIGHT_GRAY_RUG.get())
			.add(BloomBlocks.CYAN_RUG.get())
			.add(BloomBlocks.PURPLE_RUG.get())
			.add(BloomBlocks.BLUE_RUG.get())
			.add(BloomBlocks.BROWN_RUG.get())
			.add(BloomBlocks.GREEN_RUG.get())
			.add(BloomBlocks.RED_RUG.get())
			.add(BloomBlocks.BLACK_RUG.get());

		this.valueLookupBuilder(BloomBlockTags.OVERLAY)
			.addTag(BloomBlockTags.RUGS);

		this.valueLookupBuilder(BloomBlockTags.SLEEPING_BAGS)
			.add(BloomBlocks.WHITE_SLEEPING_BAG.get())
			.add(BloomBlocks.ORANGE_SLEEPING_BAG.get())
			.add(BloomBlocks.MAGENTA_SLEEPING_BAG.get())
			.add(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG.get())
			.add(BloomBlocks.YELLOW_SLEEPING_BAG.get())
			.add(BloomBlocks.LIME_SLEEPING_BAG.get())
			.add(BloomBlocks.PINK_SLEEPING_BAG.get())
			.add(BloomBlocks.GRAY_SLEEPING_BAG.get())
			.add(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG.get())
			.add(BloomBlocks.CYAN_SLEEPING_BAG.get())
			.add(BloomBlocks.PURPLE_SLEEPING_BAG.get())
			.add(BloomBlocks.BLUE_SLEEPING_BAG.get())
			.add(BloomBlocks.BROWN_SLEEPING_BAG.get())
			.add(BloomBlocks.GREEN_SLEEPING_BAG.get())
			.add(BloomBlocks.RED_SLEEPING_BAG.get())
			.add(BloomBlocks.BLACK_SLEEPING_BAG.get());

        this.valueLookupBuilder(BloomBlockTags.ARID_VEGETATION_MAY_PLACE_ON)
			.addOptionalTag(BlockTags.SAND)
			.addOptionalTag(BlockTags.DIRT)
			.add(Blocks.FARMLAND);

        this.valueLookupBuilder(BloomBlockTags.SUBMERGED_VEGETATION_MAY_PLACE_ON)
			.addOptionalTag(BlockTags.SAND)
			.addOptionalTag(BlockTags.DIRT)
			.add(Blocks.FARMLAND)
			.add(Blocks.CLAY);

		this.valueLookupBuilder(BloomBlockTags.WILD_CROPS)
			.add(BloomBlocks.WILD_COTTON.get());

		this.valueLookupBuilder(BlockTags.SMALL_FLOWERS)
			.add(BloomBlocks.PINK_ORCHID.get())
			.add(BloomBlocks.GOLDENROD.get())
			.add(BloomBlocks.CALLA_LILY.get())
			.add(BloomBlocks.ORANGE_DAISY.get())
			.add(BloomBlocks.HYACINTH.get())
			.add(BloomBlocks.QUEENCUP.get())
			.add(BloomBlocks.LAVENDER.get())
			.add(BloomBlocks.WILD_COTTON.get());

		this.valueLookupBuilder(BlockTags.FLOWERS)
			.add(BloomBlocks.DIANTHUS.get())
			.add(BloomBlocks.SCILLA.get())
			.add(BloomBlocks.BELLFLOWER.get())
			.add(BloomBlocks.BROMELIAD.get())
			.add(BloomBlocks.HELLEBORE.get())
			.add(BloomBlocks.HYDRANGEA.get());

		this.valueLookupBuilder(BlockTags.FLOWER_POTS)
			.add(BloomBlocks.POTTED_DIANTHUS.get())
			.add(BloomBlocks.POTTED_GOLDENROD.get())
			.add(BloomBlocks.POTTED_CALLA_LILY.get())
			.add(BloomBlocks.POTTED_ORANGE_DAISY.get())
			.add(BloomBlocks.POTTED_SCILLA.get())
			.add(BloomBlocks.POTTED_PINK_ORCHID.get())
			.add(BloomBlocks.POTTED_BROMELIAD.get())
			.add(BloomBlocks.POTTED_HELLEBORE.get())
			.add(BloomBlocks.POTTED_HYACINTH.get())
			.add(BloomBlocks.POTTED_QUEENCUP.get())
			.add(BloomBlocks.POTTED_LAVENDER.get());

        this.valueLookupBuilder(BlockTags.LOGS)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

        this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

		tagWoodSet(BloomBlocks.JACARANDA, BloomBlockTags.JACARANDA_LOGS);
		tagWoodSet(BloomBlocks.GOLDEN_BIRCH, BloomBlockTags.GOLDEN_BIRCH_LOGS);
		tagWoodSet(BloomBlocks.PINE, BloomBlockTags.PINE_LOGS);

		this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(BloomBlocks.DOLERITE.get())
			.add(BloomBlocks.POLISHED_DOLERITE.get())
			.add(BloomBlocks.POLISHED_DOLERITE_SLAB.get())
			.add(BloomBlocks.POLISHED_DOLERITE_STAIRS.get())
			.add(BloomBlocks.POLISHED_DOLERITE_WALL.get())
			.add(BloomBlocks.DOLERITE_BRICKS.get())
			.add(BloomBlocks.DOLERITE_BRICK_SLAB.get())
			.add(BloomBlocks.DOLERITE_BRICK_STAIRS.get())
			.add(BloomBlocks.DOLERITE_BRICK_WALL.get())
			.add(BloomBlocks.DOLERITE_TILES.get())
			.add(BloomBlocks.DOLERITE_TILE_SLAB.get())
			.add(BloomBlocks.DOLERITE_TILE_STAIRS.get())
			.add(BloomBlocks.DOLERITE_TILE_WALL.get());

		this.valueLookupBuilder(BlockTags.STAIRS)
			.add(BloomBlocks.POLISHED_DOLERITE_STAIRS.get())
			.add(BloomBlocks.DOLERITE_BRICK_STAIRS.get())
			.add(BloomBlocks.DOLERITE_TILE_STAIRS.get());

		this.valueLookupBuilder(BlockTags.SLABS)
			.add(BloomBlocks.POLISHED_DOLERITE_SLAB.get())
			.add(BloomBlocks.DOLERITE_BRICK_SLAB.get())
			.add(BloomBlocks.DOLERITE_TILE_SLAB.get());

		this.valueLookupBuilder(BlockTags.STONE_ORE_REPLACEABLES)
			.add(BloomBlocks.DOLERITE.get());

		this.valueLookupBuilder(BlockTags.DRIPSTONE_REPLACEABLE)
			.add(BloomBlocks.DOLERITE.get())
			.add(Blocks.SANDSTONE)
			.add(Blocks.RED_SANDSTONE);

		this.valueLookupBuilder(BlockTags.MOSS_REPLACEABLE)
			.add(BloomBlocks.DOLERITE.get())
			.add(Blocks.SANDSTONE)
			.add(Blocks.RED_SANDSTONE);

		this.valueLookupBuilder(BlockTags.SCULK_REPLACEABLE)
			.add(BloomBlocks.DOLERITE.get());

		this.valueLookupBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
			.addTag(BloomBlockTags.SLEEPING_BAGS);

		this.valueLookupBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
			.addTag(BloomBlockTags.RUGS);

		this.valueLookupBuilder(BlockTags.CROPS)
			.add(BloomBlocks.COTTON.get());

		this.valueLookupBuilder(BlockTags.WALLS)
			.add(BloomBlocks.POLISHED_DOLERITE_WALL.get())
			.add(BloomBlocks.DOLERITE_BRICK_WALL.get())
			.add(BloomBlocks.DOLERITE_TILE_WALL.get());

		this.valueLookupBuilder(BlockTags.BASE_STONE_OVERWORLD)
			.add(BloomBlocks.DOLERITE.get());

		StoneOresRegistry.ALL_REGISTRIES.forEach(this::tagOres);
	}

	public void tagWoodSet(WoodSet woodSet, TagKey<Block> tag) {

		this.valueLookupBuilder(BlockTags.STANDING_SIGNS)
			.add(woodSet.getSign().get());

		this.valueLookupBuilder(BlockTags.WALL_SIGNS)
			.add(woodSet.getWallSign().get());

		this.valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS)
			.add(woodSet.getHangingSign().get());

		this.valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS)
			.add(woodSet.getWallHangingSign().get());

		this.valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
			.add(woodSet.getLog().get());

		if (woodSet.hasWood()) {
			this.valueLookupBuilder(tag)
				.add(woodSet.getLog().get(), woodSet.getStrippedLog().get())
				.add(woodSet.getWood().get(), woodSet.getStrippedWood().get());
		}
		else  {
			this.valueLookupBuilder(tag)
				.add(woodSet.getLog().get(), woodSet.getStrippedLog().get());
		}

		this.valueLookupBuilder(BlockTags.LOGS)
			.addOptionalTag(tag);

		this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
			.addOptionalTag(tag);

		this.valueLookupBuilder(BlockTags.LOGS)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

		this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

		if (woodSet.hasLeaves()) {
			this.valueLookupBuilder(BlockTags.LEAVES)
				.add(woodSet.getLeaves().get());
		}

		this.valueLookupBuilder(BlockTags.PLANKS)
			.add(woodSet.getPlanks().get());

		this.valueLookupBuilder(BlockTags.SIGNS)
			.add(woodSet.getSign().get());

		this.valueLookupBuilder(BlockTags.ALL_HANGING_SIGNS)
			.add(woodSet.getHangingSign().get());

		this.valueLookupBuilder(BlockTags.WOODEN_BUTTONS)
			.add(woodSet.getButton().get());

		this.valueLookupBuilder(BlockTags.WOODEN_DOORS)
			.add(woodSet.getDoor().get());

		this.valueLookupBuilder(BlockTags.WOODEN_FENCES)
			.add(woodSet.getFence().get());

		this.valueLookupBuilder(BlockTags.FENCE_GATES)
			.add(woodSet.getFenceGate().get());

		this.valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
			.add(woodSet.getPressurePlate().get());

		this.valueLookupBuilder(BlockTags.WOODEN_SLABS)
			.add(woodSet.getSlab().get());

		this.valueLookupBuilder(BlockTags.WOODEN_STAIRS)
			.add(woodSet.getStairs().get());

		this.valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
			.add(woodSet.getTrapdoor().get());

		this.valueLookupBuilder(BlockTags.SAPLINGS)
			.add(woodSet.getSapling().get());

		this.valueLookupBuilder(BlockTags.FLOWER_POTS)
			.add(woodSet.getPottedSapling().get());
	}

    public void tagOres(StoneOresRegistry ores) {
        ores.getOresMap().forEach((type, block) -> {
            String name = type.name;
			String blockName = block.get().getName().getString();

			addOptionalTags(block.get(), BlockTags.MINEABLE_WITH_PICKAXE);
			if (blockName.contains("sandstone")) {
				addOptionalTags(block.get(), getTag("wilderwild:sound/sandstone"));
			}

            if (Objects.equals(name, "coal")) addTags(block.get(), BlockTags.COAL_ORES);
            if (Objects.equals(name, "copper")) addTags(block.get(), BlockTags.COPPER_ORES, BlockTags.NEEDS_STONE_TOOL);
            if (Objects.equals(name, "iron")) addTags(block.get(), BlockTags.IRON_ORES, BlockTags.NEEDS_STONE_TOOL);
            if (Objects.equals(name, "redstone")) addTags(block.get(), BlockTags.REDSTONE_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "gold")) addTags(block.get(), BlockTags.GOLD_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "diamond")) addTags(block.get(), BlockTags.DIAMOND_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "emerald")) addTags(block.get(), BlockTags.EMERALD_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "lapis")) addTags(block.get(), BlockTags.LAPIS_ORES, BlockTags.NEEDS_IRON_TOOL);
            if (Objects.equals(name, "sapphire")) addOptionalTags(block.get(), BloomBlockTags.SAPPHIRE_ORES, BlockTags.NEEDS_IRON_TOOL);
        });
    }

	@SafeVarargs
	public final void addTags(Block block, TagKey<Block>... tags) {
		List<TagKey<Block>> tagList = Arrays.asList(tags);
		tagList.forEach((tag) -> {
			this.valueLookupBuilder(tag).add(block);
		});
	}
	@SafeVarargs
	public final void addOptionalTags(Block block, TagKey<Block>... tags) {
		ResourceKey<Block> blockKey = block.defaultBlockState().typeHolder().unwrapKey().get();
		List<TagKey<Block>> tagList = Arrays.asList(tags);
		tagList.forEach((tag) -> {
			this.builder(tag).addOptional(blockKey);
		});
	}

	private TagKey<Block> getTag(String id) {
		return TagKey.create(this.registryKey, Identifier.parse(id));
	}
}
