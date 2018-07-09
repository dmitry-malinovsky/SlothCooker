package com.example.dmalinovschi.persistance.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "recipe_labels")
public class RecipeLabels {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int recipeLabelId;

    private String title;

    public int getRecipeLabelId() {
        return recipeLabelId;
    }

    public void setRecipeLabelId(int recipeLabelId) {
        this.recipeLabelId = recipeLabelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
