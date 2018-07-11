package com.example.romul.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.romul.bakingapp.Adapters.RecipesAdapter;
import com.example.romul.bakingapp.Models.Recipe;
import com.example.romul.bakingapp.Utils.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mErrorDisplay;
    private ProgressBar mLoadingIndicator;
    private RecyclerView mRecipesList;
    private RecipesAdapter mAdapter;

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

        this.mAdapter = new RecipesAdapter();
        mRecipesList.setAdapter(mAdapter);

        Call<List<Recipe>> call = new NetworkUtils().getRecipesApiService().getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                Log.e(TAG, "onResponse - No. Recipes: " + response.body().size());

                mAdapter.setRecipesData(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, "onFailure - link: " + call.request().url().toString() +
                        " " + t.getMessage() + " - " + t.getLocalizedMessage());
                mErrorDisplay.setVisibility(View.VISIBLE);
            }
        });
    }
}
