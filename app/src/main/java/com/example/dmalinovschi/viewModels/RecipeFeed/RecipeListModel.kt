package com.example.dmalinovschi.viewModels.RecipeFeed

class RecipeListModel (var recipeRows : MutableList<RecipeRowModel>? = null) {

    fun addElementToCollection(recipeRow: RecipeRowModel){
        recipeRows?.add(recipeRow)
     }
}


