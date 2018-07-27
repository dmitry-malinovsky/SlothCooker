package com.example.dmalinovschi.activities

import android.os.Bundle
import com.example.dmalinovschi.adapters.TextInputLayoutAdapter
import com.example.dmalinovschi.playground.R
import com.example.dmalinovschi.services.IngredientsModelService
import com.example.dmalinovschi.utils.InputValidator


open class CreateIngredientActivity : IngredientMainActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_ingrediant_activity)
        setUpToolbar(findViewById(R.id.back_toolbar))
        inputAdapter = TextInputLayoutAdapter()
        inputValidator = InputValidator(inputAdapter)
        setViewListeners()
        ingredientsModelService = IngredientsModelService(appDatabase)
    }

}