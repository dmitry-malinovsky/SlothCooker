package com.example.dmalinovschi.playground.persistance.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dmalinovschi.playground.persistance.models.Recipes;
import com.example.dmalinovschi.playground.persistance.models.RecipesIngredientsCombination;

import java.util.List;

@Dao
public interface RecipesDao {

    @Query("select * from recipes where recipeId = :recipeId")
    Recipes getRecipeById(int recipeId);

    @Query("select * from recipes")
    List<Recipes> getAllRecipes();

    @Query("select * from recipes where title = :recipe_title")
    List<Recipes> getAllRecipesByTitle(String recipe_title);

    @Query("select * from recipes where title = :recipe_title")
    Recipes getRecipeByTitle(String recipe_title);

    @Query("delete from recipes")
    void deleteAll();

    @Insert()
    void addRecipe(Recipes recipe);

    @Update
    void updateRecipe(Recipes recipe);
}
