package net.legacy.bloom.datagen;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.data.StoneOresRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

                oreRecipes(BloomBlocks.DOLERITE_ORES);
                oreRecipes(BloomBlocks.DIORITE_ORES);
                oreRecipes(BloomBlocks.GRANITE_ORES);
                oreRecipes(BloomBlocks.ANDESITE_ORES);
                oreRecipes(BloomBlocks.SANDSTONE_ORES);
                oreRecipes(BloomBlocks.TUFF_ORES);

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
