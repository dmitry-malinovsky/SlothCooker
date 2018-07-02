package com.example.dmalinovschi.playground.persistance.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dmalinovschi.playground.persistance.models.Ingredients;

import java.util.List;

@Dao
public interface IngredientDao {
    @Query("select * from ingredients where ingredientId = :ingredientId")
    Ingredients getIngredientById(int ingredientId);

    @Query("select * from ingredients where ingredientTitle = :title")
    Ingredients getIngredientByTitle(String title);

    @Query("select * from ingredients")
    List<Ingredients> getAllIngredients();

    @Query("select ingredientId from recipes_ingredient_combinations where recipeId=:recipeId")
    List<Integer> getAllIngredientsByRecipeId(int recipeId);

    @Query("delete from ingredients")
    void deleteAll();

    @Insert ()
    void addIngredient(Ingredients ingredient);



}
