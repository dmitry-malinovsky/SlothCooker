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

class IngredientsListAdapter(private var data: AppDatabase, var ingredientsListModel: IngredientsListModel) : RecyclerView.Adapter<IngredientsListViewHolder>() {

    //Replace the conntent is views (items)
    override fun onBindViewHolder(holder: IngredientsListViewHolder, position: Int) {

        val ingredient = ingredientsListModel.ingredients.get(position)

        holder.itemView.ingredient_title_text_view.text = ingredient.title

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
}

class IngredientsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}