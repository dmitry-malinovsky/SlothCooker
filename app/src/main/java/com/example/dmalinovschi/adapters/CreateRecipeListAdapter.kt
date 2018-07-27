package com.example.dmalinovschi.adapters

import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.playground.R.id.ingredient_title_edit_layout
import com.example.dmalinovschi.utils.InputValidator
import com.example.dmalinovschi.utils.ValidatingTextWatcher

import com.example.dmalinovschi.viewModels.RecipeDetails.CreateRecipe.CreateRecipeActionsModel
import kotlinx.android.synthetic.main.create_ingrediant_activity.*
import kotlinx.android.synthetic.main.create_recipe_row.view.*
import kotlinx.android.synthetic.main.recipe_preparation_step_row.view.*

class CreateRecipeListAdapter(var recipeActions: CreateRecipeActionsModel, var inputValidator: InputValidator) :
        RecyclerView.Adapter<CreateRecipeListAdapter.CreateRecipeActionViewHolder>() {
     var listRecipeActionViews =mutableListOf<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateRecipeActionViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.create_recipe_row, parent, false)

        (cellForRow.recipe_action_edit_text as (TextInputEditText)).addTextChangedListener(ValidatingTextWatcher(inputValidator::validateTitle,
                cellForRow.recipe_action_edit_layout))
        (cellForRow.ingredient_edit_text as (TextInputEditText)).addTextChangedListener(ValidatingTextWatcher(inputValidator::validateTitle,
                cellForRow.ingredient_edit_layout))
        (cellForRow.ingredient_weight_edit_text as (TextInputEditText)).addTextChangedListener(ValidatingTextWatcher(inputValidator::validateCcal,
                cellForRow.ingredient_weight_layout))
        (cellForRow.ingredient_measurement_edit_text as (TextInputEditText)).addTextChangedListener(ValidatingTextWatcher(inputValidator::validateTitle,
                cellForRow.ingredient_measurement_layout))

        listRecipeActionViews.add(cellForRow)
        return CreateRecipeActionViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return recipeActions.actions.size
    }

    override fun onBindViewHolder(holder: CreateRecipeActionViewHolder, position: Int) {

    }


    class CreateRecipeActionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
