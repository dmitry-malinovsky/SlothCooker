package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import com.example.dmalinovschi.persistance.dao.impl.IngredientDaoImpl
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsRowDetailsModel

class UpdateIngredientActivity : CreateIngredientActivity(){
    private lateinit var ingredient : Ingredients

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        floatingActionButton.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_edit_24px));

        var extras = intent.extras
        ingredient = extras.getSerializable("INGREDIENT") as Ingredients

        inputTitle.setText(ingredient.ingredientTitle)
        inputProtein.setText(ingredient.protein.toString())
        inputCarbs.setText(ingredient.carb.toString())
        inputFats.setText(ingredient.fat.toString())
        inputCcal.setText(ingredient.ccal.toString())
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