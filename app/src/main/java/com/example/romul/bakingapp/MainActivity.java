package com.example.romul.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.romul.bakingapp.Adapters.RecipesAdapter;
import com.example.romul.bakingapp.Models.Recipe;
import com.example.romul.bakingapp.Utils.NetworkUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.romul.bakingapp.Utils.NetworkUtils.isOnline;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.RecipesOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mErrorDisplay;
    private ProgressBar mLoadingIndicator;
    private RecyclerView mRecipesList;
    private RecipesAdapter mAdapter;

    private List<Recipe> mRecipes = new ArrayList<>();

    private static final String LIST_RECIPES_KEY = "list_recipes_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mErrorDisplay = findViewById(R.id.tv_error_display);
        this.mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        this.mRecipesList = findViewById(R.id.rv_recipes);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecipesList.setLayoutManager(layoutManager);
        mRecipesList.setHasFixedSize(true);

        this.mAdapter = new RecipesAdapter(this);
        mRecipesList.setAdapter(mAdapter);

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(LIST_RECIPES_KEY)){
                mRecipes = (List<Recipe>) savedInstanceState.getSerializable(LIST_RECIPES_KEY);
            }
        }

        if (mRecipes.isEmpty() == true || mRecipes == null){

            callToRecipes();
        } else {
            Log.e(TAG, "Reuses existing recipes. Saving data!!");
            mAdapter.setRecipesData(mRecipes);
        }
    }

    private void callToRecipes() {

        if (isOnline(this)) {
            mLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            Context context = MainActivity.this;
            Intent noConnectionIntent = new Intent(context, NoInternetConnectionActivity.class);
            startActivity(noConnectionIntent);
        }

        Call<List<Recipe>> call = new NetworkUtils().getRecipesApiService().getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                Log.e(TAG, "onResponse - Retrieves New Recipes: " + response.body().size());
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                mRecipes = response.body();
                Log.e(TAG, "onResponse - recipes: " + mRecipes.size());
                mAdapter.setRecipesData(mRecipes);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, "onFailure - link: " + call.request().url().toString() +
                        " " + t.getMessage() + " - " + t.getLocalizedMessage());
                mErrorDisplay.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        if (mRecipes != null)
            savedInstanceState.putSerializable(LIST_RECIPES_KEY, (Serializable) mRecipes);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(Recipe recipeClicked) {
        Intent intentSteps = new Intent(this, StepsActivity.class);
        intentSteps.putExtra(StepsActivity.EXTRA_RECIPE, recipeClicked);
        startActivity(intentSteps);
    }
}
