package com.example.dmalinovschi.playground

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.example.dmalinovschi.adapters.MainAdapter
import com.example.dmalinovschi.playground.persistance.AppDatabase
import com.example.dmalinovschi.playground.persistance.DatabaseInitialiser
import com.example.dmalinovschi.playground.persistance.models.Ingredients
import com.example.dmalinovschi.playground.persistance.models.Recipes
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeListModel
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeRowModel
import kotlinx.android.synthetic.main.activity_main.*


open class RecipesActivity : MainActivity() {

    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase = AppDatabase.getInMemoryDatabase(this)
        populateDb(appDatabase)

        setToolbar()

        var recipes: List<Recipes> = appDatabase.recipesModel().allRecipes
        var temp: MutableList<RecipeRowModel> = mutableListOf()

        for (recipe in recipes) {
            temp.add(RecipeRowModel(
                    recipe.totalCarbs,
                    recipe.totalProtein,
                    recipe.totalFat,
                    recipe.totalCcal,
                    recipe.title,
                    recipe.recipeId
            ))
        }

        var recipeListModel = RecipeListModel(temp)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        var button: Button = findViewById(R.id.add_recipe_button)

        if (recipes.isNotEmpty()) {
            button.visibility = View.GONE;
            recyclerView_main.adapter = MainAdapter(appDatabase, recipeListModel)
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
