package com.example.dmalinovschi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.playground.persistance.AppDatabase
import com.example.dmalinovschi.playground.persistance.models.Recipes
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsModel
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsIngredients.RecipeIngredientsListModel
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsPreparation.RecipeDetailsPreparationListModel
import kotlinx.android.synthetic.main.recipe_ingredient_row.view.*


class RecipePreparationStepsAdapter(database: AppDatabase, recipe: RecipeDetailsModel) : RecyclerView.Adapter<RecipePreparationStepsAdapter.PreparationstepsViewHolder>() {
    var appDatabase: AppDatabase = database
    var recipe: RecipeDetailsModel = recipe

    override fun onBindViewHolder(holder: PreparationstepsViewHolder, position: Int) {
        var preparationSteps: RecipeDetailsPreparationListModel = recipe.recipeDetailsPreparationListModel
        val preparationStep = preparationSteps.preparationSteps[position]

        holder.itemView.recipe_ingredient_title_text_view.text = preparationStep.action;
        holder.itemView.recipe_ingredient_weight_text_view.text = preparationStep.ingredient.ingredientTitle
        holder.itemView.recipe_ingredient_measurement_type_text_view.text = preparationStep.weight.toString()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreparationstepsViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recipe_ingredient_row, parent, false);
        return PreparationstepsViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return recipe.recipeIngredientsListModel.ingredients.size
    }

    class PreparationstepsViewHolder(view: View, var recipes: Recipes? = null) : RecyclerView.ViewHolder(view) {

    }
}