package com.example.romul.bakingapp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Ingredient implements Serializable {

    @JsonProperty("quantity")
    private double qtd;

    @JsonProperty("measure")
    private String measure;

    @JsonProperty("ingredient")
    private String ingredient;

    public Ingredient(){
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

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
