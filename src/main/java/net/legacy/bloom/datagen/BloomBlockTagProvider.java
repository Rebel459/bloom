package net.legacy.bloom.datagen;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.tag.BloomBlockTags;
import net.legacy.bloom.util.StoneOresRegistry;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class BloomBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public BloomBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		this.valueLookupBuilder(BloomBlockTags.RUGS)
			.add(BloomBlocks.WHITE_RUG)
			.add(BloomBlocks.ORANGE_RUG)
			.add(BloomBlocks.MAGENTA_RUG)
			.add(BloomBlocks.LIGHT_BLUE_RUG)
			.add(BloomBlocks.YELLOW_RUG)
			.add(BloomBlocks.LIME_RUG)
			.add(BloomBlocks.PINK_RUG)
			.add(BloomBlocks.GRAY_RUG)
			.add(BloomBlocks.LIGHT_GRAY_RUG)
			.add(BloomBlocks.CYAN_RUG)
			.add(BloomBlocks.PURPLE_RUG)
			.add(BloomBlocks.BLUE_RUG)
			.add(BloomBlocks.BROWN_RUG)
			.add(BloomBlocks.GREEN_RUG)
			.add(BloomBlocks.RED_RUG)
			.add(BloomBlocks.BLACK_RUG);

		this.valueLookupBuilder(BloomBlockTags.OVERLAY)
			.addTag(BloomBlockTags.RUGS);

		this.valueLookupBuilder(BloomBlockTags.SLEEPING_BAGS)
			.add(BloomBlocks.WHITE_SLEEPING_BAG)
			.add(BloomBlocks.ORANGE_SLEEPING_BAG)
			.add(BloomBlocks.MAGENTA_SLEEPING_BAG)
			.add(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG)
			.add(BloomBlocks.YELLOW_SLEEPING_BAG)
			.add(BloomBlocks.LIME_SLEEPING_BAG)
			.add(BloomBlocks.PINK_SLEEPING_BAG)
			.add(BloomBlocks.GRAY_SLEEPING_BAG)
			.add(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG)
			.add(BloomBlocks.CYAN_SLEEPING_BAG)
			.add(BloomBlocks.PURPLE_SLEEPING_BAG)
			.add(BloomBlocks.BLUE_SLEEPING_BAG)
			.add(BloomBlocks.BROWN_SLEEPING_BAG)
			.add(BloomBlocks.GREEN_SLEEPING_BAG)
			.add(BloomBlocks.RED_SLEEPING_BAG)
			.add(BloomBlocks.BLACK_SLEEPING_BAG);

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
			.add(BloomBlocks.WILD_COTTON);

		this.valueLookupBuilder(BlockTags.SMALL_FLOWERS)
			.add(BloomBlocks.PINK_ORCHID)
			.add(BloomBlocks.GOLDENROD)
			.add(BloomBlocks.CALLA_LILY)
			.add(BloomBlocks.ORANGE_DAISY)
			.add(BloomBlocks.HYACINTH)
			.add(BloomBlocks.QUEENCUP)
			.add(BloomBlocks.WILD_COTTON);

		this.valueLookupBuilder(BlockTags.FLOWERS)
			.add(BloomBlocks.DIANTHUS)
			.add(BloomBlocks.SCILLA)
			.add(BloomBlocks.BELLFLOWER)
			.add(BloomBlocks.BROMELIAD)
			.add(BloomBlocks.HELLEBORE)
			.add(BloomBlocks.HYDRANGEA);

		this.valueLookupBuilder(BlockTags.FLOWER_POTS)
			.add(BloomBlocks.POTTED_DIANTHUS)
			.add(BloomBlocks.POTTED_GOLDENROD)
			.add(BloomBlocks.POTTED_CALLA_LILY)
			.add(BloomBlocks.POTTED_ORANGE_DAISY)
			.add(BloomBlocks.POTTED_SCILLA)
			.add(BloomBlocks.POTTED_PINK_ORCHID)
			.add(BloomBlocks.POTTED_BROMELIAD)
			.add(BloomBlocks.POTTED_HELLEBORE)
			.add(BloomBlocks.POTTED_HYACINTH)
			.add(BloomBlocks.POTTED_QUEENCUP);

        this.valueLookupBuilder(BlockTags.LOGS)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

        this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

        this.valueLookupBuilder(BlockTags.SAPLINGS)
			.add(BloomBlocks.JACARANDA_SAPLING);

		tagWoodset(BloomBlocks.JACARANDA, BloomBlockTags.JACARANDA_LOGS);
		tagWoodset(BloomBlocks.GOLDEN_BIRCH, BloomBlockTags.GOLDEN_BIRCH_LOGS);
		tagWoodset(BloomBlocks.PINE, BloomBlockTags.PINE_LOGS);

		this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(BloomBlocks.DOLERITE)
			.add(BloomBlocks.POLISHED_DOLERITE)
			.add(BloomBlocks.POLISHED_DOLERITE_SLAB)
			.add(BloomBlocks.POLISHED_DOLERITE_STAIRS)
			.add(BloomBlocks.POLISHED_DOLERITE_WALL)
			.add(BloomBlocks.DOLERITE_BRICKS)
			.add(BloomBlocks.DOLERITE_BRICK_SLAB)
			.add(BloomBlocks.DOLERITE_BRICK_STAIRS)
			.add(BloomBlocks.DOLERITE_BRICK_WALL)
			.add(BloomBlocks.DOLERITE_TILES)
			.add(BloomBlocks.DOLERITE_TILE_SLAB)
			.add(BloomBlocks.DOLERITE_TILE_STAIRS)
			.add(BloomBlocks.DOLERITE_TILE_WALL);

		this.valueLookupBuilder(BlockTags.STAIRS)
			.add(BloomBlocks.POLISHED_DOLERITE_STAIRS)
			.add(BloomBlocks.DOLERITE_BRICK_STAIRS)
			.add(BloomBlocks.DOLERITE_TILE_STAIRS);

		this.valueLookupBuilder(BlockTags.SLABS)
			.add(BloomBlocks.POLISHED_DOLERITE_SLAB)
			.add(BloomBlocks.DOLERITE_BRICK_SLAB)
			.add(BloomBlocks.DOLERITE_TILE_SLAB);

		this.valueLookupBuilder(BlockTags.STONE_ORE_REPLACEABLES)
			.add(BloomBlocks.DOLERITE);

		this.valueLookupBuilder(BlockTags.DRIPSTONE_REPLACEABLE)
			.add(BloomBlocks.DOLERITE)
			.add(Blocks.SANDSTONE)
			.add(Blocks.RED_SANDSTONE);

		this.valueLookupBuilder(BlockTags.MOSS_REPLACEABLE)
			.add(BloomBlocks.DOLERITE)
			.add(Blocks.SANDSTONE)
			.add(Blocks.RED_SANDSTONE);

		this.valueLookupBuilder(BlockTags.SCULK_REPLACEABLE)
			.add(BloomBlocks.DOLERITE);

		this.valueLookupBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
			.addTag(BloomBlockTags.SLEEPING_BAGS);

		this.valueLookupBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
			.addTag(BloomBlockTags.RUGS);

		this.valueLookupBuilder(BlockTags.CROPS)
			.add(BloomBlocks.COTTON);

		this.valueLookupBuilder(BlockTags.WALLS)
			.add(BloomBlocks.POLISHED_DOLERITE_WALL)
			.add(BloomBlocks.DOLERITE_BRICK_WALL)
			.add(BloomBlocks.DOLERITE_TILE_WALL);

		this.valueLookupBuilder(BlockTags.BASE_STONE_OVERWORLD)
			.add(BloomBlocks.DOLERITE);

		StoneOresRegistry.ALL_REGISTRIES.forEach(this::tagOres);

		this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
			.addOptionalTag(BloomBlockTags.SAPPHIRE_ORES);
	}

	public void tagWoodset(WoodsetRegistry woodset, TagKey<Block> tag) {

		this.valueLookupBuilder(BlockTags.STANDING_SIGNS)
			.add(woodset.getSign());

		this.valueLookupBuilder(BlockTags.WALL_SIGNS)
			.add(woodset.getWallSign());

		this.valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS)
			.add(woodset.getHangingSign());

		this.valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS)
			.add(woodset.getWallHangingSign());

		this.valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
			.add(woodset.getLog());

		this.valueLookupBuilder(tag)
			.add(woodset.getLog(), woodset.getStrippedLog())
			.add(woodset.getWood(), woodset.getStrippedWood());

		this.valueLookupBuilder(BlockTags.LOGS)
			.addOptionalTag(tag);

		this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
			.addOptionalTag(tag);

		this.valueLookupBuilder(BloomBlockTags.JACARANDA_LOGS)
			.add(woodset.getLog(), woodset.getStrippedLog())
			.add(woodset.getWood(), woodset.getStrippedWood());

		this.valueLookupBuilder(BlockTags.LOGS)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

		this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
			.addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

		this.valueLookupBuilder(BlockTags.LEAVES)
			.add(woodset.getLeaves());

		this.valueLookupBuilder(BlockTags.PLANKS)
			.add(woodset.getPlanks());

		this.valueLookupBuilder(BlockTags.SIGNS)
			.add(woodset.getSign());

		this.valueLookupBuilder(BlockTags.ALL_HANGING_SIGNS)
			.add(woodset.getHangingSign());

		this.valueLookupBuilder(BlockTags.WOODEN_BUTTONS)
			.add(woodset.getButton());

		this.valueLookupBuilder(BlockTags.WOODEN_DOORS)
			.add(woodset.getDoor());

		this.valueLookupBuilder(BlockTags.WOODEN_FENCES)
			.add(woodset.getFence());

		this.valueLookupBuilder(BlockTags.FENCE_GATES)
			.add(woodset.getFenceGate());

		this.valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
			.add(woodset.getPressurePlate());

		this.valueLookupBuilder(BlockTags.WOODEN_SLABS)
			.add(woodset.getSlab());

		this.valueLookupBuilder(BlockTags.WOODEN_STAIRS)
			.add(woodset.getStairs());

		this.valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
			.add(woodset.getTrapDoor());
	}

    public void tagOres(StoneOresRegistry ores) {
        ores.getOresMap().forEach((type, block) -> {
            String name = type.name;
			if (!Objects.equals(name, "sapphire")) addTag(block, BlockTags.MINEABLE_WITH_PICKAXE);
            if (Objects.equals(name, "coal")) addTag(block, BlockTags.COAL_ORES);
            if (Objects.equals(name, "copper")) addTag(block, BlockTags.COPPER_ORES);
            if (Objects.equals(name, "iron")) addTag(block, BlockTags.IRON_ORES);
            if (Objects.equals(name, "redstone")) addTag(block, BlockTags.REDSTONE_ORES);
            if (Objects.equals(name, "gold")) addTag(block, BlockTags.GOLD_ORES);
            if (Objects.equals(name, "diamond")) addTag(block, BlockTags.DIAMOND_ORES);
            if (Objects.equals(name, "emerald")) addTag(block, BlockTags.EMERALD_ORES);
            if (Objects.equals(name, "lapis")) addTag(block, BlockTags.LAPIS_ORES);
            if (Objects.equals(name, "sapphire")) addTag(block, BloomBlockTags.SAPPHIRE_ORES);
        });
    }

    public void addTag(Block block, TagKey<Block> tag) {
        this.valueLookupBuilder(tag).add(block);
    }
}
