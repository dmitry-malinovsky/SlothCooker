package com.example.dmalinovschi.playground.persistance.dao.impl;


import com.example.dmalinovschi.playground.persistance.AppDatabase;
import com.example.dmalinovschi.playground.persistance.models.Ingredients;

public class IngredientDaoImpl {
    private static AppDatabase appDatabase;

    public IngredientDaoImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void addIngredient(final String ingredientTitle, int protein, int carbs, int fat, int ccal) {
        Ingredients ingredient = new Ingredients();
        ingredient.setIngredientTitle(ingredientTitle);
        ingredient.setProtein(protein);
        ingredient.setCarb(carbs);
        ingredient.setFat(fat);
        ingredient.setCcal(ccal);
        appDatabase.ingredientModel().addIngredient(ingredient);
    }

    public void deleteAllIngredients() {
        appDatabase.ingredientModel().deleteAll();
    }


}
