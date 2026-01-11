package net.legacy.bloom.datagen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.util.StoneOresRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

@Environment(EnvType.CLIENT)
public final class BloomModelProvider extends FabricModelProvider {

	public BloomModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generator) {
		generator.createPlantWithDefaultItem(BloomBlocks.HELLEBORE, BloomBlocks.POTTED_HELLEBORE, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.BROMELIAD, BloomBlocks.POTTED_BROMELIAD, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.PINK_ORCHID, BloomBlocks.POTTED_PINK_ORCHID, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.CALLA_LILY, BloomBlocks.POTTED_CALLA_LILY, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.DIANTHUS, BloomBlocks.POTTED_DIANTHUS, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.GOLDENROD, BloomBlocks.POTTED_GOLDENROD, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.ORANGE_DAISY, BloomBlocks.POTTED_ORANGE_DAISY, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.SCILLA, BloomBlocks.POTTED_SCILLA, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.HYACINTH, BloomBlocks.POTTED_HYACINTH, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.QUEENCUP, BloomBlocks.POTTED_QUEENCUP, BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createDoublePlantWithDefaultItem(BloomBlocks.BELLFLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.createDoublePlantWithDefaultItem(BloomBlocks.HYDRANGEA, BlockModelGenerators.PlantType.NOT_TINTED);
        generator.createDoublePlantWithDefaultItem(BloomBlocks.REEDS, BlockModelGenerators.PlantType.NOT_TINTED);
        this.createSinglePlant(BloomBlocks.SUCCULENT, BlockModelGenerators.PlantType.NOT_TINTED, generator);

		BloomBlocks.JACARANDA.fullWoodset(generator);
		generator.createPlantWithDefaultItem(BloomBlocks.JACARANDA_SAPLING, BloomBlocks.POTTED_JACARANDA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        generator.createTrivialCube(BloomBlocks.DOLERITE);

        oreModels(BloomBlocks.TUFF_ORES, generator);
        oreModels(BloomBlocks.GRANITE_ORES, generator);
        oreModels(BloomBlocks.ANDESITE_ORES, generator);
        oreModels(BloomBlocks.DIORITE_ORES, generator);
        oreModels(BloomBlocks.DOLERITE_ORES, generator);
        oreModels(BloomBlocks.SANDSTONE_ORES, generator);
        oreModels(BloomBlocks.RED_SANDSTONE_ORES, generator);

		this.rug(BloomBlocks.WHITE_RUG, generator);
		this.rug(BloomBlocks.ORANGE_RUG, generator);
		this.rug(BloomBlocks.MAGENTA_RUG, generator);
		this.rug(BloomBlocks.LIGHT_BLUE_RUG, generator);
		this.rug(BloomBlocks.YELLOW_RUG, generator);
		this.rug(BloomBlocks.LIME_RUG, generator);
		this.rug(BloomBlocks.PINK_RUG, generator);
		this.rug(BloomBlocks.GRAY_RUG, generator);
		this.rug(BloomBlocks.LIGHT_GRAY_RUG, generator);
		this.rug(BloomBlocks.CYAN_RUG, generator);
		this.rug(BloomBlocks.PURPLE_RUG, generator);
		this.rug(BloomBlocks.BLUE_RUG, generator);
		this.rug(BloomBlocks.BROWN_RUG, generator);
		this.rug(BloomBlocks.GREEN_RUG, generator);
		this.rug(BloomBlocks.RED_RUG, generator);
		this.rug(BloomBlocks.BLACK_RUG, generator);

		generator.createParticleOnlyBlock(BloomBlocks.WHITE_SLEEPING_BAG, Blocks.WHITE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.ORANGE_SLEEPING_BAG, Blocks.ORANGE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.MAGENTA_SLEEPING_BAG, Blocks.MAGENTA_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG, Blocks.LIGHT_BLUE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.YELLOW_SLEEPING_BAG, Blocks.YELLOW_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.LIME_SLEEPING_BAG, Blocks.LIME_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.PINK_SLEEPING_BAG, Blocks.PINK_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.GRAY_SLEEPING_BAG, Blocks.GRAY_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG, Blocks.LIGHT_GRAY_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.CYAN_SLEEPING_BAG, Blocks.CYAN_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.PURPLE_SLEEPING_BAG, Blocks.PURPLE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.BLUE_SLEEPING_BAG, Blocks.BLUE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.BROWN_SLEEPING_BAG, Blocks.BROWN_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.GREEN_SLEEPING_BAG, Blocks.GREEN_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.RED_SLEEPING_BAG, Blocks.RED_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.BLACK_SLEEPING_BAG, Blocks.BLACK_WOOL);
	}

	public void rug(Block block, BlockModelGenerators generator) {
		MultiVariant multiVariant = BlockModelGenerators.plainVariant(TexturedModel.CARPET.get(block).create(block, generator.modelOutput));
		generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, multiVariant));
	}

    public void oreModels(StoneOresRegistry ores, BlockModelGenerators generator) {
        ores.getOresMap().forEach((type, ore) -> {
            StoneOresRegistry.STONE_MODELS_MAP.getOrDefault(ores.getBaseStone(), (generators, b) -> {
                generators.createTrivialCube(ore);
                return true;
            }).apply(generator, ore);
        });
    }

	@Override
	public void generateItemModels(ItemModelGenerators generator) {
		generator.generateFlatItem(BloomItems.WHITE_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.ORANGE_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.MAGENTA_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.LIGHT_BLUE_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.YELLOW_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.LIME_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.PINK_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.GRAY_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.LIGHT_GRAY_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.CYAN_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.PURPLE_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.BLUE_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.BROWN_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.GREEN_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.RED_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.BLACK_SLEEPING_BAG, ModelTemplates.FLAT_ITEM);
	}

	public void createSinglePlant(Block block, BlockModelGenerators.PlantType type, BlockModelGenerators generator) {
		generator.registerSimpleItemModel(block.asItem(), type.createItemModel(generator, block));
		this.createPlant(block, type, generator);
	}

	public void createPlant(Block block, BlockModelGenerators.PlantType type, BlockModelGenerators generator) {
		generator.createCrossBlock(block, type);
	}
}
