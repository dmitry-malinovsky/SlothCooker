package com.example.dmalinovschi.activities

import android.os.Bundle
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.playground.R
import kotlinx.android.synthetic.main.create_ingrediant_activity.*

class UpdateIngredientActivity : CreateIngredientActivity() {
    private lateinit var ingredient: Ingredients

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        save_ingredient_fab.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_edit_24px));

        var extras = intent.extras
        ingredient = extras.getSerializable("INGREDIENT") as Ingredients

        ingredient_title_edit_text.setText(ingredient.ingredientTitle)
        ingredient_protein_edit_text.setText(ingredient.protein.toString())
        ingredient_carbs_edit_text.setText(ingredient.carb.toString())
        ingredient_fats_edit_text.setText(ingredient.fat.toString())
        ingredient_ccal_edit_text.setText(ingredient.ccal.toString())
    }
}