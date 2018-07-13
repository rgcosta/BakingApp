package com.example.romul.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.romul.bakingapp.Adapters.TabsAdapter;
import com.example.romul.bakingapp.Fragments.StepDetailFragment;
import com.example.romul.bakingapp.Models.Step;

import java.util.ArrayList;
import java.util.List;

import static com.example.romul.bakingapp.StepsActivity.EXTRA_STEPS;

public class StepDetailActivity extends AppCompatActivity {

    private static final String TAG = StepDetailActivity.class.getSimpleName();
    public static final String RECIPE_TITLE_TOOLBAR_KEY = "recipe_title_toolbar_key";

    private List<Step> mSteps = new ArrayList<>();
    private int mStepSelected;
    private String mRecipeTitleToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if (savedInstanceState == null){

            Intent intent = getIntent();
            if (intent != null){
                if (intent.hasExtra(EXTRA_STEPS) && intent.hasExtra(Intent.EXTRA_INDEX)){
                    this.mSteps = (List<Step>) intent.getSerializableExtra(EXTRA_STEPS);
                    this.mStepSelected = intent.getIntExtra(Intent.EXTRA_INDEX, 0);
                    this.mRecipeTitleToolBar = intent.getStringExtra(Intent.EXTRA_PACKAGE_NAME);

                    getSupportActionBar().setTitle(mRecipeTitleToolBar);

                    StepDetailFragment stepDetailFragment = new StepDetailFragment();
                    stepDetailFragment.setSteps(mSteps);
                    stepDetailFragment.setStepSelected(mStepSelected);

                    FragmentManager fragmentManager = getSupportFragmentManager();

                    fragmentManager.beginTransaction()
                            .add(R.id.step_detail_container, stepDetailFragment)
                            .commit();

                }
            }
        } else if (savedInstanceState.containsKey(RECIPE_TITLE_TOOLBAR_KEY)) {
            mRecipeTitleToolBar = savedInstanceState.getString(RECIPE_TITLE_TOOLBAR_KEY);
            getSupportActionBar().setTitle(mRecipeTitleToolBar);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mRecipeTitleToolBar != null)
            outState.putString(RECIPE_TITLE_TOOLBAR_KEY, mRecipeTitleToolBar);
        super.onSaveInstanceState(outState);
    }
}
