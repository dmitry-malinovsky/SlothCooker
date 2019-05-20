package com.example.dmalinovschi.adapters

import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.utils.InputValidator
import com.example.dmalinovschi.utils.ValidatingTextWatcher
import com.example.dmalinovschi.viewModels.RecipeDetails.CreateRecipe.CreateRecipeActionsModel
import kotlinx.android.synthetic.main.create_recipe_row.view.*

class CreateRecipeListAdapter(public var recipeActions: CreateRecipeActionsModel, var inputValidator: InputValidator) :
        RecyclerView.Adapter<CreateRecipeListAdapter.CreateRecipeActionViewHolder>() {

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

        return CreateRecipeActionViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return recipeActions.actions.size
    }

    override fun onBindViewHolder(holder: CreateRecipeActionViewHolder, position: Int) {
        (holder.ingredientActionText as (TextInputEditText)).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                recipeActions.actions[holder.adapterPosition].action = holder.ingredientActionText.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        (holder.ingredientText as (TextInputEditText)).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                recipeActions.actions[holder.adapterPosition].ingredient = holder.ingredientText.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        (holder.ingredienWeightText as (TextInputEditText)).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                recipeActions.actions[holder.adapterPosition].weight = holder.ingredienWeightText.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        (holder.ingredientMeasurementText as (TextInputEditText)).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                recipeActions.actions[holder.adapterPosition].measurement = holder.ingredientMeasurementText.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }


    class CreateRecipeActionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ingredientActionText = view.findViewById<TextInputEditText>(R.id.recipe_action_edit_text)
        val ingredientActionLayout = view.findViewById<TextInputLayout>(R.id.recipe_action_edit_layout)
        val ingredientText = view.findViewById<TextInputEditText>(R.id.ingredient_edit_text)
        val ingredienLayout = view.findViewById<TextInputLayout>(R.id.ingredient_edit_layout)

        val ingredienWeightText = view.findViewById<TextInputEditText>(R.id.ingredient_weight_edit_text)
        val ingredienWeightLayout = view.findViewById<TextInputLayout>(R.id.ingredient_weight_layout)

        val ingredientMeasurementText = view.findViewById<TextInputEditText>(R.id.ingredient_measurement_edit_text)
        val ingredientMeasurementLayout = view.findViewById<TextInputLayout>(R.id.ingredient_measurement_layout)

    }
}
