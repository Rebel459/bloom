package net.rebel459.bloom.datagen;

import java.util.Map;
import java.util.function.BiFunction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.bloom.util.WoodsetRegistry;

@Environment(EnvType.CLIENT)
public final class BloomModelProvider extends FabricModelProvider {

	public BloomModelProvider(FabricPackOutput output) {
		super(output);
	}

	public static final BlockFamily FAMILY_POLISHED_DOLERITE = BlockFamilies.familyBuilder(BloomBlocks.POLISHED_DOLERITE.get())
		.stairs(BloomBlocks.POLISHED_DOLERITE_STAIRS.get())
		.slab(BloomBlocks.POLISHED_DOLERITE_SLAB.get())
		.wall(BloomBlocks.POLISHED_DOLERITE_WALL.get())
		.getFamily();

	public static final BlockFamily FAMILY_DOLERITE_BRICKS = BlockFamilies.familyBuilder(BloomBlocks.DOLERITE_BRICKS.get())
		.stairs(BloomBlocks.DOLERITE_BRICK_STAIRS.get())
		.slab(BloomBlocks.DOLERITE_BRICK_SLAB.get())
		.wall(BloomBlocks.DOLERITE_BRICK_WALL.get())
		.getFamily();

	public static final BlockFamily FAMILY_DOLERITE_TILES = BlockFamilies.familyBuilder(BloomBlocks.DOLERITE_TILES.get())
		.stairs(BloomBlocks.DOLERITE_TILE_STAIRS.get())
		.slab(BloomBlocks.DOLERITE_TILE_SLAB.get())
		.wall(BloomBlocks.DOLERITE_TILE_WALL.get())
		.getFamily();

