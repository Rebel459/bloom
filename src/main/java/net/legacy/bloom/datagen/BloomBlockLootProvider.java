package net.legacy.bloom.datagen;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.util.StoneOresRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public final class BloomBlockLootProvider extends FabricBlockLootTableProvider {

	public BloomBlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registries) {
		super(dataOutput, registries);
	}

	@Override
	public void generate() {
		this.dropSelf(BloomBlocks.HELLEBORE);
		this.dropPottedContents(BloomBlocks.POTTED_HELLEBORE);
		this.dropSelf(BloomBlocks.BROMELIAD);
		this.dropPottedContents(BloomBlocks.POTTED_BROMELIAD);
		this.dropSelf(BloomBlocks.PINK_ORCHID);
		this.dropPottedContents(BloomBlocks.POTTED_PINK_ORCHID);
		this.dropSelf(BloomBlocks.CALLA_LILY);
		this.dropPottedContents(BloomBlocks.POTTED_CALLA_LILY);
		this.dropSelf(BloomBlocks.DIANTHUS);
		this.dropPottedContents(BloomBlocks.POTTED_DIANTHUS);
		this.dropSelf(BloomBlocks.GOLDENROD);
		this.dropPottedContents(BloomBlocks.POTTED_GOLDENROD);
		this.dropSelf(BloomBlocks.ORANGE_DAISY);
		this.dropPottedContents(BloomBlocks.POTTED_ORANGE_DAISY);
		this.dropSelf(BloomBlocks.SCILLA);
        this.dropPottedContents(BloomBlocks.POTTED_SCILLA);
		this.dropSelf(BloomBlocks.BELLFLOWER);
		this.dropSelf(BloomBlocks.HYDRANGEA);
		this.dropSelf(BloomBlocks.SUCCULENT);

        this.dropSelf(BloomBlocks.JACARANDA_LOG);
        this.dropSelf(BloomBlocks.STRIPPED_JACARANDA_LOG);
        this.dropSelf(BloomBlocks.JACARANDA_WOOD);
        this.dropSelf(BloomBlocks.STRIPPED_JACARANDA_WOOD);
        this.dropSelf(BloomBlocks.JACARANDA_PLANKS);
        this.dropSelf(BloomBlocks.JACARANDA_BUTTON);
        this.dropSelf(BloomBlocks.JACARANDA_PRESSURE_PLATE);
        this.dropSelf(BloomBlocks.JACARANDA_TRAPDOOR);
        this.dropSelf(BloomBlocks.JACARANDA_STAIRS);
        this.add(BloomBlocks.JACARANDA_SLAB, this::createSlabItemTable);
        this.dropSelf(BloomBlocks.JACARANDA_FENCE);
        this.dropSelf(BloomBlocks.JACARANDA_FENCE_GATE);
        this.add(BloomBlocks.JACARANDA_DOOR, this::createDoorTable);
        this.dropSelf(BloomBlocks.JACARANDA_SIGN);
        this.dropSelf(BloomBlocks.JACARANDA_HANGING_SIGN);
        this.dropSelf(BloomBlocks.JACARANDA_SHELF);
        this.dropSelf(BloomBlocks.JACARANDA_SAPLING);
        this.add(BloomBlocks.JACARANDA_LEAVES, block -> this.createLeavesDrops(block, BloomBlocks.JACARANDA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(BloomBlocks.DOLERITE);

        this.oreDrops(BloomBlocks.TUFF_ORES);
        this.oreDrops(BloomBlocks.GRANITE_ORES);
        this.oreDrops(BloomBlocks.ANDESITE_ORES);
        this.oreDrops(BloomBlocks.DIORITE_ORES);
        this.oreDrops(BloomBlocks.DOLERITE_ORES);
        this.oreDrops(BloomBlocks.SANDSTONE_ORES);
        this.oreDrops(BloomBlocks.RED_SANDSTONE_ORES);

        //this.add(ERBlocks.CHORUS_MOSAIC_SLAB, this::createSlabItemTable);
	}

    public void oreDrops(StoneOresRegistry ores) {
        ores.getOresMap().forEach((type, block) -> {
            Block baseBlock = type.baseBlock;
            if (ores.deep) baseBlock = getDeepslateOre(type.name);
            this.add(block, this.createSilkTouchDispatchTable(block, NestedLootTable.lootTableReference(baseBlock.getLootTable().get())));
        });
    }

    public static Block getDeepslateOre(String material) {
        String id = "minecraft";
        if (Objects.equals(material, "sapphire")) id = "legacies_and_legends";
        return BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath(id, "deepslate_" + material + "_ore"));
    }
}
