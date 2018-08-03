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

        var createRecipeListAdapter = CreateRecipeListAdapter(recipeActions, inputValidator)
        create_recipe_recycle_view.adapter = createRecipeListAdapter
        create_recipe_recycle_view.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.add_recipe_action)
        val saveRecipe = save_recipe_button

        fab.setOnClickListener {
            recipeActions.actions.add(createRecipeService.buildEmptyRecipeAction())
            create_recipe_recycle_view.adapter.notifyItemInserted(recipeActions.actions.size)
            create_recipe_recycle_view.adapter.notifyDataSetChanged()
            create_recipe_recycle_view.setItemViewCacheSize(recipeActions.actions.size)
        }

        saveRecipe.setOnClickListener {
            for (i in 0 until recipeActions.actions.size) {
                createRecipeListAdapter.recipeActions.actions[i]
            }
        }
    }

    internal fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    internal fun validateAllActions() {

//        for (i in 0 until create_recipe_recycle_view.childCount) {
//            inputValidator.validateTitle(create_recipe_recycle_view.findViewHolderForLayoutPosition(i).itemView.findViewById<TextInputLayout>(R.id.recipe_action_edit_layout))
//        }

    }

    internal fun validateInput(view: View) {
//        return ((inputValidator.validateTitle(ingredient_title_edit_layout)) &&
//                (inputValidator.validateMacronutrients(ingredient_protein_edit_layout)) &&
//                (inputValidator.validateMacronutrients(ingredient_carbs_edit_layout)) &&
//                (inputValidator.validateMacronutrients(ingredient_fats_edit_layout)) &&
//                (inputValidator.validateCcal(ingredient_ccal_edit_layout)))
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