	@Override
	public void generateBlockStateModels(BlockModelGenerators generator) {
		generator.createPlantWithDefaultItem(BloomBlocks.HELLEBORE.get(), BloomBlocks.POTTED_HELLEBORE.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.BROMELIAD.get(), BloomBlocks.POTTED_BROMELIAD.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.PINK_ORCHID.get(), BloomBlocks.POTTED_PINK_ORCHID.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.CALLA_LILY.get(), BloomBlocks.POTTED_CALLA_LILY.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.DIANTHUS.get(), BloomBlocks.POTTED_DIANTHUS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.GOLDENROD.get(), BloomBlocks.POTTED_GOLDENROD.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.ORANGE_DAISY.get(), BloomBlocks.POTTED_ORANGE_DAISY.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.SCILLA.get(), BloomBlocks.POTTED_SCILLA.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.HYACINTH.get(), BloomBlocks.POTTED_HYACINTH.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.QUEENCUP.get(), BloomBlocks.POTTED_QUEENCUP.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createDoublePlantWithDefaultItem(BloomBlocks.BELLFLOWER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        generator.createDoublePlantWithDefaultItem(BloomBlocks.HYDRANGEA.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        generator.createDoublePlantWithDefaultItem(BloomBlocks.REEDS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createSinglePlant(BloomBlocks.SUCCULENT.get(), BlockModelGenerators.PlantType.NOT_TINTED, generator);

		WoodsetRegistry.WOODSETS.forEach(woodset -> {
			fullWoodset(woodset, generator);
		});
		generator.createPlantWithDefaultItem(BloomBlocks.JACARANDA_SAPLING.get(), BloomBlocks.POTTED_JACARANDA_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.GOLDEN_BIRCH_SAPLING.get(), BloomBlocks.POTTED_GOLDEN_BIRCH_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		generator.createPlantWithDefaultItem(BloomBlocks.PINE_SAPLING.get(), BloomBlocks.POTTED_PINE_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);

		generator.createTrivialCube(BloomBlocks.DOLERITE.get());

		generator.family(BloomBlocks.POLISHED_DOLERITE.get()).generateFor(FAMILY_POLISHED_DOLERITE);
		generator.family(BloomBlocks.DOLERITE_BRICKS.get()).generateFor(FAMILY_DOLERITE_BRICKS);
		generator.family(BloomBlocks.DOLERITE_TILES.get()).generateFor(FAMILY_DOLERITE_TILES);

        oreModels(BloomBlocks.TUFF_ORES, generator);
        oreModels(BloomBlocks.GRANITE_ORES, generator);
        oreModels(BloomBlocks.ANDESITE_ORES, generator);
        oreModels(BloomBlocks.DIORITE_ORES, generator);
        oreModels(BloomBlocks.DOLERITE_ORES, generator);
        oreModels(BloomBlocks.SANDSTONE_ORES, generator);
        oreModels(BloomBlocks.RED_SANDSTONE_ORES, generator);

		generator.createCropBlock(BloomBlocks.COTTON.get(), BlockStateProperties.AGE_7, 0, 1, 2, 3 , 4, 5, 5, 6);
		this.createSinglePlant(BloomBlocks.WILD_COTTON.get(), BlockModelGenerators.PlantType.NOT_TINTED, generator);

		this.rug(BloomBlocks.WHITE_RUG.get(), generator);
		this.rug(BloomBlocks.ORANGE_RUG.get(), generator);
		this.rug(BloomBlocks.MAGENTA_RUG.get(), generator);
		this.rug(BloomBlocks.LIGHT_BLUE_RUG.get(), generator);
		this.rug(BloomBlocks.YELLOW_RUG.get(), generator);
		this.rug(BloomBlocks.LIME_RUG.get(), generator);
		this.rug(BloomBlocks.PINK_RUG.get(), generator);
		this.rug(BloomBlocks.GRAY_RUG.get(), generator);
		this.rug(BloomBlocks.LIGHT_GRAY_RUG.get(), generator);
		this.rug(BloomBlocks.CYAN_RUG.get(), generator);
		this.rug(BloomBlocks.PURPLE_RUG.get(), generator);
		this.rug(BloomBlocks.BLUE_RUG.get(), generator);
		this.rug(BloomBlocks.BROWN_RUG.get(), generator);
		this.rug(BloomBlocks.GREEN_RUG.get(), generator);
		this.rug(BloomBlocks.RED_RUG.get(), generator);
		this.rug(BloomBlocks.BLACK_RUG.get(), generator);

		generator.createParticleOnlyBlock(BloomBlocks.WHITE_SLEEPING_BAG.get(), Blocks.WHITE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.ORANGE_SLEEPING_BAG.get(), Blocks.ORANGE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.MAGENTA_SLEEPING_BAG.get(), Blocks.MAGENTA_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.LIGHT_BLUE_SLEEPING_BAG.get(), Blocks.LIGHT_BLUE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.YELLOW_SLEEPING_BAG.get(), Blocks.YELLOW_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.LIME_SLEEPING_BAG.get(), Blocks.LIME_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.PINK_SLEEPING_BAG.get(), Blocks.PINK_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.GRAY_SLEEPING_BAG.get(), Blocks.GRAY_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.LIGHT_GRAY_SLEEPING_BAG.get(), Blocks.LIGHT_GRAY_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.CYAN_SLEEPING_BAG.get(), Blocks.CYAN_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.PURPLE_SLEEPING_BAG.get(), Blocks.PURPLE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.BLUE_SLEEPING_BAG.get(), Blocks.BLUE_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.BROWN_SLEEPING_BAG.get(), Blocks.BROWN_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.GREEN_SLEEPING_BAG.get(), Blocks.GREEN_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.RED_SLEEPING_BAG.get(), Blocks.RED_WOOL);
		generator.createParticleOnlyBlock(BloomBlocks.BLACK_SLEEPING_BAG.get(), Blocks.BLACK_WOOL);
	}

	private void fullWoodset(WoodsetRegistry woodset, BlockModelGenerators generator){
		BlockModelGenerators.BlockFamilyProvider pool = generator.family(woodset.getPlanks().get());
		pool.generateFor(woodset.getBlockFamily());

		if (woodset.isOverworldTreeWood()){
			generator.createTrivialBlock(woodset.getLeaves().get(), TexturedModel.LEAVES);
		}

		if (woodset.notBambooVariant()){
			generator.woodProvider(woodset.getLog().get()).logWithHorizontal(woodset.getLog().get()).wood(woodset.getWood().get());
			generator.woodProvider(woodset.getStrippedLog().get()).logWithHorizontal(woodset.getStrippedLog().get()).wood(woodset.getStrippedWood().get());
		}
		if (woodset.getWoodsetSettings().hasMosaic()){
			generator.woodProvider(woodset.getLog().get()).logUVLocked(woodset.getLog().get());
			generator.woodProvider(woodset.getStrippedLog().get()).logUVLocked(woodset.getStrippedLog().get());
		}

		generator.createHangingSign(woodset.getStrippedLog().get(), woodset.getHangingSign().get(), woodset.getWallHangingSign().get());

		generator.registerSimpleFlatItemModel(woodset.getBoatItem().get());
		generator.registerSimpleFlatItemModel(woodset.getChestBoatItem().get());

		generator.createShelf(woodset.getShelf().get(), woodset.getStrippedLog().get());
	}

	public void rug(Block block, BlockModelGenerators generator) {
		MultiVariant multiVariant = BlockModelGenerators.plainVariant(TexturedModel.CARPET.get(block).create(block, generator.modelOutput));
		generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, multiVariant));
	}

    public void oreModels(StoneOresRegistry ores, BlockModelGenerators generator) {
        ores.getOresMap().forEach((type, ore) -> {
            STONE_MODELS_MAP.getOrDefault(ores.getBaseStone(), (generators, b) -> {
                generators.createTrivialCube(ore.get());
                return true;
            }).apply(generator, ore.get());
        });
    }

	@Override
	public void generateItemModels(ItemModelGenerators generator) {
		generator.generateFlatItem(BloomItems.WHITE_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.ORANGE_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.MAGENTA_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.LIGHT_BLUE_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.YELLOW_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.LIME_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.PINK_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.GRAY_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.LIGHT_GRAY_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.CYAN_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.PURPLE_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.BLUE_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.BROWN_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.GREEN_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.RED_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.BLACK_SLEEPING_BAG.get(), ModelTemplates.FLAT_ITEM);

		generator.generateFlatItem(BloomItems.COTTON.get(), ModelTemplates.FLAT_ITEM);
		generator.generateFlatItem(BloomItems.YARN.get(), ModelTemplates.FLAT_ITEM);
	}

	public void createSinglePlant(Block block, BlockModelGenerators.PlantType type, BlockModelGenerators generator) {
		generator.registerSimpleItemModel(block.asItem(), type.createItemModel(generator, block));
		this.createPlant(block, type, generator);
	}

	public void createPlant(Block block, BlockModelGenerators.PlantType type, BlockModelGenerators generator) {
		generator.createCrossBlock(block, type);
	}

	public static Map<Block, BiFunction<BlockModelGenerators, Block, Boolean>> STONE_MODELS_MAP = Map.ofEntries(
		Map.entry(Blocks.SANDSTONE, (generators, block) -> {
			StoneCustomDatagens.sandstoneBlockModel(generators, Blocks.SANDSTONE, block);
			return true;
		}),
		Map.entry(Blocks.RED_SANDSTONE, (generators, block) -> {
			StoneCustomDatagens.sandstoneBlockModel(generators, Blocks.RED_SANDSTONE, block);
			return true;
		})
	);

	private static class StoneCustomDatagens{
		private static void sandstoneBlockModel(BlockModelGenerators blockModelGenerators, Block stone, Block ore){
			Material oreMaterial = TextureMapping.getBlockTexture(ore);
			TexturedModel texturedModel = TexturedModel.TOP_BOTTOM_WITH_WALL.get(stone)
				.updateTextures(textureMapping -> textureMapping.put(TextureSlot.SIDE, oreMaterial).put(TextureSlot.BOTTOM, oreMaterial)
				);
			MultiVariant multiVariant = BlockModelGenerators.plainVariant(texturedModel.create(ore, blockModelGenerators.modelOutput));
			blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ore, multiVariant));
		}
	}
}
