package com.example.romul.bakingapp.API;

import com.example.romul.bakingapp.Models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.romul.bakingapp.Utils.NetworkUtils.FULL_PATH;

public interface RecipesApiService {

    @GET(FULL_PATH)
    Call<List<Recipe>> getRecipes();
}
