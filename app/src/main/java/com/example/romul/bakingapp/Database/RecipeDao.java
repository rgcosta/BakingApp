package com.example.romul.bakingapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.romul.bakingapp.Models.Recipe;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe WHERE id_table = 1")
    Recipe loadRecipe();

    @Insert
    void insertRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecipe(Recipe recipe);

}
