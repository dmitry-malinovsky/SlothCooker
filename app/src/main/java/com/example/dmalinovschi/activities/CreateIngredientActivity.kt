package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.dmalinovschi.adapters.RecipesListViewHolder
import com.example.dmalinovschi.persistance.dao.impl.IngredientDaoImpl
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import kotlinx.android.synthetic.main.create_ingrediant_activity.*

class CreateIngredientActivity : IngredientsActivity() {
    companion object {
        val NEW_INGREDIENT_KEY = "NEW INGREDIENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_ingrediant_activity)
        setUpToolbar(findViewById(R.id.back_toolbar))
        val ingredientDao = IngredientDaoImpl(appDatabase)

        save_ingredient_fab.setOnClickListener {
            var inputIngredientDetails: IngredientsListRowModel = getInputDetails()
            ingredientDao.addIngredient(inputIngredientDetails.title,
                    inputIngredientDetails.protein,
                    inputIngredientDetails.carbs,
                    inputIngredientDetails.fat,
                    inputIngredientDetails.ccal)


            val intent = Intent(this, IngredientsActivity::class.java)
            intent.putExtra(NEW_INGREDIENT_KEY, inputIngredientDetails)
            finish()
        }
    }

    private fun getInputDetails(): IngredientsListRowModel {
        return IngredientsListRowModel(
                ingredient_title_edit.text.toString(),
                ingredient_protein_edit.text.toString().toInt(),
                ingredient_carbs_edit.text.toString().toInt(),
                ingredient_fats_edit.text.toString().toInt(),
                ingredient_ccal_edit.text.toString().toInt()
        )
    }

    private fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
}