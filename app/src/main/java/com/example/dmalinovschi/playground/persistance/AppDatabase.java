package com.example.dmalinovschi.playground.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.content.Context;

import com.example.dmalinovschi.playground.persistance.dao.IngredientDao;
import com.example.dmalinovschi.playground.persistance.dao.RecipeLabelsDao;
import com.example.dmalinovschi.playground.persistance.dao.RecipesDao;
import com.example.dmalinovschi.playground.persistance.dao.RecipesIngredientsCombinationDao;
import com.example.dmalinovschi.playground.persistance.models.RecipesIngredientsCombination;
import com.example.dmalinovschi.playground.persistance.models.Ingredients;
import com.example.dmalinovschi.playground.persistance.models.RecipeLabels;
import com.example.dmalinovschi.playground.persistance.models.Recipes;

@Database(entities = {Ingredients.class, RecipesIngredientsCombination.class, RecipeLabels.class, Recipes.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public  abstract IngredientDao ingredientModel();
    public abstract RecipesIngredientsCombinationDao recipesIngredientsCombinationDao();
    public abstract RecipesDao recipesModel();
    public abstract RecipeLabelsDao recipeLabelsModel();


    public static  AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public void destroyInstance(){
        INSTANCE = null;
    }
}
