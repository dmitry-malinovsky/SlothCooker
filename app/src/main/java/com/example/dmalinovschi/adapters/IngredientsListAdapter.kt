package com.example.dmalinovschi.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmalinovschi.activities.MainActivity
import com.example.dmalinovschi.activities.UpdateIngredientActivity
import com.example.dmalinovschi.persistance.AppDatabase
import com.example.dmalinovschi.persistance.dao.impl.IngredientDaoImpl
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import kotlinx.android.synthetic.main.ingredient_row.view.*


class IngredientsListAdapter(private var data: AppDatabase, var ingredientsListModel: IngredientsListModel) : RecyclerView.Adapter<IngredientsListViewHolder>() {

    //Replace the conntent is views (items)
    override fun onBindViewHolder(holder: IngredientsListViewHolder, position: Int) {

        val ingredient = ingredientsListModel.ingredients[position]
        val ingredientDao = IngredientDaoImpl(MainActivity.appDatabase)

        holder.itemView.ingredient_title_text_view.text = ingredient.title
        holder.itemView.ingredient_protein_textView.text = ingredient.protein.toString()
        holder.itemView.ingredient_carbs_textView.text = ingredient.carbs.toString()
        holder.itemView.ingredient_fat_textView.text = ingredient.fat.toString()
        holder.itemView.ingredient_calories_textView.text = ingredient.ccal.toString()


        holder.ingredientDetails = ingredientDao.getIngredientById(ingredient.id)
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
        notifyItemInserted(itemCount + 1)
    }

}


class IngredientsListViewHolder(view: View, var ingredientDetails: Ingredients? = null) : RecyclerView.ViewHolder(view) {
    companion object {
        val INGREDIENT_KEY = "SELECTED INGREDIENT"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, UpdateIngredientActivity::class.java)
            intent.putExtra(INGREDIENT_KEY, ingredientDetails)
            view.context.startActivity(intent)
        }
    }
}