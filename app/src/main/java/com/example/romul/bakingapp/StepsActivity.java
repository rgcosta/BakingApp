package com.example.romul.bakingapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.romul.bakingapp.Fragments.StepsFragment;
import com.example.romul.bakingapp.Models.Recipe;

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

            this.mRecipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
            Log.e(TAG, "onCreate - Recipe Clicked: " + mRecipe.getName());

            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setRecipe(mRecipe);

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.steps_container, stepsFragment)
                    .commit();

        }
    }
}
