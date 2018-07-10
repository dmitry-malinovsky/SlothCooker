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
import android.widget.Adapter
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.IngredientsModelService


open class IngredientsActivity : MainActivity() {

    lateinit var appDatabase: AppDatabase
    private var ingredients: List<Ingredients> = mutableListOf()
    private var temp: MutableList<IngredientsListRowModel> = mutableListOf()
    private lateinit var ingredientsService: IngredientsModelService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredients_activity)
        setToolbar()

        appDatabase = AppDatabase.getInMemoryDatabase(this)

        ingredientsService = IngredientsModelService(appDatabase)

        ingredients_recycle_view.layoutManager = LinearLayoutManager(this)
        ingredients_recycle_view.adapter = IngredientsListAdapter(appDatabase,
                ingredientsService.buildIngredientModelList())

        val fab = findViewById<FloatingActionButton>(R.id.add_ingredient_fab)
        fab.setOnClickListener {
            startActivity(Intent(this, CreateIngredientActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()
    }


}