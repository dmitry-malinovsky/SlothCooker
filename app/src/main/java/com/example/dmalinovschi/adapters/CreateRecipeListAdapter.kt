package com.example.dmalinovschi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.viewModels.RecipeDetails.CreateRecipe.CreateRecipeActionsModel
import kotlinx.android.synthetic.main.create_recipe_row.view.*

class CreateRecipeListAdapter(var  recipeActions: CreateRecipeActionsModel) : RecyclerView.Adapter<CreateRecipeListAdapter.CreateRecipeActionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateRecipeActionViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.create_recipe_row, parent, false);
        return CreateRecipeActionViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return recipeActions.actions.size
    }

    override fun onBindViewHolder(holder: CreateRecipeActionViewHolder, position: Int) {
        holder.itemView.recipe_action_edit_layout
        holder.itemView.ingredient_edit_layout
        holder.itemView.ingredient_weight_layout
        holder.itemView.ingredient_measurement_layout
    }


    class CreateRecipeActionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
