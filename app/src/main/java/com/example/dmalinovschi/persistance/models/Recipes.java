package com.example.dmalinovschi.persistance.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.dmalinovschi.persistance.models.RecipeLabels;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "recipes",
        indices = {@Index("labelId")},
        foreignKeys = {
                @ForeignKey(entity = RecipeLabels.class,
                        parentColumns = "recipeLabelId",
                        childColumns = "labelId"
                )})

public class Recipes implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int recipeId;

    private String title;

    private int labelId;

    private int totalProtein;

    private int totalCarbs;

    private int totalFat;

    private int totalCcal;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int recipeLabel_id) {
        this.labelId = recipeLabel_id;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public int getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(int totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public int getTotalCcal() {
        return totalCcal;
    }

    public void setTotalCcal(int totalCcal) {
        this.totalCcal = totalCcal;
    }
}
