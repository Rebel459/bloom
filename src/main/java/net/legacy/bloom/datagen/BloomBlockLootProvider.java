package net.legacy.bloom.datagen;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.legacy.bloom.block.SleepingBagBlock;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.util.StoneOresRegistry;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BedPart;
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
		this.dropSelf(BloomBlocks.HYACINTH);
		this.dropPottedContents(BloomBlocks.POTTED_HYACINTH);
		this.dropSelf(BloomBlocks.QUEENCUP);
		this.dropPottedContents(BloomBlocks.POTTED_QUEENCUP);
		this.dropSelf(BloomBlocks.BELLFLOWER);
		this.dropSelf(BloomBlocks.HYDRANGEA);
		this.dropSelf(BloomBlocks.SUCCULENT);

        woodDrops(BloomBlocks.JACARANDA);

        this.dropSelf(BloomBlocks.DOLERITE);

		this.sleepingBagDrops(BloomBlocks.WHITE_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.ORANGE_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.MAGENTA_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.YELLOW_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.LIME_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.PINK_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.GRAY_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.CYAN_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.PURPLE_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.BLUE_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.BROWN_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.GREEN_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.RED_SLEEPING_BAG);
		this.sleepingBagDrops(BloomBlocks.BLACK_SLEEPING_BAG);

        this.oreDrops(BloomBlocks.TUFF_ORES);
        this.oreDrops(BloomBlocks.GRANITE_ORES);
        this.oreDrops(BloomBlocks.ANDESITE_ORES);
        this.oreDrops(BloomBlocks.DIORITE_ORES);
        this.oreDrops(BloomBlocks.DOLERITE_ORES);
        this.oreDrops(BloomBlocks.SANDSTONE_ORES);
        this.oreDrops(BloomBlocks.RED_SANDSTONE_ORES);
	}

	public void sleepingBagDrops(Block sleepingBag) {
		this.add(sleepingBag, block -> this.createSinglePropConditionTable(block, SleepingBagBlock.PART, SleepingBagBlock.Part.HEAD));
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

	public void woodDrops(WoodsetRegistry woodset){
		this.dropSelf(woodset.getPlanks());
		this.dropSelf(woodset.getStairs());
		this.createSlabItemTable(woodset.getSlab());
		this.dropSelf(woodset.getFence());
		this.dropSelf(woodset.getFenceGate());
		this.dropSelf(woodset.getButton());
		this.dropSelf(woodset.getPressurePlate());
		this.dropSelf(woodset.getLog());

		if (woodset.getWoodPreset() == WoodsetRegistry.WoodPreset.BAMBOO) {
			this.dropSelf(woodset.getMosaic());
			this.dropSelf(woodset.getMosaicStairs());
			this.createSlabItemTable(woodset.getMosaicSlab());
		}
		else{
			this.dropSelf(woodset.getWood());
			this.dropSelf(woodset.getStrippedLog());
			this.dropSelf(woodset.getStrippedWood());
		}

		this.dropSelf(woodset.getTrapDoor());
		this.createDoorTable(woodset.getDoor());
		if (woodset.isOverworldTreeWood()){
			this.createLeavesDrops(woodset.getLeaves(), woodset.getLeaves(), 0.05f, 0.0625f, 0.025f, 0.083333336f, 0.1f);
		}
		this.dropSelf(woodset.getSign());
		this.dropSelf(woodset.getHangingSign());

		this.dropSelf(woodset.getShelf());
	}
}
