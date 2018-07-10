package com.example.dmalinovschi.viewModels.Ingredients

class IngredientsListModel(var ingredients : MutableList<IngredientsListRowModel>) {
    fun addItem(ingredientModel: IngredientsListRowModel){
        ingredients.add(ingredientModel)
    }
}