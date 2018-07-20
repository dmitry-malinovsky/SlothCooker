package com.example.dmalinovschi.services

import com.example.dmalinovschi.persistance.AppDatabase
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel

class IngredientsModelService(var appDatabase: AppDatabase) {
    public fun buildIngredientModelList(): IngredientsListModel {
        val temp: MutableList<IngredientsListRowModel> = mutableListOf()
        val ingredients = getAllIngredients()!!
        for (ingredient in ingredients) {
            temp.add(buildIngredientRowModel(ingredient)
            )
        }
        return IngredientsListModel(temp)
    }

    public fun buildIngredientRowModel(ingredient: Ingredients): IngredientsListRowModel {
        return IngredientsListRowModel(ingredient.ingredientId,
                ingredient.ingredientTitle,
                ingredient.protein,
                ingredient.carb,
                ingredient.fat,
                ingredient.ccal)
    }

    private fun getAllIngredients(): List<Ingredients>? {
        return appDatabase.ingredientModel().allIngredients
    }
}