package com.example.romul.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.romul.bakingapp.Adapters.TabsAdapter;
import com.example.romul.bakingapp.Models.Step;
import com.example.romul.bakingapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StepDetailFragment extends Fragment {

    private static final String TAG = StepDetailFragment.class.getSimpleName();
    private static final String LIST_STEPS_KEY = "list_steps_key";
    private static final String STEP_SELECTED_KEY = "step_selected_key";

    private List<Step> mSteps = new ArrayList<>();
    private int mStepSelected;

    public StepDetailFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(LIST_STEPS_KEY) && savedInstanceState.containsKey(STEP_SELECTED_KEY)){
                mSteps = (List<Step>) savedInstanceState.getSerializable(LIST_STEPS_KEY);
                mStepSelected = savedInstanceState.getInt(STEP_SELECTED_KEY);
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        TabsAdapter tabsAdapter = new TabsAdapter(getActivity().getSupportFragmentManager());
        for (Step step: mSteps) {
            StepDetailLayoutFragment stepDetailLayoutFragment = new StepDetailLayoutFragment();
            stepDetailLayoutFragment.setStep(step);

            tabsAdapter.addData(stepDetailLayoutFragment, "Step: " + step.getId());
        }

        //bind of the elements
        ViewPager tabViewPager = rootView.findViewById(R.id.tab_view_pager);
        tabViewPager.setAdapter(tabsAdapter);
        tabViewPager.setCurrentItem(mStepSelected);

        TabLayout tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(tabViewPager);

        return rootView;
    }

    public void setSteps(List<Step> mSteps) {
        this.mSteps = mSteps;
    }

    public void setStepSelected(int mStepSelected) {
        this.mStepSelected = mStepSelected;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(LIST_STEPS_KEY, (Serializable) mSteps);
        outState.putInt(STEP_SELECTED_KEY, mStepSelected);
        super.onSaveInstanceState(outState);
    }
}
