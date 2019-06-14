package com.example.dmalinovschi.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputLayout
import android.support.v4.app.NavUtils
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.example.dmalinovschi.adapters.CreateRecipeListAdapter
import com.example.dmalinovschi.adapters.TextInputLayoutAdapter
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.CreateRecipeActionsModelService
import com.example.dmalinovschi.utils.InputValidator
import kotlinx.android.synthetic.main.create_recipe_activity.*
import kotlinx.android.synthetic.main.create_recipe_row.*

class CreateRecipeActivity : RecipesActivity() {
    private var createRecipeService = CreateRecipeActionsModelService()
    internal lateinit var inputValidator: InputValidator
    internal lateinit var inputAdapter: TextInputLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_recipe_activity)
        setDatabase(this)
        setUpToolbar(findViewById(R.id.back_toolbar))
        inputAdapter = TextInputLayoutAdapter()
        inputValidator = InputValidator(inputAdapter)
        val recipeActions = createRecipeService.buileEmptyRecipeActions()

        var createRecipeListAdapter = CreateRecipeListAdapter(this,recipeActions, inputValidator)
        create_recipe_recycle_view.adapter = createRecipeListAdapter
        create_recipe_recycle_view.layoutManager = LinearLayoutManager(this)

        val addRecipeStepFab = findViewById<FloatingActionButton>(R.id.add_recipe_action)
        val saveRecipe = save_recipe_button

        addRecipeStepFab.setOnClickListener {
            recipeActions.actions.add(createRecipeService.buildEmptyRecipeAction())
            create_recipe_recycle_view.adapter.notifyItemInserted(recipeActions.actions.size)
            create_recipe_recycle_view.adapter.notifyDataSetChanged()
            create_recipe_recycle_view.setItemViewCacheSize(recipeActions.actions.size)
        }

        saveRecipe.setOnClickListener {
            validateAllActions()
        }
    }

    internal fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    internal fun validateAllActions() {
        for (i in 0 until create_recipe_recycle_view.childCount) {
            validateInput(create_recipe_recycle_view.findViewHolderForLayoutPosition(i).itemView.
                    findViewById<TextInputLayout>(R.id.recipe_action_edit_layout))
        }
    }

    internal fun validateInput(view: View): Boolean {
        return ((inputValidator.validateTitle(recipe_title_edit_layout)) &&
                (inputValidator.validateAction(recipe_action_edit_layout)) &&
                (inputValidator.validateIngredient(ingredient_edit_layout)) &&
                (inputValidator.validateWeight(ingredient_weight_layout)) &&
                (inputValidator.validateMeasurement(ingredient_measurement_layout)))

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