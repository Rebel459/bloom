package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class BloomBlockLootProvider extends FabricBlockLootTableProvider {

	public BloomBlockLootProvider(@NotNull FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registries) {
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

		//this.add(ERBlocks.CHORUS_MOSAIC_SLAB, this::createSlabItemTable);
	}

	public LootTable.@NotNull Builder createMultifaceBlockDrops(Block drop) {
		return LootTable.lootTable()
			.withPool(
				LootPool.lootPool()
					.add(
						this.applyExplosionDecay(
							drop,
							LootItem.lootTableItem(drop)
								.apply(
									Direction.values(),
									direction -> SetItemCountFunction.setCount(ConstantValue.exactly(1F), true)
										.when(
											LootItemBlockStatePropertyCondition.hasBlockStateProperties(drop)
												.setProperties(
													StatePropertiesPredicate.Builder.properties().hasProperty(MultifaceBlock.getFaceProperty(direction), true)
												)
										)
								)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(-1F), true))
						)
					)
			);
	}
}