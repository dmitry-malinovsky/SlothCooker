package com.example.dmalinovschi.persistance;

import android.os.AsyncTask;

import com.example.dmalinovschi.persistance.AppDatabase;
import com.example.dmalinovschi.persistance.models.Ingredients;
import com.example.dmalinovschi.persistance.models.Recipes;
import com.example.dmalinovschi.persistance.dao.impl.IngredientDaoImpl;
import com.example.dmalinovschi.persistance.dao.impl.RecipeLabelsImpl;
import com.example.dmalinovschi.persistance.dao.impl.RecipesDaoImpl;
import com.example.dmalinovschi.persistance.dao.impl.RecipesIngredientsCombinationDaoImpl;
import com.example.dmalinovschi.persistance.models.Ingredients;
import com.example.dmalinovschi.persistance.models.MeasurementType;
import com.example.dmalinovschi.persistance.models.Recipes;

public class DatabaseInitialiser {
    private IngredientDaoImpl ingredientDao;

    public static void populateAsync(final AppDatabase appDatabase) {
        PolulateDbAsync task = new PolulateDbAsync(appDatabase);
        task.execute();
    }

    public static void populateSync(final AppDatabase appDatabase) {
        populateWithTestData(appDatabase);
    }


    public static void populateWithTestData(AppDatabase appDatabase) {
        IngredientDaoImpl ingredientDao = new IngredientDaoImpl(appDatabase);
        RecipeLabelsImpl recipeLabelsDao = new RecipeLabelsImpl(appDatabase);
        RecipesDaoImpl recipesDao = new RecipesDaoImpl(appDatabase);
        RecipesIngredientsCombinationDaoImpl combinationDao = new RecipesIngredientsCombinationDaoImpl(appDatabase);

        combinationDao.deleteAllRecipeIngredientsCombinations(appDatabase);
        recipesDao.deleteAllRecipes();
        ingredientDao.deleteAllIngredients();
        recipeLabelsDao.deleteAllLabels();

        recipeLabelsDao.addLabel( "Dinner");
        recipeLabelsDao.addLabel( "Breakfast");

        recipesDao.addRecipe("Chicken and rice", "Dinner");
        recipesDao.addRecipe( "Eggs and Oatmeal", "Breakfast");
        recipesDao.addRecipe( "Protein Pancakes", "Breakfast");

        ingredientDao.addIngredient("Chicken", 100, 20, 20, 300);
        ingredientDao.addIngredient("Rice", 123, 20, 20, 300);
        ingredientDao.addIngredient("Protein", 222, 20, 20, 300);
        ingredientDao.addIngredient("Eggs", 12, 20, 20, 300);
        ingredientDao.addIngredient("Oats", 54, 20, 20, 300);

        Recipes recipe = appDatabase.recipesModel().getRecipeByTitle("Chicken and rice");
        Ingredients ingredient = appDatabase.ingredientModel().getIngredientByTitle("Chicken");
        Ingredients ingredient1 = appDatabase.ingredientModel().getIngredientByTitle("Rice");

        Recipes recipe2 = appDatabase.recipesModel().getRecipeByTitle("Eggs and Oatmeal");
        Ingredients ingredient2 = appDatabase.ingredientModel().getIngredientByTitle("Eggs");
        Ingredients ingredient3 = appDatabase.ingredientModel().getIngredientByTitle("Oats");

        combinationDao.addRecipeIngredientCombination(appDatabase,1 , "Add",ingredient, recipe , 100, MeasurementType.GR);
        combinationDao.addRecipeIngredientCombination(appDatabase, 2, "Add",ingredient1, recipe, 300, MeasurementType.GR);

        combinationDao.addRecipeIngredientCombination(appDatabase, 1, "Add",ingredient2, recipe2,300, MeasurementType.PSC);
        combinationDao.addRecipeIngredientCombination(appDatabase, 2, "Add",ingredient3, recipe2, 300, MeasurementType.GR);

        recipesDao.updateNutritionValueForRecipe(recipe);
        recipesDao.getAllRecipeIngredientsByRecipe(recipe);
    }

    private static class PolulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final AppDatabase mDb;

        PolulateDbAsync(AppDatabase database) {
            mDb = database;
        }


        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }
    }
}
