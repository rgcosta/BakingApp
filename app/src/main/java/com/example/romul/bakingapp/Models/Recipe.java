package com.example.romul.bakingapp.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipe")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_table")
    private int idTable;

    @JsonProperty("id")
    @ColumnInfo(name = "id_recipe")
    private int idRecipe;

    @JsonProperty("name")
    private String name;

    @JsonProperty("servings")
    @Ignore
    private int servings;

    @JsonProperty("ingredients")
    @Ignore
    private ArrayList<Ingredient> ingredients;

    @JsonProperty("steps")
    @Ignore
    private List<Step> steps;

    @Ignore
    public Recipe(){
    }

    public Recipe(int idTable, int idRecipe, String name){
        this.idTable = idTable;
        this.idRecipe = idRecipe;
        this.name = name;
    }

    public int getIdTable() {
        return idTable;
    }

    public String getName() {
        return name;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public int getServings() {
        return servings;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
