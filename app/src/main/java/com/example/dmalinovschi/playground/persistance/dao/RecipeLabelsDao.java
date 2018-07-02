package com.example.dmalinovschi.playground.persistance.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dmalinovschi.playground.persistance.models.RecipeLabels;

import java.util.List;

@Dao
public interface RecipeLabelsDao {

    @Query("select * from recipe_labels where recipeLabelId = :recipeLabelId")
    RecipeLabels getRecipeLabelById(int recipeLabelId);

    @Query("select * from recipe_labels where title = :title")
    RecipeLabels getRecipeLabelByTitle(String title);

    @Query("select * from recipe_labels")
    List<RecipeLabels> getAllRecipeLabels();

    @Query("delete from recipe_labels")
    void deleteAll();

    @Insert()
    void addRecipeLabel(RecipeLabels recipeLabels);

}
