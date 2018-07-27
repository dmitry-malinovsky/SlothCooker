package com.example.dmalinovschi.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
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
import kotlinx.android.synthetic.main.create_ingrediant_activity.*
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

        val createRecipeListAdapter = CreateRecipeListAdapter(recipeActions, inputValidator)
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

    internal fun validateInput(view : View) {
        view
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