package com.example.dmalinovschi.viewModels.RecipeDetails

import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsIngredients.RecipeIngredientsListModel
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsPreparation.RecipeDetailsPreparationListModel
import java.io.Serializable

class RecipeDetailsModel (
        var recipeIngredientsListModel: RecipeIngredientsListModel,
        var title: String,
        var recipeDetailsPreparationListModel : RecipeDetailsPreparationListModel
) : Serializable {

}