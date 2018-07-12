package com.example.romul.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.romul.bakingapp.Adapters.IngredientsAdapter;
import com.example.romul.bakingapp.Models.Recipe;
import com.example.romul.bakingapp.R;
import com.example.romul.bakingapp.StepsActivity;

import static com.example.romul.bakingapp.StepsActivity.EXTRA_RECIPE;


public class StepsFragment extends Fragment {

    private static final String TAG = StepsFragment.class.getSimpleName();

    private Recipe mRecipe;

    public StepsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(EXTRA_RECIPE)){
                mRecipe = (Recipe) savedInstanceState.getSerializable(EXTRA_RECIPE);
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        RecyclerView rvIngredientsList = rootView.findViewById(R.id.rv_ingredients);

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        rvIngredientsList.setLayoutManager(layoutManager);
        rvIngredientsList.setHasFixedSize(true);

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
        rvIngredientsList.setAdapter(ingredientsAdapter);

        if (mRecipe != null){
            Log.e(TAG, "onCreateView - Ingredients: " + mRecipe.getIngredients().size());
            ingredientsAdapter.setIngredients(mRecipe.getIngredients());
        }

        return rootView;
    }

    public void setRecipe(Recipe mRecipe) {
        this.mRecipe = mRecipe;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (mRecipe != null)
            outState.putSerializable(EXTRA_RECIPE, mRecipe);
        super.onSaveInstanceState(outState);
    }
}
