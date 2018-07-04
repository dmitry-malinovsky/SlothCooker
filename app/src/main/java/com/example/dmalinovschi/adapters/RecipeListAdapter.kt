package com.example.dmalinovschi.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.playground.RecipeDetailsActivity
import com.example.dmalinovschi.playground.persistance.AppDatabase
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsModel
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsIngredients.RecipeIngredientsListModel
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsIngredients.RecipeIngredientsRowModel
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsPreparation.RecipeDetailsPreparationListItemModel
import com.example.dmalinovschi.viewModels.RecipeDetails.RecipeDetailsPreparation.RecipeDetailsPreparationListModel
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeListModel
import kotlinx.android.synthetic.main.recipe_row.view.*

class MainAdapter(private var data: AppDatabase, var recipeListModel: RecipeListModel) : RecyclerView.Adapter<RecipesListViewHolder>() {

    //Replace the conntent is views (items)
    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) {
        var view: View = holder.itemView

        val recipe = recipeListModel.recipeRows?.get(position)

        view.recipeTitle_textView.text = recipe?.title
        view.carbs_textView.text = recipe?.totalCarbs.toString()
        view.protein_textView.text = recipe?.totalProtein.toString();
        view.fat_textView.text = recipe?.totalFat.toString()
        view.calories_textView.text = recipe?.totalCcal.toString()

        val allCombinations = data.recipesIngredientsCombinationDao().getAllCombinationsByRecipeId(recipe?.id!!)
        var temp: MutableList<RecipeIngredientsRowModel> = mutableListOf()
        var tempPreparationSteps: MutableList<RecipeDetailsPreparationListItemModel> = mutableListOf()
        for (combination in allCombinations) {
            var ingredientWeight: Int = combination.weight

            temp.add(RecipeIngredientsRowModel(data.ingredientModel().getIngredientById(combination.ingredientId).ingredientTitle, ingredientWeight, combination.measurementType))
            tempPreparationSteps.add(RecipeDetailsPreparationListItemModel(combination.executionOrder, combination.action, data.ingredientModel().getIngredientById(combination.ingredientId), combination.weight))
        }

        holder.reicpeDetails = RecipeDetailsModel(RecipeIngredientsListModel(temp), recipe.title, RecipeDetailsPreparationListModel(tempPreparationSteps))

    }

    //creates new views (items in the list)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recipe_row, parent, false);
        return RecipesListViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return recipeListModel.recipeRows!!.size
    }
}

class RecipesListViewHolder(view: View, var reicpeDetails: RecipeDetailsModel? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        val RECIPE_KEY = "RECIPE"
    }

    init {
        view.setOnClickListener {
            getRecipeDetails()
            val intent = Intent(view.context, RecipeDetailsActivity::class.java)
            intent.putExtra(RECIPE_KEY, reicpeDetails)
            view.context.startActivity(intent)
        }
    }

    fun getRecipeDetails(){
        println("ITEM IS CLICKED")
    }
}