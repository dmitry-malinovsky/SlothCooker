package com.example.dmalinovschi.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.NavUtils
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.dmalinovschi.adapters.CreateRecipeListAdapter
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.CreateRecipeActionsModelService
import com.example.dmalinovschi.viewModels.RecipeDetails.CreateRecipe.CreateRecipeActionModel
import com.example.dmalinovschi.viewModels.RecipeDetails.CreateRecipe.CreateRecipeActionsModel
import kotlinx.android.synthetic.main.create_recipe_activity.*

class CreateRecipeActivity : RecipesActivity() {
    public var createRecipeService = CreateRecipeActionsModelService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_recipe_activity)
        setDatabase(this)
        setUpToolbar(findViewById(R.id.back_toolbar))

        val recipeActions = createRecipeService.buileEmptyRecipeActions()

        val createRecipeListAdapter = CreateRecipeListAdapter(recipeActions)
        create_recipe_recycle_view.adapter = createRecipeListAdapter
        create_recipe_recycle_view.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.add_recipe_action)
        fab.setOnClickListener {
            recipeActions.actions.add(createRecipeService.buildEmptyRecipeAction())
            create_recipe_recycle_view.adapter.notifyItemInserted(recipeActions.actions.size)
            create_recipe_recycle_view.adapter.notifyDataSetChanged()
        }
    }

    internal fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}