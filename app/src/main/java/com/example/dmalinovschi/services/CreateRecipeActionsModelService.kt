package com.example.dmalinovschi.services

import com.example.dmalinovschi.viewModels.RecipeDetails.CreateRecipe.CreateRecipeActionModel
import com.example.dmalinovschi.viewModels.RecipeDetails.CreateRecipe.CreateRecipeActionsModel

class CreateRecipeActionsModelService() {
    fun buildEmptyRecipeAction(): CreateRecipeActionModel {
        return CreateRecipeActionModel("", "", null, "")
    }

    fun buileEmptyRecipeActions(): CreateRecipeActionsModel {
        val temp = mutableListOf<CreateRecipeActionModel>()
        temp.add(0,buildEmptyRecipeAction())
        return CreateRecipeActionsModel(temp)
    }
}