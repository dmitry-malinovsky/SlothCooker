package com.example.dmalinovschi.persistance.dao.impl;

import com.example.dmalinovschi.persistance.AppDatabase;
import com.example.dmalinovschi.persistance.models.Ingredients;
import com.example.dmalinovschi.persistance.models.RecipeLabels;
import com.example.dmalinovschi.persistance.models.Recipes;
import com.example.dmalinovschi.persistance.models.RecipesIngredientsCombination;

import java.util.ArrayList;
import java.util.List;

public class RecipesDaoImpl {
    private AppDatabase appDatabase;

    public RecipesDaoImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }


    public void addRecipe(final String reipeTitle, final String recipeLabelTitle) {
        RecipeLabels recipeLabel = appDatabase.recipeLabelsModel().getRecipeLabelByTitle(recipeLabelTitle);
        Recipes recipe = new Recipes();
        recipe.setTitle(reipeTitle);
        recipe.setLabelId(recipeLabel.getRecipeLabelId());
        appDatabase.recipesModel().addRecipe(recipe);
    }

    public void deleteAllRecipes() {
        appDatabase.recipesModel().deleteAll();
    }

    public List<RecipesIngredientsCombination> getAllCombinationsByRecipeId(Recipes recipes){
       return appDatabase.recipesIngredientsCombinationDao().
               getAllCombinationsByRecipeId(recipes.getRecipeId());
    }

    public void updateNutritionValueForRecipe(Recipes recipes){
        List<RecipesIngredientsCombination> allRecipeIngredients = getAllCombinationsByRecipeId(recipes);

        int totalCarbs = recipes.getTotalCarbs();
        int totalFat = recipes.getTotalFat();
        int totalProtein = recipes.getTotalProtein();
        int totalCcal = recipes.getTotalCcal();

        for (RecipesIngredientsCombination combination: allRecipeIngredients
             ) {
            int ingredientWeight = combination.getWeight();
            Ingredients ingredient = appDatabase.ingredientModel().getIngredientById(combination.getIngredientId());
            totalCarbs += ingredient.getCarb()*ingredientWeight;
            totalFat += ingredient.getFat()*ingredientWeight;
            totalProtein += ingredient.getProtein()*ingredientWeight;
            totalCcal += ingredient.getCcal()*ingredientWeight;
        }
        recipes.setTotalCarbs(totalCarbs);
        recipes.setTotalFat(totalFat);
        recipes.setTotalProtein(totalProtein);
        recipes.setTotalCcal(totalCcal);
        appDatabase.recipesModel().updateRecipe(recipes);
    }

    public List<Ingredients> getAllRecipeIngredientsByRecipe(Recipes recipes){
        List<RecipesIngredientsCombination> allCombinationsByRecipeId = getAllCombinationsByRecipeId(recipes);
        List<Ingredients> ingredients = new ArrayList<>();
        for (RecipesIngredientsCombination combination :
                allCombinationsByRecipeId) {
         ingredients.add( appDatabase.ingredientModel().getIngredientById(combination.getIngredientId()));
        }
        return ingredients;
    }


}
