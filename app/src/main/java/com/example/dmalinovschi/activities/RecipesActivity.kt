package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import com.example.dmalinovschi.adapters.RecipeListAdapter
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.RecipesModelService
import kotlinx.android.synthetic.main.activity_main.*


open class RecipesActivity : MainActivity() {

    private lateinit var recipeModelService: RecipesModelService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDatabase(this)
        if (!databaseInitialised) {
            populateDb()
        }

        recipeModelService = RecipesModelService(appDatabase)
        setToolbar()

        recipes_recycle_view.layoutManager = LinearLayoutManager(this)
        recipes_recycle_view.adapter = RecipeListAdapter(appDatabase, recipeModelService.buildRecipeList())
        val fab = findViewById<FloatingActionButton>(R.id.add_recipe_fab)
        fab.setOnClickListener {
            startActivity(Intent(this, CreateRecipeActivity::class.java))
        }
    }

    public fun fetchDb(): MutableList<Ingredients>? {
        return appDatabase.ingredientModel().allIngredients

    }


}
