package com.example.dmalinovschi.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.example.dmalinovschi.adapters.MainAdapter
import com.example.dmalinovschi.persistance.AppDatabase
import com.example.dmalinovschi.persistance.DatabaseInitialiser
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.persistance.models.Recipes
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.RecipesModelService
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeListModel
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeRowModel
import kotlinx.android.synthetic.main.activity_main.*


open class RecipesActivity : MainActivity() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var recipeModelService : RecipesModelService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase = AppDatabase.getInMemoryDatabase(this)
        recipeModelService = RecipesModelService(appDatabase)
        populateDb(appDatabase)

        setToolbar()

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        var button: Button = findViewById(R.id.add_recipe_button)

        if (recipeModelService.getCurrentRecipes()!!.isNotEmpty()) {
            button.visibility = View.GONE;
            recyclerView_main.adapter = MainAdapter(appDatabase, recipeModelService.buildRecipeList())
        } else {
            button.visibility = View.VISIBLE;
            recyclerView_main.setBackgroundColor(Color.GRAY)
        }

    }


    private fun populateDb(appDatabase: AppDatabase) {
        DatabaseInitialiser.populateSync(appDatabase);
    }

    public fun fetchDb(): MutableList<Ingredients>? {
        return appDatabase.ingredientModel().allIngredients

    }


}
