package com.example.romul.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romul.bakingapp.Models.Step;
import com.example.romul.bakingapp.R;

public class StepDetailLayoutFragment extends Fragment {

    private static final String STEP_KEY = "step_key";

    private Step mStep;

    public StepDetailLayoutFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(STEP_KEY)){
                mStep = (Step) savedInstanceState.getSerializable(STEP_KEY);
            }
        }

        View rootView = inflater.inflate(R.layout.fragment_step_detail_layout, container, false);

        TextView linkTextView = rootView.findViewById(R.id.tv_video_link);
        TextView fullDescriptionTextView = rootView.findViewById(R.id.tv_full_description);

        if (mStep.getVideoURL() != null){
            linkTextView.setText(mStep.getVideoURL());
        }
        if (mStep.getFullDescription() != null){
            fullDescriptionTextView.setText(mStep.getShortDescription());
        }

        return rootView;
    }

    public void setStep(Step mStep) {
        this.mStep = mStep;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(STEP_KEY, mStep);
        super.onSaveInstanceState(outState);
    }
}
