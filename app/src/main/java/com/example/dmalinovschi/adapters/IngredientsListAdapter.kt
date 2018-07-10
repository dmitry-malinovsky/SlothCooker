package com.example.dmalinovschi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.persistance.AppDatabase
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListModel
import kotlinx.android.synthetic.main.ingredient_row.view.*
import android.arch.lifecycle.ViewModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel


class IngredientsListAdapter (private var data: AppDatabase, var ingredientsListModel: IngredientsListModel) : RecyclerView.Adapter<IngredientsListViewHolder>() {

    //Replace the conntent is views (items)
    override fun onBindViewHolder(holder: IngredientsListViewHolder, position: Int) {

        val ingredient = ingredientsListModel.ingredients[position]

        holder.itemView.ingredient_title_text_view.text = ingredient.title
        holder.itemView.ingredient_protein_textView.text = ingredient.protein.toString()
        holder.itemView.ingredient_carbs_textView.text = ingredient.carbs.toString()
        holder.itemView.ingredient_fat_textView.text = ingredient.fat.toString()
        holder.itemView.ingredient_calories_textView.text = ingredient.ccal.toString()

    }

    //creates new views (items in the list)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsListViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.ingredient_row, parent, false);
        return IngredientsListViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return ingredientsListModel.ingredients.size
    }

   public fun addItem(viewModel: IngredientsListRowModel) {
        ingredientsListModel.addItem(viewModel)
        notifyItemInserted(itemCount +1)
    }
}



class IngredientsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}