package com.example.dmalinovschi.persistance.dao.impl;


import com.example.dmalinovschi.persistance.AppDatabase;
import com.example.dmalinovschi.persistance.models.Ingredients;

import java.util.List;

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

    public List<Ingredients> getAllIngredients(){ return appDatabase.ingredientModel().getAllIngredients();}

    public void deleteAllIngredients() {
        appDatabase.ingredientModel().deleteAll();
    }

    public void updateIngredient( Ingredients ingredients) { appDatabase.ingredientModel().updateIngredient(ingredients);}

    public Ingredients getIngredientById (int id){
       return appDatabase.ingredientModel().getIngredientById(id);
    }
}
