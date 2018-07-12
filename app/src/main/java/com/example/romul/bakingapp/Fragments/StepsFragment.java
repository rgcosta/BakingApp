package com.example.romul.bakingapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.romul.bakingapp.Adapters.IngredientsAdapter;
import com.example.romul.bakingapp.Adapters.StepsAdapter;
import com.example.romul.bakingapp.Models.Recipe;
import com.example.romul.bakingapp.R;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;
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

        //initIngredientsRecyclerView(rootView);

        initStepsRecyclerView(rootView);

        return rootView;
    }

    private void initStepsRecyclerView(View rootView) {
        RecyclerView rvStepsList = rootView.findViewById(R.id.rv_steps);

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        rvStepsList.setHasFixedSize(true);
        rvStepsList.setLayoutManager(layoutManager);

        DividerItemDecoration decor = new DividerItemDecoration(rootView.getContext(), VERTICAL);
        rvStepsList.addItemDecoration(decor);

        StepsAdapter stepsAdapter = new StepsAdapter();
        rvStepsList.setAdapter(stepsAdapter);

        if (mRecipe.getSteps() != null){
            Log.e(TAG, "initStepsRecyclerView - Steps: " + mRecipe.getSteps().size());
            stepsAdapter.setSteps(mRecipe.getSteps());
        }
    }

    private void initIngredientsRecyclerView(View rootView) {
        RecyclerView rvIngredientsList = rootView.findViewById(R.id.rv_ingredients);

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
        rvIngredientsList.setHasFixedSize(true);
        rvIngredientsList.setLayoutManager(layoutManager);


        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
        rvIngredientsList.setAdapter(ingredientsAdapter);

        if (mRecipe.getIngredients() != null){
            Log.e(TAG, "initIngredientsRecyclerView - Ingredients: " + mRecipe.getIngredients().size());
            ingredientsAdapter.setIngredients(mRecipe.getIngredients());
        }
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
