package com.example.dmalinovschi.services

import com.example.dmalinovschi.persistance.AppDatabase
import com.example.dmalinovschi.persistance.models.Recipes
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeListModel
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeRowModel

class RecipesModelService(var appDatabase: AppDatabase) {

    public fun buildRecipeList(): RecipeListModel {
        val temp: MutableList<RecipeRowModel> = mutableListOf()
        for (recipe in this.getCurrentRecipes()!!) {
            temp.add(buildRecipeRow(recipe)
            )
        }
        return RecipeListModel(temp)
    }

    public fun buildRecipeRow(recipe: Recipes): RecipeRowModel {
        return RecipeRowModel(
                recipe.totalCarbs,
                recipe.totalProtein,
                recipe.totalFat,
                recipe.totalCcal,
                recipe.title,
                recipe.recipeId
        )
    }

    public fun getCurrentRecipes(): List<Recipes>? {
        return appDatabase.recipesModel().allRecipes
    }
}
