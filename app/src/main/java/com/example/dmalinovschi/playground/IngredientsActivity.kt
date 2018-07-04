package com.example.dmalinovschi.playground

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.dmalinovschi.adapters.IngredientsListAdapter
import com.example.dmalinovschi.adapters.RecipeIngredientsAdapter
import com.example.dmalinovschi.playground.persistance.AppDatabase
import com.example.dmalinovschi.playground.persistance.models.Ingredients
import com.example.dmalinovschi.playground.persistance.models.Recipes
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeListModel
import com.example.dmalinovschi.viewModels.RecipeFeed.RecipeRowModel
import kotlinx.android.synthetic.main.ingredients_activity.*
import kotlinx.android.synthetic.main.recipe_details_activity.*

class IngredientsActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredients_activity)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }



        appDatabase = AppDatabase.getInMemoryDatabase(this)


        var ingredients: List<Ingredients> = appDatabase.ingredientModel().allIngredients
        var temp: MutableList<IngredientsListRowModel> = mutableListOf()

        for (ingredient in ingredients) {
            temp.add(IngredientsListRowModel(
                    ingredient.ingredientId,
                    ingredient.ingredientTitle
            ))
        }

        var ingredientsListModel = IngredientsListModel(temp)

        ingredients_recycle_view.layoutManager = LinearLayoutManager(this)
        ingredients_recycle_view.adapter = IngredientsListAdapter(appDatabase, ingredientsListModel)

    }
}