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
import kotlinx.android.synthetic.main.recipe_ingredient_row.view.*


class RecipeIngredientsAdapter(database: AppDatabase, recipe: RecipeDetailsModel) : RecyclerView.Adapter<RecipeIngredientsAdapter.IngredientsListViewHolder>() {
    var appDatabase: AppDatabase = database
    var recipe: RecipeDetailsModel = recipe

    override fun onBindViewHolder(holder: IngredientsListViewHolder, position: Int) {
        var ingredients: RecipeIngredientsListModel = recipe.recipeIngredientsListModel

        holder.itemView.recipe_ingredient_title_text_view.text = ingredients.ingredients.get(position).title;
        holder.itemView.recipe_ingredient_weight_text_view.text = ingredients.ingredients.get(position).weight.toString()
        holder.itemView.recipe_ingredient_measurement_type_text_view.text = ingredients.ingredients.get(position).measurementType.name

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsListViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recipe_ingredient_row, parent, false);
        return IngredientsListViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return recipe.recipeIngredientsListModel.ingredients.size
    }

    class IngredientsListViewHolder(view: View, var recipes: Recipes? = null) : RecyclerView.ViewHolder(view) {

    }
}