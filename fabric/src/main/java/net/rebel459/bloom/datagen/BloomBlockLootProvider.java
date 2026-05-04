package net.rebel459.bloom.datagen;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.rebel459.bloom.block.SleepingBagBlock;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.bloom.util.WoodsetRegistry;

public final class BloomBlockLootProvider extends FabricBlockLootSubProvider {

	public BloomBlockLootProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registries) {
		super(dataOutput, registries);
	}

	@Override
	public void generate() {
		this.dropSelf(BloomBlocks.HELLEBORE.get());
		this.dropPottedContents(BloomBlocks.POTTED_HELLEBORE.get());
		this.dropSelf(BloomBlocks.BROMELIAD.get());
		this.dropPottedContents(BloomBlocks.POTTED_BROMELIAD.get());
		this.dropSelf(BloomBlocks.PINK_ORCHID.get());
		this.dropPottedContents(BloomBlocks.POTTED_PINK_ORCHID.get());
		this.dropSelf(BloomBlocks.CALLA_LILY.get());
		this.dropPottedContents(BloomBlocks.POTTED_CALLA_LILY.get());
		this.dropSelf(BloomBlocks.DIANTHUS.get());
		this.dropPottedContents(BloomBlocks.POTTED_DIANTHUS.get());
		this.dropSelf(BloomBlocks.GOLDENROD.get());
		this.dropPottedContents(BloomBlocks.POTTED_GOLDENROD.get());
		this.dropSelf(BloomBlocks.ORANGE_DAISY.get());
		this.dropPottedContents(BloomBlocks.POTTED_ORANGE_DAISY.get());
		this.dropSelf(BloomBlocks.SCILLA.get());
		this.dropPottedContents(BloomBlocks.POTTED_SCILLA.get());
		this.dropSelf(BloomBlocks.HYACINTH.get());
		this.dropPottedContents(BloomBlocks.POTTED_HYACINTH.get());
		this.dropSelf(BloomBlocks.QUEENCUP.get());
		this.dropPottedContents(BloomBlocks.POTTED_QUEENCUP.get());

		this.dropSelf(BloomBlocks.SUCCULENT.get());

		this.tallFoliageDrops(BloomBlocks.BELLFLOWER.get());
		this.tallFoliageDrops(BloomBlocks.HYDRANGEA.get());
		this.tallFoliageDrops(BloomBlocks.REEDS.get());

		this.cropDrops(BloomBlocks.COTTON.get(), 7, BloomItems.COTTON.get(), BloomItems.COTTON_SEEDS.get());

		WoodsetRegistry.WOODSETS.forEach(this::woodDrops);

		this.add(BloomBlocks.JACARANDA.getLeaves().get(), block -> this.createLeavesDrops(block, BloomBlocks.JACARANDA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(BloomBlocks.GOLDEN_BIRCH.getLeaves().get(), block -> this.createLeavesDrops(block, BloomBlocks.GOLDEN_BIRCH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(BloomBlocks.PINE.getLeaves().get(), block -> this.createLeavesDrops(block, BloomBlocks.PINE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.dropSelf(BloomBlocks.JACARANDA_SAPLING.get());
		this.dropSelf(BloomBlocks.GOLDEN_BIRCH_SAPLING.get());
		this.dropSelf(BloomBlocks.PINE_SAPLING.get());

		this.sleepingBagDrops(BloomBlocks.WHITE_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.ORANGE_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.MAGENTA_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.YELLOW_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.LIME_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.PINK_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.GRAY_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.CYAN_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.PURPLE_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.BLUE_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.BROWN_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.GREEN_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.RED_SLEEPING_BAG.get());
		this.sleepingBagDrops(BloomBlocks.BLACK_SLEEPING_BAG.get());

		this.dropSelf(BloomBlocks.WHITE_RUG.get());
		this.dropSelf(BloomBlocks.ORANGE_RUG.get());
		this.dropSelf(BloomBlocks.MAGENTA_RUG.get());
		this.dropSelf(BloomBlocks.LIGHT_BLUE_RUG.get());
		this.dropSelf(BloomBlocks.YELLOW_RUG.get());
		this.dropSelf(BloomBlocks.LIME_RUG.get());
		this.dropSelf(BloomBlocks.PINK_RUG.get());
		this.dropSelf(BloomBlocks.GRAY_RUG.get());
		this.dropSelf(BloomBlocks.LIGHT_GRAY_RUG.get());
		this.dropSelf(BloomBlocks.CYAN_RUG.get());
		this.dropSelf(BloomBlocks.PURPLE_RUG.get());
		this.dropSelf(BloomBlocks.BLUE_RUG.get());
		this.dropSelf(BloomBlocks.BROWN_RUG.get());
		this.dropSelf(BloomBlocks.GREEN_RUG.get());
		this.dropSelf(BloomBlocks.RED_RUG.get());
		this.dropSelf(BloomBlocks.BLACK_RUG.get());

		this.dropSelf(BloomBlocks.DOLERITE.get());
		this.dropSelf(BloomBlocks.POLISHED_DOLERITE.get());
		this.dropSelf(BloomBlocks.POLISHED_DOLERITE_STAIRS.get());
		this.dropSelf(BloomBlocks.POLISHED_DOLERITE_SLAB.get());
		this.dropSelf(BloomBlocks.POLISHED_DOLERITE_WALL.get());
		this.dropSelf(BloomBlocks.DOLERITE_BRICKS.get());
		this.dropSelf(BloomBlocks.DOLERITE_BRICK_STAIRS.get());
		this.dropSelf(BloomBlocks.DOLERITE_BRICK_SLAB.get());
		this.dropSelf(BloomBlocks.DOLERITE_BRICK_WALL.get());
		this.dropSelf(BloomBlocks.DOLERITE_TILES.get());
		this.dropSelf(BloomBlocks.DOLERITE_TILE_STAIRS.get());
		this.dropSelf(BloomBlocks.DOLERITE_TILE_SLAB.get());
		this.dropSelf(BloomBlocks.DOLERITE_TILE_WALL.get());

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
            this.add(block.get(), this.createSilkTouchDispatchTable(block.get(), NestedLootTable.lootTableReference(baseBlock.getLootTable().get())));
        });
    }

    public static Block getDeepslateOre(String material) {
        String id = "minecraft";
        if (Objects.equals(material, "sapphire")) id = "legacies_and_legends";
        return BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath(id, "deepslate_" + material + "_ore"));
    }

	public void woodDrops(WoodsetRegistry woodset){
		this.dropSelf(woodset.getPlanks().get());
		this.dropSelf(woodset.getStairs().get());
		this.add(woodset.getSlab().get(), this::createSlabItemTable);
		this.dropSelf(woodset.getFence().get());
		this.dropSelf(woodset.getFenceGate().get());
		this.dropSelf(woodset.getButton().get());
		this.dropSelf(woodset.getPressurePlate().get());
		this.dropSelf(woodset.getLog().get());

		if (woodset.getWoodPreset() == WoodsetRegistry.WoodPreset.BAMBOO) {
			this.dropSelf(woodset.getMosaic().get());
			this.dropSelf(woodset.getMosaicStairs().get());
			this.add(woodset.getMosaicSlab().get(), this::createSlabItemTable);
		}
		else{
			this.dropSelf(woodset.getWood().get());
			this.dropSelf(woodset.getStrippedLog().get());
			this.dropSelf(woodset.getStrippedWood().get());
		}

		this.dropSelf(woodset.getTrapDoor().get());
		this.add(woodset.getDoor().get(), this::createDoorTable);
		this.dropSelf(woodset.getSign().get());
		this.dropSelf(woodset.getHangingSign().get());

		this.dropSelf(woodset.getShelf().get());
	}
}
