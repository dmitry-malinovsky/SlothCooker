package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import com.example.dmalinovschi.persistance.dao.impl.IngredientDaoImpl
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsRowDetailsModel

class UpdateIngredientActivity : CreateIngredientActivity(){
    private lateinit var ingredient : Ingredients

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var extras = intent.extras
        var ingredientDetails = extras.getSerializable("INGREDIENT") as IngredientsRowDetailsModel

        inputTitle.setText(ingredientDetails.title)
        inputProtein.setText(ingredientDetails.protein)
        inputCarbs.setText(ingredientDetails.carbs)
        inputFats.setText(ingredientDetails.fat)
        inputCcal.setText(ingredientDetails.ccal)
    }

 override fun confirmInput() {
     val ingredientDao = IngredientDaoImpl(appDatabase)
     if (!validateTitle() and !validateProtein() and !validateCarbs() and !validateCcal() and !validateFat()) {
         inputValidationResult = false
     } else {
         if (validateTitle() and validateProtein() and validateCarbs() and validateCcal() and validateFat()) {
             inputValidationResult = true
             var inputIngredientDetails: IngredientsListRowModel = getInputDetails()
             ingredient.ingredientTitle = inputIngredientDetails.title
             ingredient.protein = inputIngredientDetails.protein
             ingredient.carb = inputIngredientDetails.carbs
             ingredient.fat = inputIngredientDetails.fat
             ingredient.ccal = inputIngredientDetails.ccal
             ingredientDao.updateIngredient(ingredient)
             
             val intent = Intent(this, IngredientsActivity::class.java)
             intent.putExtra(NEW_INGREDIENT_KEY, inputIngredientDetails)
             finish()
         }
     }
 }
}