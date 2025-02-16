package net.mefiddzy.bedrockagemod.datagen;

import net.mefiddzy.bedrockagemod.BedrockAgeMod;
import net.mefiddzy.bedrockagemod.block.ModBlocks;
import net.mefiddzy.bedrockagemod.datagen.loot.ModBlockLootTables;
import net.mefiddzy.bedrockagemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipieProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipieProvider(PackOutput pOutput) {
        super(pOutput);
    }

    private static final List<ItemLike> WeakBD = List.of(ModBlocks.WEAK_BEDROCK.get().asItem());

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter,WeakBD , RecipeCategory.MISC, ModItems.UNREFINED_BEDROCK_INGOT.get().asItem(), 0.5f, 100, "un_bedrock_ingot");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WEAK_BEDROCK.get())
                .pattern("!!!")
                .pattern("!!!")
                .pattern("!!!")
                .define('!', ModItems.BEDROCK_POWDER.get())
                .save(pWriter);
        ShapelessRecipeBuilder. shapeless(RecipeCategory.MISC, ModItems.BEDROCK_POWDER.get(), 9)
                .requires(ModBlocks.WEAK_BEDROCK.get())
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, BedrockAgeMod.MOD_ID + ':' +getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
