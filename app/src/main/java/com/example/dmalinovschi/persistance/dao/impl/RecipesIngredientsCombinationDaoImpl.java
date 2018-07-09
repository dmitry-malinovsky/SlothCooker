package com.example.dmalinovschi.persistance.dao.impl;

import com.example.dmalinovschi.persistance.AppDatabase;
import com.example.dmalinovschi.persistance.models.Ingredients;
import com.example.dmalinovschi.persistance.models.MeasurementType;
import com.example.dmalinovschi.persistance.models.Recipes;
import com.example.dmalinovschi.persistance.models.RecipesIngredientsCombination;

public class RecipesIngredientsCombinationDaoImpl {
    private static AppDatabase appDatabase;

    public RecipesIngredientsCombinationDaoImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void addRecipeIngredientCombination(final AppDatabase appDatabase,
                                               final int executionOrder,
                                               final String action,
                                               final Ingredients ingredient,
                                               final Recipes recipe,
                                               final int weight,
                                               final MeasurementType measurementType) {
        RecipesIngredientsCombination combination = new RecipesIngredientsCombination();
        combination.setExecutionOrder(executionOrder);
        combination.setAction(action);
        combination.setRecipeId(recipe.getRecipeId());
        combination.setIngredientId(ingredient.getIngredientId());
        combination.setWeight(weight);
        combination.setMeasurementType(measurementType);

        appDatabase.recipesIngredientsCombinationDao().addCombination(combination);
    }

    public void deleteAllRecipeIngredientsCombinations(final AppDatabase appDatabase) {
        appDatabase.recipesIngredientsCombinationDao().deleteAll();
    }

}
