package com.example.dmalinovschi.persistance.dao.impl;

import com.example.dmalinovschi.persistance.AppDatabase;
import com.example.dmalinovschi.persistance.models.RecipeLabels;

public class RecipeLabelsImpl {
    private static AppDatabase appDatabase;

    public RecipeLabelsImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void addLabel(final String lableName) {
        RecipeLabels label = new RecipeLabels();
        label.setTitle(lableName);
        appDatabase.recipeLabelsModel().addRecipeLabel(label);
    }

    public void deleteAllLabels() {
        appDatabase.recipeLabelsModel().deleteAll();
    }

}
