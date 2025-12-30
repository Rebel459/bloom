package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.data.StoneOresRegistry;
import net.legacy.bloom.tag.BloomBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public final class BloomBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public BloomBlockTagProvider(@NotNull FabricDataOutput output, @NotNull CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void addTags(@NotNull HolderLookup.Provider arg) {
        this.valueLookupBuilder(BloomBlockTags.ARID_VEGETATION_MAY_PLACE_ON)
                .addOptionalTag(BlockTags.SAND)
                .addOptionalTag(BlockTags.DIRT)
                .add(Blocks.FARMLAND);

        this.valueLookupBuilder(BloomBlockTags.SUBMERGED_VEGETATION_MAY_PLACE_ON)
                .addOptionalTag(BlockTags.SAND)
                .addOptionalTag(BlockTags.DIRT)
                .add(Blocks.FARMLAND)
                .add(Blocks.CLAY);

		this.valueLookupBuilder(BlockTags.SMALL_FLOWERS)
				.add(BloomBlocks.PINK_ORCHID)
				.add(BloomBlocks.GOLDENROD)
				.add(BloomBlocks.CALLA_LILY)
				.add(BloomBlocks.ORANGE_DAISY);
		this.valueLookupBuilder(BlockTags.FLOWERS)
				.add(BloomBlocks.DIANTHUS)
				.add(BloomBlocks.GOLDENROD)
				.add(BloomBlocks.CALLA_LILY)
				.add(BloomBlocks.ORANGE_DAISY)
				.add(BloomBlocks.SCILLA)
				.add(BloomBlocks.BELLFLOWER)
				.add(BloomBlocks.PINK_ORCHID)
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
				.add(BloomBlocks.POTTED_HELLEBORE);

        this.valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(BloomBlocks.JACARANDA_LOG);

        this.valueLookupBuilder(BloomBlockTags.JACARANDA_LOGS)
                .add(BloomBlocks.JACARANDA_LOG, BloomBlocks.STRIPPED_JACARANDA_LOG)
                .add(BloomBlocks.JACARANDA_WOOD, BloomBlocks.STRIPPED_JACARANDA_WOOD);

        this.valueLookupBuilder(BlockTags.LOGS)
                .addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

        this.valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .addOptionalTag(BloomBlockTags.JACARANDA_LOGS);

        this.valueLookupBuilder(BlockTags.LEAVES)
                .add(BloomBlocks.JACARANDA_LEAVES);

        this.valueLookupBuilder(BlockTags.PLANKS)
                .add(BloomBlocks.JACARANDA_PLANKS);

        this.valueLookupBuilder(BlockTags.SAPLINGS)
                .add(BloomBlocks.JACARANDA_SAPLING);

        this.valueLookupBuilder(BlockTags.STANDING_SIGNS)
                .add(BloomBlocks.JACARANDA_SIGN);

        this.valueLookupBuilder(BlockTags.WALL_SIGNS)
                .add(BloomBlocks.JACARANDA_WALL_SIGN);

        this.valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(BloomBlocks.JACARANDA_HANGING_SIGN);

        this.valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(BloomBlocks.JACARANDA_WALL_HANGING_SIGN);

        this.valueLookupBuilder(BlockTags.WOODEN_BUTTONS)
                .add(BloomBlocks.JACARANDA_BUTTON);

        this.valueLookupBuilder(BlockTags.WOODEN_DOORS)
                .add(BloomBlocks.JACARANDA_DOOR);

        this.valueLookupBuilder(BlockTags.WOODEN_FENCES)
                .add(BloomBlocks.JACARANDA_FENCE);

        this.valueLookupBuilder(BlockTags.FENCE_GATES)
                .add(BloomBlocks.JACARANDA_FENCE_GATE);

        this.valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(BloomBlocks.JACARANDA_PRESSURE_PLATE);

        this.valueLookupBuilder(BlockTags.WOODEN_SLABS)
                .add(BloomBlocks.JACARANDA_SLAB);

        this.valueLookupBuilder(BlockTags.WOODEN_STAIRS)
                .add(BloomBlocks.JACARANDA_STAIRS);

        this.valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(BloomBlocks.JACARANDA_TRAPDOOR);

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .addAll(BloomBlocks.SANDSTONE_ORES.getOresMap().values());

        tagOres(BloomBlocks.TUFF_ORES);
        tagOres(BloomBlocks.GRANITE_ORES);
        tagOres(BloomBlocks.ANDESITE_ORES);
        tagOres(BloomBlocks.DIORITE_ORES);
        tagOres(BloomBlocks.SANDSTONE_ORES);
	}

    public void tagOres(StoneOresRegistry ores) {
        ores.getOresMap().forEach((oreType, block) -> {
            addTag(block, BlockTags.MINEABLE_WITH_PICKAXE);
            String name = oreType.name;
            if (Objects.equals(name, "coal")) addTag(block, BlockTags.COAL_ORES);
            if (Objects.equals(name, "copper")) addTag(block, BlockTags.COPPER_ORES);
            if (Objects.equals(name, "iron")) addTag(block, BlockTags.IRON_ORES);
            if (Objects.equals(name, "redstone")) addTag(block, BlockTags.REDSTONE_ORES);
            if (Objects.equals(name, "gold")) addTag(block, BlockTags.GOLD_ORES);
            if (Objects.equals(name, "diamond")) addTag(block, BlockTags.DIAMOND_ORES);
            if (Objects.equals(name, "emerald")) addTag(block, BlockTags.EMERALD_ORES);
            if (Objects.equals(name, "lapis")) addTag(block, BlockTags.LAPIS_ORES);
            if (Objects.equals(name, "sapphire")) addTag(block, BloomBlockTags.SAPPHIRE_ORES, true);
        });
    }

    public void addTag(Block block, TagKey<Block> tag) {
        addTag(block, tag, false);
    }

    public void addTag(Block block, TagKey<Block> tag, boolean optional) {
        if (optional) {
            this.valueLookupBuilder(tag)
                    .addOptional(block);
        }
        else {
            this.valueLookupBuilder(tag)
                    .add(block);
        }
    }
}