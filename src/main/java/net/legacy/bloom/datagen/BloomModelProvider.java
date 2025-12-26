package net.legacy.bloom.datagen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public final class BloomModelProvider extends FabricModelProvider {

	public BloomModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(@NotNull BlockModelGenerators generator) {
		generator.createPlantWithDefaultItem(BloomBlocks.HELLEBORE, BloomBlocks.POTTED_HELLEBORE, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.BROMELIAD, BloomBlocks.POTTED_BROMELIAD, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.PINK_ORCHID, BloomBlocks.POTTED_PINK_ORCHID, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createDoublePlantWithDefaultItem(BloomBlocks.BELLFLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createDoublePlantWithDefaultItem(BloomBlocks.HYDRANGEA, BlockModelGenerators.PlantType.NOT_TINTED);
/*		generator.createTrivialCube(ERBlocks.CRYSTALLINE_LAMP);
		generator.createTrivialCube(ERBlocks.CRYSTALLINE_BLOCK);
		generator.createTrivialCube(ERBlocks.END_IRON_ORE);
		generator.createTrivialCube(ERBlocks.MIRESTONE_IRON_ORE);
		generator.createTrivialCube(ERBlocks.RAW_CRYSTALLINE_BLOCK);
		generator.createDoor(ERBlocks.CHORUS_DOOR);
		generator.createTrapdoor(ERBlocks.CHORUS_TRAPDOOR);
        generator.createShelf(ERBlocks.CHORUS_SHELF, ERBlocks.STRIPPED_CHORUS_BLOCK);

		BlockModelGenerators.BlockFamilyProvider chorusFamily = generator.family(ERBlocks.CHORUS_PLANKS);
		chorusFamily.skipGeneratingModelsFor.add(ERBlocks.CHORUS_DOOR);
		chorusFamily.skipGeneratingModelsFor.add(ERBlocks.CHORUS_TRAPDOOR);
		chorusFamily.generateFor(ERBlocks.FAMILY_CHORUS);
		generator.woodProvider(ERBlocks.CHORUS_BLOCK).logWithHorizontal(ERBlocks.CHORUS_BLOCK);
		generator.woodProvider(ERBlocks.STRIPPED_CHORUS_BLOCK).logWithHorizontal(ERBlocks.STRIPPED_CHORUS_BLOCK);
		generator.createHangingSign(ERBlocks.STRIPPED_CHORUS_BLOCK, ERBlocks.CHORUS_HANGING_SIGN, ERBlocks.CHORUS_WALL_HANGING_SIGN);*/
	}

	@Override
	public void generateItemModels(@NotNull ItemModelGenerators generator) {
/*        generator.generateFlatItem(ERItems.CHORUS_RAFT, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(ERItems.CHORUS_CHEST_RAFT, ModelTemplates.FLAT_ITEM);*/
	}
}