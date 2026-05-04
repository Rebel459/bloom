package net.rebel459.bloom.datagen;

import com.ibm.icu.impl.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.tag.BloomItemTags;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.bloom.util.WoodsetRegistry;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Contract;

public final class BloomRecipeProvider extends FabricRecipeProvider {

    public BloomRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Contract("_, _ -> new")
    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
        return new RecipeProvider(registries, exporter) {
            @Override
            public void buildRecipes() {

				this.oneToOneConversionRecipe(Items.PURPLE_DYE, BloomBlocks.BELLFLOWER, "purple_dye", 2);
                this.oneToOneConversionRecipe(Items.WHITE_DYE, BloomBlocks.HYDRANGEA, "white_dye", 2);

                this.oneToOneConversionRecipe(Items.RED_DYE, BloomBlocks.BROMELIAD, "red_dye");
                this.oneToOneConversionRecipe(Items.YELLOW_DYE, BloomBlocks.HELLEBORE, "yellow_dye");
                this.oneToOneConversionRecipe(Items.PINK_DYE, BloomBlocks.PINK_ORCHID, "pink_dye");
                this.oneToOneConversionRecipe(Items.PURPLE_DYE, BloomBlocks.SCILLA, "purple_dye");
                this.oneToOneConversionRecipe(Items.WHITE_DYE, BloomBlocks.CALLA_LILY, "white_dye");
                this.oneToOneConversionRecipe(Items.MAGENTA_DYE, BloomBlocks.DIANTHUS, "magenta_dye");
                this.oneToOneConversionRecipe(Items.YELLOW_DYE, BloomBlocks.GOLDENROD, "yellow_dye");
				this.oneToOneConversionRecipe(Items.ORANGE_DYE, BloomBlocks.ORANGE_DAISY, "orange_dye");
				this.oneToOneConversionRecipe(Items.PINK_DYE, BloomBlocks.HYACINTH, "pink_dye");
				this.oneToOneConversionRecipe(Items.WHITE_DYE, BloomBlocks.QUEENCUP, "white_dye");

				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.POLISHED_DOLERITE, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.POLISHED_DOLERITE_STAIRS, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.POLISHED_DOLERITE_SLAB, BloomBlocks.DOLERITE, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.POLISHED_DOLERITE_WALL, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICKS, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_STAIRS, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_SLAB, BloomBlocks.DOLERITE, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_WALL, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILES, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_STAIRS, BloomBlocks.DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_SLAB, BloomBlocks.DOLERITE, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_WALL, BloomBlocks.DOLERITE);

				this.convertStoneRecipe(BloomBlocks.POLISHED_DOLERITE.get(), BloomBlocks.DOLERITE.get());

				this.slabRecipe(BloomBlocks.POLISHED_DOLERITE_SLAB, BloomBlocks.POLISHED_DOLERITE);
				this.stairRecipe(BloomBlocks.POLISHED_DOLERITE_STAIRS, BloomBlocks.POLISHED_DOLERITE);
				this.wallRecipe(BloomBlocks.POLISHED_DOLERITE_WALL, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.POLISHED_DOLERITE_STAIRS, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.POLISHED_DOLERITE_SLAB, BloomBlocks.POLISHED_DOLERITE, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.POLISHED_DOLERITE_WALL, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICKS, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_STAIRS, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_SLAB, BloomBlocks.POLISHED_DOLERITE, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_WALL, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILES, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_STAIRS, BloomBlocks.POLISHED_DOLERITE);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_SLAB, BloomBlocks.POLISHED_DOLERITE, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_WALL, BloomBlocks.POLISHED_DOLERITE);

				this.convertStoneRecipe(BloomBlocks.DOLERITE_BRICKS, BloomBlocks.POLISHED_DOLERITE);

