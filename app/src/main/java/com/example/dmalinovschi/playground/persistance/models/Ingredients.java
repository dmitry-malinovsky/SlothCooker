package com.example.dmalinovschi.playground.persistance.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;

@Entity (tableName = "ingredients")
public class Ingredients implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ingredientId;

    private String ingredientTitle;

    private int protein;

    private int carb;

    private int fat;

    private int ccal;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientTitle() {
        return ingredientTitle;
    }

    public void setIngredientTitle(String ingredientTitle) {
        this.ingredientTitle = ingredientTitle;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCcal() {
        return ccal;
    }

    public void setCcal(int ccal) {
        this.ccal = ccal;
    }
}
