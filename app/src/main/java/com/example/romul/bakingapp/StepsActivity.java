package com.example.romul.bakingapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.romul.bakingapp.Adapters.IngredientsAdapter;
import com.example.romul.bakingapp.Adapters.StepsAdapter;
import com.example.romul.bakingapp.Fragments.StepDetailFragment;
import com.example.romul.bakingapp.Fragments.StepsFragment;
import com.example.romul.bakingapp.Models.Recipe;
import com.example.romul.bakingapp.Models.Step;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_recipe";
    public static final String EXTRA_STEPS = "extra_steps";
    private static final String TAG = StepsActivity.class.getSimpleName();

    private Recipe mRecipe;
    private String mRecipeTitleToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_RECIPE)) {
                this.mRecipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
                this.mRecipeTitleToolBar = mRecipe.getName();
                getSupportActionBar().setTitle(mRecipeTitleToolBar);
            }
        }


        if (findViewById(R.id.second_panel_linear_layout) != null) {

            if (savedInstanceState == null){

                FragmentManager fragmentManager = getSupportFragmentManager();

                StepDetailFragment stepDetailFragment = new StepDetailFragment();
                stepDetailFragment.setSteps(mRecipe.getSteps());
                stepDetailFragment.setStepSelected(0);

                fragmentManager.beginTransaction()
                        .add(R.id.step_detail_container, stepDetailFragment)
                        .commit();
            }

        }
    }
}
