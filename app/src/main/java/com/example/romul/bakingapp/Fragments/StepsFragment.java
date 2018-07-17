package com.example.romul.bakingapp.Fragments;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.romul.bakingapp.Adapters.IngredientsAdapter;
import com.example.romul.bakingapp.Adapters.StepsAdapter;
import com.example.romul.bakingapp.BakingWidgetProvider;
import com.example.romul.bakingapp.Database.AppDatabase;
import com.example.romul.bakingapp.Database.AppExecutors;
import com.example.romul.bakingapp.Models.Ingredient;
import com.example.romul.bakingapp.Models.Recipe;
import com.example.romul.bakingapp.Models.Step;
import com.example.romul.bakingapp.R;
import com.example.romul.bakingapp.StepDetailActivity;

import java.io.Serializable;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;
import static com.example.romul.bakingapp.StepsActivity.EXTRA_RECIPE;
import static com.example.romul.bakingapp.StepsActivity.EXTRA_STEPS;


public class StepsFragment extends Fragment implements StepsAdapter.StepsOnClickHandler {

    private static final String TAG = StepsFragment.class.getSimpleName();

    private Recipe mRecipe;
    private AppDatabase mDb;

    public StepsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_RECIPE)) {
                this.mRecipe = (Recipe) intent.getSerializableExtra(EXTRA_RECIPE);
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        mDb = AppDatabase.getInstance(getContext());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Recipe currentRecipe = mDb.recipeDao().loadRecipe();

                if (currentRecipe == null){
                    mDb.recipeDao().insertRecipe(mRecipe);
                    mDb.ingredientDao().insertIngredients(mRecipe.getIngredients());

                } else if (!currentRecipe.getName().equals(mRecipe.getName())){
                    Log.e(TAG, "Updating ingredients");
                    mDb.ingredientDao().deleteIngredients(mDb.ingredientDao().loadAllIngredients());
                    mDb.ingredientDao().insertIngredients(mRecipe.getIngredients());

                    mRecipe.setIdTable(1);
                    mDb.recipeDao().updateRecipe(mRecipe);

                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
                    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getContext(), BakingWidgetProvider.class));

                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list_view_widget);
                    BakingWidgetProvider.updateAppWidgets(getContext(), appWidgetManager, appWidgetIds);
                }
            }
        });

        initIngredientsRecyclerView(rootView);

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

        StepsAdapter stepsAdapter = new StepsAdapter(this);
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

    @Override
    public void onClick(List<Step> steps, int stepSelected) {

        if (getActivity().findViewById(R.id.second_panel_linear_layout) == null) {
            Log.e(TAG, "onClick - from Mobile");

            Intent intentStepDetail = new Intent(getContext(), StepDetailActivity.class);
            intentStepDetail.putExtra(EXTRA_STEPS, (Serializable) steps);
            intentStepDetail.putExtra(Intent.EXTRA_INDEX, stepSelected);
            intentStepDetail.putExtra(Intent.EXTRA_PACKAGE_NAME, mRecipe.getName());
            startActivity(intentStepDetail);

        } else {
            Log.e(TAG, "onClick - from Tablet");

            ViewPager tabViewPager = getActivity().findViewById(R.id.tab_view_pager);
            tabViewPager.setCurrentItem(stepSelected);
        }
    }
}
