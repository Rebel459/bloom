package net.legacy.bloom.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import com.ibm.icu.impl.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.tag.BloomItemTags;
import net.legacy.bloom.util.StoneOresRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Contract;

public final class BloomRecipeProvider extends FabricRecipeProvider {

    public BloomRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
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

				this.shaped(RecipeCategory.MISC, BloomItems.YARN)
					.define('#', Ingredient.of(BloomItems.COTTON))
					.pattern("##")
					.pattern("##")
					.unlockedBy("has_cotton", this.has(BloomItems.COTTON))
					.save(exporter);

				this.shaped(RecipeCategory.DECORATIONS, BloomBlocks.WHITE_RUG, 2)
					.define('#', Ingredient.of(BloomItems.YARN))
					.pattern("###")
					.unlockedBy("has_yarn", this.has(BloomItems.YARN))
					.group("rug")
					.save(exporter);

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

				BloomBlocks.JACARANDA.generateRecipes(this, exporter, BloomItemTags.JACARANDA_LOGS);

				for (StoneOresRegistry registry : StoneOresRegistry.ALL_REGISTRIES) {
					oreRecipes(registry);
				}
//                oreRecipes(BloomBlocks.DOLERITE_ORES);
//                oreRecipes(BloomBlocks.DIORITE_ORES);
//                oreRecipes(BloomBlocks.GRANITE_ORES);
//                oreRecipes(BloomBlocks.ANDESITE_ORES);
//                oreRecipes(BloomBlocks.SANDSTONE_ORES);
//                oreRecipes(BloomBlocks.TUFF_ORES);

/*                ERWoodRecipeProvider.buildRecipes(this, exporter);

                this.shapeless(RecipeCategory.BUILDING_BLOCKS, ERBlocks.CHORUS_BLOCK)
                        .requires(Ingredient.of(ERItems.CHORUS_SPINE), 9)
                        .unlockedBy("has_chorus_spine", this.has(ERItems.CHORUS_SPINE))
                        .save(exporter);

                this.shaped(RecipeCategory.BUILDING_BLOCKS, ERBlocks.CHORUS_MOSAIC)
                        .define('#', Ingredient.of(ERBlocks.CHORUS_SLAB))
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy("has_planks", this.has(ERBlocks.CHORUS_PLANKS))
                        .save(exporter);

                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.PURPUR_BLOCK, ERBlocks.PURPUR);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.PURPUR_STAIRS, ERBlocks.PURPUR);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.PURPUR_SLAB, ERBlocks.PURPUR, 2);
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, Blocks.PURPUR_PILLAR, ERBlocks.PURPUR);*/
            }

			public void sleepingBagRecipes(List<Triple<Item, Item, Item>> list) {
				list.forEach(triple -> {
					Item sleepingBag = triple.getLeft();
					Item wool = triple.getMiddle();
					Item dye = triple.getRight();
					this.shaped(RecipeCategory.DECORATIONS, sleepingBag)
						.define('Y', Ingredient.of(BloomItems.YARN))
						.define('#', Ingredient.of(wool))
						.pattern("YYY")
						.pattern("###")
						.unlockedBy("has_sleeping_bag_material", this.has(BloomItemTags.SLEEPING_BAG_MATERIALS))
						.group("sleeping_bag")
						.save(exporter);
					Stream<Triple<Item, Item, Item>> stream = list.stream().filter(sleepingBagTriple -> !sleepingBagTriple.getLeft().equals(sleepingBag.asItem()));
					List<Triple<Item, Item, Item>> streamList = stream.toList();
					List<Item> sleepingBagList = new ArrayList<>();
					for (Triple<Item, Item, Item> sleepingBagTriple : streamList) {
						sleepingBagList.add(sleepingBagTriple.getLeft());
					}
					this.shapeless(RecipeCategory.DECORATIONS, sleepingBag)
						.requires(Ingredient.of(sleepingBagList.stream()))
						.unlockedBy("has_dye", this.has(dye))
						.group("sleeping_bag_dye")
						.save(this.output, "dye_" + getItemName(sleepingBag));
				});
			}

			public void rugRecipes(List<Pair<Item, Item>> list) {
				list.forEach(pair -> {
				Item rug = pair.first;
				Item dye = pair.second;
				if (rug != BloomBlocks.WHITE_RUG.asItem()) {
					this.shaped(RecipeCategory.DECORATIONS, rug, 3)
						.define('D', Ingredient.of(dye))
						.define('#', Ingredient.of(BloomItems.YARN))
						.pattern("DDD")
						.pattern("###")
						.unlockedBy("has_dye", this.has(dye))
						.group("rug")
						.save(exporter);
				}
				Stream<Pair<Item, Item>> stream = list.stream().filter(rugPair -> !rugPair.first.equals(rug.asItem()));
				List<Pair<Item, Item>> streamList = stream.toList();
				List<Item> rugList = new ArrayList<>();
				for (Pair<Item, Item> rugPair : streamList) {
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

            public void smeltOre(Block input, Item output, float experience, String material) {
                String group = material;
                String type = output.getName().getString().toLowerCase();
                if (type.contains("lazuli")) group = group + "_lazuli";
                if (type.contains("ingot")) group = group + "_ingot";
                this.oreSmelting(List.of(input.asItem()), RecipeCategory.MISC, output, experience, 200, group);
                this.oreBlasting(List.of(input.asItem()), RecipeCategory.MISC, output, experience, 100, group);
            }
        };
    }

    @Override
    public String getName() {
        return "Bloom Recipes";
    }
}
