package com.example.romul.bakingapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.romul.bakingapp.Models.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM ingredient")
    List<Ingredient> loadAllIngredients();

    @Insert
    void insertIngredients(List<Ingredient> ingredients);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateIngredient(Ingredient ingredient);

    @Delete
    void deleteIngredients(List<Ingredient> ingredients);


}
