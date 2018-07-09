package com.example.dmalinovschi.activities

import android.os.Bundle
import com.example.dmalinovschi.playground.R

class CreateIngredientActivity : IngredientsActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_ingrediant_activity)
    }
}