package com.example.romul.bakingapp.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "ingredient")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @JsonProperty("quantity")
    private double qtd;

    @JsonProperty("measure")
    private String measure;

    @JsonProperty("ingredient")
    private String ingredient;

    @ColumnInfo(name = "recipe_name")
    private String recipeName;

    @Ignore
    public Ingredient(){
    }

    public Ingredient(int id, double qtd, String measure, String ingredient, String recipeName){
        this.id = id;
        this.qtd = qtd;
        this.measure = measure;
        this.ingredient = ingredient;
        this.recipeName = recipeName;
    }

    public int getId() {
        return id;
    }

    public double getQtd() {
        return qtd;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
