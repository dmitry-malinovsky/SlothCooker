package com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsIngredients

import android.os.Parcel
import android.os.Parcelable
import com.example.dmalinovschi.playground.persistance.models.Ingredients
import com.example.dmalinovschi.playground.persistance.models.MeasurementType
import java.io.Serializable

class RecipeIngredientsRowModel (var title : String, var  weight : Int, var measurementType : MeasurementType) : Serializable {

}