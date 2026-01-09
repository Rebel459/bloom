package net.legacy.bloom.datagen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.client.SleepingBagSpecialRenderer;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.util.StoneOresRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.BlockModelDefinition;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.special.BedSpecialRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.Optional;

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

		generator.createParticleOnlyBlock(BloomBlocks.WHITE_SLEEPING_BAG, Blocks.WHITE_WOOL);

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
/*        generator.generateFlatItem(ERItems.CHORUS_RAFT, ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(ERItems.CHORUS_CHEST_RAFT, ModelTemplates.FLAT_ITEM);*/
	}

	public void createSinglePlant(Block block, BlockModelGenerators.PlantType type, BlockModelGenerators generator) {
		generator.registerSimpleItemModel(block.asItem(), type.createItemModel(generator, block));
		this.createPlant(block, type, generator);
	}

	public void createPlant(Block block, BlockModelGenerators.PlantType type, BlockModelGenerators generator) {
		generator.createCrossBlock(block, type);
	}
}
