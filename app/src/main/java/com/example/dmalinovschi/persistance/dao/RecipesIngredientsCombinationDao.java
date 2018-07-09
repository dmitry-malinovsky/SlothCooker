package com.example.dmalinovschi.persistance.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dmalinovschi.persistance.models.RecipesIngredientsCombination;

import java.util.List;

@Dao
public interface RecipesIngredientsCombinationDao {
    @Query("select * from recipes_ingredient_combinations where id = :id")
    RecipesIngredientsCombination getIngredientCombinationById(int id);

    @Query("select * from recipes_ingredient_combinations")
    List<RecipesIngredientsCombination> getAllIngredientCombinations();

    @Query("select * from recipes_ingredient_combinations where recipeId =:recipeId")
    List<RecipesIngredientsCombination> getAllCombinationsByRecipeId(int recipeId);

    @Query("delete from recipes_ingredient_combinations")
    void deleteAll();

    @Insert()
    void addCombination(RecipesIngredientsCombination ingredientCombination);

}
