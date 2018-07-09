package com.example.dmalinovschi.persistance.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.example.dmalinovschi.persistance.StringTypeConverter;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "recipes_ingredient_combinations",
        indices = {@Index("ingredientId"),
                @Index("recipeId")},
        foreignKeys = {
                @ForeignKey(entity = Ingredients.class,
                        parentColumns = "ingredientId",
                        childColumns = "ingredientId"
                ), @ForeignKey(entity = Recipes.class,
                parentColumns = "recipeId",
                childColumns = "recipeId"
        ),

        })

public class RecipesIngredientsCombination {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int executionOrder;

    private String action;

    private int ingredientId;

    private int weight;

    private int recipeId;

    @TypeConverters({StringTypeConverter.class})
    private MeasurementType measurementType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public MeasurementType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }

    public int getExecutionOrder() {
        return executionOrder;
    }

    public void setExecutionOrder(int executionOrder) {
        this.executionOrder = executionOrder;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
