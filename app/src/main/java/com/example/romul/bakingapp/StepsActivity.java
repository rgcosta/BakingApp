package com.example.romul.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.romul.bakingapp.Fragments.StepsFragment;
import com.example.romul.bakingapp.Models.Recipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_recipe";
    private static final String TAG = StepsActivity.class.getSimpleName();

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);


        if (savedInstanceState == null){

            Intent intent = getIntent();
            if (intent != null) {
                if (intent.hasExtra(EXTRA_RECIPE)){
                    this.mRecipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
                    Log.e(TAG, "onCreate - Recipe Clicked: " + mRecipe.getName());
                    getSupportActionBar().setTitle(mRecipe.getName());

                    StepsFragment stepsFragment = new StepsFragment();
                    stepsFragment.setRecipe(mRecipe);

                    FragmentManager fragmentManager = getSupportFragmentManager();

                    fragmentManager.beginTransaction()
                            .add(R.id.steps_container, stepsFragment)
                            .commit();
                }
            }
        } else if (savedInstanceState.containsKey(EXTRA_RECIPE)) {
            String recipeName = ((Recipe) savedInstanceState.getSerializable(EXTRA_RECIPE)).getName();
            getSupportActionBar().setTitle(recipeName);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mRecipe != null)
            outState.putSerializable(EXTRA_RECIPE, mRecipe);
        super.onSaveInstanceState(outState);
    }
}
