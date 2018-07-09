package com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsPreparation

import com.example.dmalinovschi.persistance.models.Ingredients
import java.io.Serializable

class RecipeDetailsPreparationListItemModel(var order : Int, var action: String, var ingredient : Ingredients, var weight : Int): Serializable