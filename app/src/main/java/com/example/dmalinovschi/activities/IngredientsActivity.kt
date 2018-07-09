package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.dmalinovschi.adapters.IngredientsListAdapter
import com.example.dmalinovschi.persistance.AppDatabase
import com.example.dmalinovschi.persistance.models.Ingredients
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListModel
import com.example.dmalinovschi.viewModels.Ingredients.IngredientsListRowModel
import kotlinx.android.synthetic.main.ingredients_activity.*
import android.support.design.widget.FloatingActionButton
import com.example.dmalinovschi.playground.R


open class IngredientsActivity : MainActivity() {

    private lateinit var appDatabase: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredients_activity)
        setToolbar()

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

        val fab = findViewById<FloatingActionButton>(R.id.add_ingredient_fab)
        fab.setOnClickListener {
            startActivity(Intent(this, CreateIngredientActivity::class.java ))
        }
    }
}