package com.example.dmalinovschi.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.dmalinovschi.adapters.RecipeIngredientsAdapter
import com.example.dmalinovschi.adapters.RecipePreparationStepsAdapter
import com.example.dmalinovschi.playground.persistance.AppDatabase
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsModel
import kotlinx.android.synthetic.main.recipe_details_activity.*

class RecipeDetailsActivity : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_details_activity)

        appDatabase = AppDatabase.getInMemoryDatabase(this)

        ricepe_ingredients_recycle_view.layoutManager = LinearLayoutManager(this)
        recipe_preparation_guide_recucle_view.layoutManager = LinearLayoutManager(this)

        var extras : Bundle = intent.extras
        if (true) {
         var recipe : RecipeDetailsModel = extras.getSerializable("RECIPE") as RecipeDetailsModel
            supportActionBar!!.title = recipe.title;
            ricepe_ingredients_recycle_view.adapter = RecipeIngredientsAdapter(appDatabase, recipe)
            recipe_preparation_guide_recucle_view.adapter =  RecipePreparationStepsAdapter(appDatabase, recipe)

        }
    }
}