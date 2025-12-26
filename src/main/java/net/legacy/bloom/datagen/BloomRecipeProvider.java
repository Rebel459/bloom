package net.legacy.bloom.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class BloomRecipeProvider extends FabricRecipeProvider {

    public BloomRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Contract("_, _ -> new")
    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
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
        };
    }

    @Override
    @NotNull
    public String getName() {
        return "Bloom Recipes";
    }
}