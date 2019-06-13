package com.example.dmalinovschi.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import com.example.dmalinovschi.adapters.IngredientsListAdapter
import com.example.dmalinovschi.persistance.AppDatabase
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.IngredientsModelService
import kotlinx.android.synthetic.main.ingredients_activity.*


open class IngredientsActivity : MainActivity() {

    private lateinit var ingredientsService: IngredientsModelService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredients_activity)
        setDatabase(this)
        setToolbar()

        ingredientsService = IngredientsModelService(appDatabase)

        ingredients_recycle_view.layoutManager = LinearLayoutManager(this)
        ingredients_recycle_view.adapter = IngredientsListAdapter(appDatabase,
                ingredientsService.buildIngredientModelList())

        val fab = findViewById<FloatingActionButton>(R.id.add_ingredient_fab)
        fab.setOnClickListener {
            startActivity(Intent(this, UpdateIngredientActivity::class.java))
        }
    }


    override fun onRestart() {
        super.onRestart()

        ingredients_recycle_view.layoutManager = LinearLayoutManager(this)
        ingredients_recycle_view.adapter = IngredientsListAdapter(appDatabase,
                ingredientsService.buildIngredientModelList())
    }


}