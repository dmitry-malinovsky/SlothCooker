package com.example.dmalinovschi.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.TextView

import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsRowDetailsModel

class IngredientDetailsActivity : CreateIngredientActivity() {
    private lateinit var textViewTitle: TextView
    private lateinit var textViewProtein: TextView
    private lateinit var textViewCarbs: TextView
    private lateinit var textViewFats: TextView
    private lateinit var textViewCcal: TextView
    private lateinit var editIngredientButton: FloatingActionButton
    private lateinit var inputAdapter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredient_details_activity)
        setDatabase(this)
        setUpToolbar(findViewById(R.id.back_toolbar))
        setViewElements()

        var extras = intent.extras
        var ingredientDetails = extras.getSerializable("INGREDIENT") as IngredientsRowDetailsModel
        textViewTitle.text = ingredientDetails.title
        textViewProtein.text = ingredientDetails.protein.toString()
        textViewCarbs.text =ingredientDetails.carbs.toString()
        textViewFats.text = ingredientDetails.fat.toString()
        textViewCcal.text = ingredientDetails.ccal.toString()
    }

    private fun setViewElements() {
        textViewTitle = findViewById(R.id.ingredient_title_text)
        textViewProtein = findViewById(R.id.ingredient_protein_text)
        textViewCarbs = findViewById(R.id.ingredient_carbs_text)
        textViewFats = findViewById(R.id.ingredient_fats_text)
        textViewCcal = findViewById(R.id.ingredient_ccal_text)
    }
}