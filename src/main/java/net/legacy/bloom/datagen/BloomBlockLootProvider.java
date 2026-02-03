package net.legacy.bloom.datagen;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.legacy.bloom.block.SleepingBagBlock;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.util.StoneOresRegistry;
import net.legacy.bloom.util.WoodsetRegistry;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

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

		this.dropSelf(BloomBlocks.SUCCULENT);

		this.tallFoliageDrops(BloomBlocks.BELLFLOWER);
		this.tallFoliageDrops(BloomBlocks.HYDRANGEA);
		this.tallFoliageDrops(BloomBlocks.REEDS);

		this.cropDrops(BloomBlocks.COTTON, 7, BloomItems.COTTON, BloomItems.COTTON_SEEDS);

		WoodsetRegistry.WOODSETS.forEach(this::woodDrops);

		this.add(BloomBlocks.JACARANDA.getLeaves(), block -> this.createLeavesDrops(block, BloomBlocks.JACARANDA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(BloomBlocks.GOLDEN_BIRCH.getLeaves(), block -> this.createLeavesDrops(block, BloomBlocks.GOLDEN_BIRCH_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(BloomBlocks.PINE.getLeaves(), block -> this.createLeavesDrops(block, BloomBlocks.PINE_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));

		this.dropSelf(BloomBlocks.JACARANDA_SAPLING);
		this.dropSelf(BloomBlocks.GOLDEN_BIRCH_SAPLING);
		this.dropSelf(BloomBlocks.PINE_SAPLING);

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

		this.dropSelf(BloomBlocks.WHITE_RUG);
		this.dropSelf(BloomBlocks.ORANGE_RUG);
		this.dropSelf(BloomBlocks.MAGENTA_RUG);
		this.dropSelf(BloomBlocks.LIGHT_BLUE_RUG);
		this.dropSelf(BloomBlocks.YELLOW_RUG);
		this.dropSelf(BloomBlocks.LIME_RUG);
		this.dropSelf(BloomBlocks.PINK_RUG);
		this.dropSelf(BloomBlocks.GRAY_RUG);
		this.dropSelf(BloomBlocks.LIGHT_GRAY_RUG);
		this.dropSelf(BloomBlocks.CYAN_RUG);
		this.dropSelf(BloomBlocks.PURPLE_RUG);
		this.dropSelf(BloomBlocks.BLUE_RUG);
		this.dropSelf(BloomBlocks.BROWN_RUG);
		this.dropSelf(BloomBlocks.GREEN_RUG);
		this.dropSelf(BloomBlocks.RED_RUG);
		this.dropSelf(BloomBlocks.BLACK_RUG);

		this.dropSelf(BloomBlocks.DOLERITE);

		StoneOresRegistry.ALL_REGISTRIES.forEach(this::oreDrops);
	}

	public void cropDrops(Block crop, int age, Item drop, Item seeds) {
		LootItemCondition.Builder condition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(crop).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, age));
		this.add(crop, this.createCropDrops(crop, drop, seeds, condition));
	}

	public void tallFoliageDrops(Block tallBlock) {
		this.add(tallBlock, block -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
	}

	public void sleepingBagDrops(Block sleepingBag) {
		this.add(sleepingBag, block -> this.createSinglePropConditionTable(block, SleepingBagBlock.PART, BedPart.HEAD));
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
		this.add(woodset.getSlab(), this::createSlabItemTable);
		this.dropSelf(woodset.getFence());
		this.dropSelf(woodset.getFenceGate());
		this.dropSelf(woodset.getButton());
		this.dropSelf(woodset.getPressurePlate());
		this.dropSelf(woodset.getLog());

		if (woodset.getWoodPreset() == WoodsetRegistry.WoodPreset.BAMBOO) {
			this.dropSelf(woodset.getMosaic());
			this.dropSelf(woodset.getMosaicStairs());
			this.add(woodset.getMosaicSlab(), this::createSlabItemTable);
		}
		else{
			this.dropSelf(woodset.getWood());
			this.dropSelf(woodset.getStrippedLog());
			this.dropSelf(woodset.getStrippedWood());
		}

		this.dropSelf(woodset.getTrapDoor());
		this.add(woodset.getDoor(), this::createDoorTable);
		this.dropSelf(woodset.getSign());
		this.dropSelf(woodset.getHangingSign());

		this.dropSelf(woodset.getShelf());
	}
}