				this.slabRecipe(BloomBlocks.DOLERITE_BRICK_SLAB, BloomBlocks.DOLERITE_BRICKS);
				this.stairRecipe(BloomBlocks.DOLERITE_BRICK_STAIRS, BloomBlocks.DOLERITE_BRICKS);
				this.wallRecipe(BloomBlocks.DOLERITE_BRICK_WALL, BloomBlocks.DOLERITE_BRICKS);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_STAIRS, BloomBlocks.DOLERITE_BRICKS);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_SLAB, BloomBlocks.DOLERITE_BRICKS, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_BRICK_WALL, BloomBlocks.DOLERITE_BRICKS);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILES, BloomBlocks.DOLERITE_BRICKS);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_STAIRS, BloomBlocks.DOLERITE_BRICKS);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_SLAB, BloomBlocks.DOLERITE_BRICKS, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_WALL, BloomBlocks.DOLERITE_BRICKS);

				this.convertStoneRecipe(BloomBlocks.DOLERITE_TILES, BloomBlocks.DOLERITE_BRICKS);

				this.slabRecipe(BloomBlocks.DOLERITE_TILE_SLAB, BloomBlocks.DOLERITE_TILES);
				this.stairRecipe(BloomBlocks.DOLERITE_TILE_STAIRS, BloomBlocks.DOLERITE_TILES);
				this.wallRecipe(BloomBlocks.DOLERITE_TILE_WALL, BloomBlocks.DOLERITE_TILES);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_STAIRS, BloomBlocks.DOLERITE_TILES);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_SLAB, BloomBlocks.DOLERITE_TILES, 2);
				this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BloomBlocks.DOLERITE_TILE_WALL, BloomBlocks.DOLERITE_TILES);

				this.shaped(RecipeCategory.MISC, BloomItems.YARN)
					.define('#', Ingredient.of(BloomItems.COTTON))
					.pattern("##")
					.pattern("##")
					.unlockedBy("has_cotton", this.has(BloomItems.COTTON))
					.save(exporter);

				this.shaped(RecipeCategory.MISC, Items.STRING)
					.define('#', Ingredient.of(BloomItems.YARN))
					.pattern("##")
					.pattern("##")
					.unlockedBy("has_cotton", this.has(BloomItems.YARN))
					.save(exporter);

				this.shaped(RecipeCategory.DECORATIONS, BloomBlocks.WHITE_RUG, 3)
					.define('#', Ingredient.of(BloomItems.YARN))
					.pattern("###")
					.pattern("###")
					.unlockedBy("has_yarn", this.has(BloomItems.YARN))
					.group("rug")
					.save(this.output, "rug");

				sleepingBagRecipes(
					List.of(
						Triple.of(BloomItems.BLACK_SLEEPING_BAG, Items.BLACK_WOOL, Items.BLACK_DYE),
						Triple.of(BloomItems.BLUE_SLEEPING_BAG, Items.BLUE_WOOL, Items.BLUE_DYE),
						Triple.of(BloomItems.BROWN_SLEEPING_BAG, Items.BROWN_WOOL, Items.BROWN_DYE),
						Triple.of(BloomItems.CYAN_SLEEPING_BAG, Items.CYAN_WOOL, Items.CYAN_DYE),
						Triple.of(BloomItems.GRAY_SLEEPING_BAG, Items.GRAY_WOOL, Items.GRAY_DYE),
						Triple.of(BloomItems.GREEN_SLEEPING_BAG, Items.GREEN_WOOL, Items.GREEN_DYE),
						Triple.of(BloomItems.LIGHT_BLUE_SLEEPING_BAG, Items.LIGHT_BLUE_WOOL, Items.LIGHT_BLUE_DYE),
						Triple.of(BloomItems.LIGHT_GRAY_SLEEPING_BAG, Items.LIGHT_GRAY_WOOL, Items.LIGHT_GRAY_DYE),
						Triple.of(BloomItems.LIME_SLEEPING_BAG, Items.LIME_WOOL, Items.LIME_DYE),
						Triple.of(BloomItems.MAGENTA_SLEEPING_BAG, Items.MAGENTA_WOOL, Items.MAGENTA_DYE),
						Triple.of(BloomItems.ORANGE_SLEEPING_BAG, Items.ORANGE_WOOL, Items.ORANGE_DYE),
						Triple.of(BloomItems.PINK_SLEEPING_BAG, Items.PINK_WOOL, Items.PINK_DYE),
						Triple.of(BloomItems.PURPLE_SLEEPING_BAG, Items.PURPLE_WOOL, Items.PURPLE_DYE),
						Triple.of(BloomItems.RED_SLEEPING_BAG, Items.RED_WOOL, Items.RED_DYE),
						Triple.of(BloomItems.YELLOW_SLEEPING_BAG, Items.YELLOW_WOOL, Items.YELLOW_DYE),
						Triple.of(BloomItems.WHITE_SLEEPING_BAG, Items.WHITE_WOOL, Items.WHITE_DYE)
					)
				);

				rugRecipes(
					List.of(
						Pair.of(BloomBlocks.BLACK_RUG.asItem(), Items.BLACK_DYE),
						Pair.of(BloomBlocks.BLUE_RUG.asItem(), Items.BLUE_DYE),
						Pair.of(BloomBlocks.BROWN_RUG.asItem(), Items.BROWN_DYE),
						Pair.of(BloomBlocks.CYAN_RUG.asItem(), Items.CYAN_DYE),
						Pair.of(BloomBlocks.GRAY_RUG.asItem(), Items.GRAY_DYE),
						Pair.of(BloomBlocks.GREEN_RUG.asItem(), Items.GREEN_DYE),
						Pair.of(BloomBlocks.LIGHT_BLUE_RUG.asItem(), Items.LIGHT_BLUE_DYE),
						Pair.of(BloomBlocks.LIGHT_GRAY_RUG.asItem(), Items.LIGHT_GRAY_DYE),
						Pair.of(BloomBlocks.LIME_RUG.asItem(), Items.LIME_DYE),
						Pair.of(BloomBlocks.MAGENTA_RUG.asItem(), Items.MAGENTA_DYE),
						Pair.of(BloomBlocks.ORANGE_RUG.asItem(), Items.ORANGE_DYE),
						Pair.of(BloomBlocks.PINK_RUG.asItem(), Items.PINK_DYE),
						Pair.of(BloomBlocks.PURPLE_RUG.asItem(), Items.PURPLE_DYE),
						Pair.of(BloomBlocks.RED_RUG.asItem(), Items.RED_DYE),
						Pair.of(BloomBlocks.YELLOW_RUG.asItem(), Items.YELLOW_DYE),
						Pair.of(BloomBlocks.WHITE_RUG.asItem(), Items.WHITE_DYE)
					)
				);

				generateWoodsetRecipes(BloomBlocks.JACARANDA, this, exporter, BloomItemTags.JACARANDA_LOGS);
				generateWoodsetRecipes(BloomBlocks.GOLDEN_BIRCH, this, exporter, BloomItemTags.GOLDEN_BIRCH_LOGS);
				generateWoodsetRecipes(BloomBlocks.PINE, this, exporter, BloomItemTags.PINE_LOGS);

				for (StoneOresRegistry registry : StoneOresRegistry.ALL_REGISTRIES) {
					oreRecipes(registry);
				}
            }

			public void sleepingBagRecipes(List<Triple<ItemLike, ItemLike, ItemLike>> list) {
				list.forEach(triple -> {
					ItemLike sleepingBag = triple.getLeft();
					ItemLike wool = triple.getMiddle();
					ItemLike dye = triple.getRight();
					this.shaped(RecipeCategory.DECORATIONS, sleepingBag)
						.define('Y', Ingredient.of(BloomItems.YARN))
						.define('#', Ingredient.of(wool))
						.pattern("YYY")
						.pattern("###")
						.unlockedBy("has_sleeping_bag_material", this.has(BloomItemTags.SLEEPING_BAG_MATERIALS))
						.group("sleeping_bag")
						.save(exporter);
					Stream<Triple<ItemLike, ItemLike, ItemLike>> stream = list.stream().filter(sleepingBagTriple -> !sleepingBagTriple.getLeft().equals(sleepingBag.asItem()));
					List<Triple<ItemLike, ItemLike, ItemLike>> streamList = stream.toList();
					List<ItemLike> sleepingBagList = new ArrayList<>();
					for (Triple<ItemLike, ItemLike, ItemLike> sleepingBagTriple : streamList) {
						sleepingBagList.add(sleepingBagTriple.getLeft());
					}
					this.shapeless(RecipeCategory.DECORATIONS, sleepingBag)
						.requires(Ingredient.of(sleepingBagList.stream()))
						.unlockedBy("has_dye", this.has(dye))
						.group("sleeping_bag_dye")
						.save(this.output, "dye_" + getItemName(sleepingBag));
				});
			}

			public void rugRecipes(List<Pair<ItemLike, ItemLike>> list) {
				list.forEach(pair -> {
					ItemLike rug = pair.first;
					ItemLike dye = pair.second;
					this.shaped(RecipeCategory.DECORATIONS, rug, 3)
						.define('D', Ingredient.of(dye))
						.define('#', Ingredient.of(BloomItems.YARN))
						.pattern("DDD")
						.pattern("###")
						.unlockedBy("has_dye", this.has(dye))
						.group("rug")
						.save(exporter);
					Stream<Pair<ItemLike, ItemLike>> stream = list.stream().filter(rugPair -> !rugPair.first.equals(rug.asItem()));
					List<Pair<ItemLike, ItemLike>> streamList = stream.toList();
					List<ItemLike> rugList = new ArrayList<>();
					for (Pair<ItemLike, ItemLike> rugPair : streamList) {
						rugList.add(rugPair.first);
					}
					this.shapeless(RecipeCategory.DECORATIONS, rug)
						.requires(Ingredient.of(rugList.stream()))
						.unlockedBy("has_dye", this.has(dye))
						.group("rug_dye")
						.save(this.output, "dye_" + getItemName(rug));
				});
			}

            public void oreRecipes(StoneOresRegistry ores) {
                ores.getOresMap().forEach((oreType, block) -> {
                    String name = oreType.name;
                    if (Objects.equals(name, "coal")) this.smeltOre(block, Items.COAL, 0.1F, name);
                    if (Objects.equals(name, "copper")) this.smeltOre(block, Items.COPPER_INGOT, 0.7F, name);
                    if (Objects.equals(name, "iron")) this.smeltOre(block, Items.IRON_INGOT, 0.7F, name);
                    if (Objects.equals(name, "redstone")) this.smeltOre(block, Items.REDSTONE, 0.7F, name);
                    if (Objects.equals(name, "gold")) this.smeltOre(block, Items.GOLD_INGOT, 1F, name);
                    if (Objects.equals(name, "diamond")) this.smeltOre(block, Items.DIAMOND, 1F, name);
                    if (Objects.equals(name, "emerald")) this.smeltOre(block, Items.EMERALD, 1F, name);
                    if (Objects.equals(name, "lapis")) this.smeltOre(block, Items.LAPIS_LAZULI, 0.2F, name);
                    if (Objects.equals(name, "sapphire")) this.smeltOre(block, BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath("legacies_and_legends", "sapphire")).asItem(), 1F, name);
                });
            }

            public void smeltOre(ItemLike input, ItemLike output, float experience, String material) {
                String group = material;
                String type = output.asItem().getName(output.asItem().getDefaultInstance()).getString().toLowerCase();
                if (type.contains("lazuli")) group = group + "_lazuli";
                if (type.contains("ingot")) group = group + "_ingot";
                this.oreSmelting(List.of(input), RecipeCategory.MISC, CookingBookCategory.MISC, output, experience, 200,  group);
                this.oreBlasting(List.of(input), RecipeCategory.MISC, CookingBookCategory.MISC, output, experience, 100,  group);
            }

			public void slabRecipe(ItemLike output, ItemLike ingredient) {
				this.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
					.define('#', Ingredient.of(ingredient))
					.pattern("###")
					.unlockedBy("has_ingredient", this.has(ingredient))
					.save(exporter);
			}

			public void stairRecipe(ItemLike output, ItemLike ingredient) {
				this.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
					.define('#', Ingredient.of(ingredient))
					.pattern("#  ")
					.pattern("## ")
					.pattern("###")
					.unlockedBy("has_ingredient", this.has(ingredient))
					.save(exporter);
			}

			public void wallRecipe(ItemLike output, ItemLike ingredient) {
				this.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
					.define('#', Ingredient.of(ingredient))
					.pattern("###")
					.pattern("###")
					.unlockedBy("has_ingredient", this.has(ingredient))
					.save(exporter);
			}

			public void convertStoneRecipe(ItemLike output, ItemLike ingredient) {
				this.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
					.define('#', Ingredient.of(ingredient))
					.pattern("##")
					.pattern("##")
					.unlockedBy("has_ingredient", this.has(ingredient))
					.save(exporter);
			}
        };
    }

	private void generateWoodsetRecipes(WoodsetRegistry woodset, RecipeProvider recipeGenerator, RecipeOutput exporter, TagKey<Item> logs){
		recipeGenerator.planksFromLog(woodset.getPlanks(), logs, 4);
		recipeGenerator.stairBuilder(woodset.getStairs(), Ingredient.of(woodset.getPlanks())).unlockedBy(woodset.hasPlanks(), recipeGenerator.has(woodset.getPlanks())).save(exporter);
		recipeGenerator.slab(RecipeCategory.BUILDING_BLOCKS, woodset.getSlab(), woodset.getPlanks());
		recipeGenerator.generateRecipes(woodset.getBlockFamily(), FeatureFlagSet.of());
		if (woodset.notBambooVariant()){
			recipeGenerator.woodFromLogs(woodset.getWood(), woodset.getLog());
			recipeGenerator.woodFromLogs(woodset.getStrippedWood(), woodset.getStrippedLog());
		}
		if (woodset.getWoodsetSettings().hasMosaic()){
			recipeGenerator.mosaicBuilder(RecipeCategory.BUILDING_BLOCKS, woodset.getMosaic(), woodset.getSlab());
			recipeGenerator.stairBuilder(woodset.getMosaicStairs(), Ingredient.of(woodset.getPlanks())).unlockedBy(woodset.hasPlanks(), recipeGenerator.has(woodset.getMosaic())).save(exporter);
			recipeGenerator.slab(RecipeCategory.BUILDING_BLOCKS, woodset.getMosaicStairs(), woodset.getPlanks());
		}
		recipeGenerator.fenceBuilder(woodset.getFence(), Ingredient.of(woodset.getPlanks())).unlockedBy(woodset.hasPlanks(), recipeGenerator.has(woodset.getPlanks())).save(exporter);
		recipeGenerator.fenceGateBuilder(woodset.getFenceGate(), Ingredient.of(woodset.getPlanks())).unlockedBy(woodset.hasPlanks(), recipeGenerator.has(woodset.getPlanks())).save(exporter);
		recipeGenerator.doorBuilder(woodset.getDoor(), Ingredient.of(woodset.getPlanks())).unlockedBy(woodset.hasPlanks(), recipeGenerator.has(woodset.getPlanks())).save(exporter);
		recipeGenerator.trapdoorBuilder(woodset.getTrapDoor(), Ingredient.of(woodset.getPlanks())).unlockedBy(woodset.hasPlanks(), recipeGenerator.has(woodset.getPlanks())).save(exporter);

		recipeGenerator.signBuilder(woodset.getSignItem(), Ingredient.of(woodset.getPlanks())).unlockedBy(woodset.hasPlanks(), recipeGenerator.has(woodset.getPlanks())).save(exporter);
		recipeGenerator.hangingSign(woodset.getHangingSignItem(), woodset.getStrippedLog());

		recipeGenerator.woodenBoat(woodset.getBoatItem(), woodset.getPlanks());
		recipeGenerator.chestBoat(woodset.getChestBoatItem(), woodset.getBoatItem());

		recipeGenerator.shelf(woodset.getShelf(), woodset.getStrippedLog());
	}

    @Override
    public String getName() {
        return "Bloom Recipes";
    }
}
