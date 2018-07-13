package com.example.romul.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.romul.bakingapp.Models.Step;
import com.example.romul.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private List<Step> mSteps = new ArrayList<>();
    private StepsOnClickHandler mClickHandler;

    public interface StepsOnClickHandler {
        void onClick(List<Step> steps, int stepSelected);
    }

    public StepsAdapter(StepsOnClickHandler onClickHandler){
        this.mClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.steps_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        StepViewHolder viewHolder = new StepViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {

        holder.mStepNumber.setText("" + mSteps.get(position).getId());
        holder.mStepShortDescription.setText(mSteps.get(position).getShortDescription());
        if (mSteps.get(position).getVideoURL().length() > 0){
            holder.mPlayIcon.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (mSteps == null)
            return 0;
        else
            return mSteps.size();
    }

    public void setSteps(List<Step> mSteps) {
        this.mSteps = mSteps;
        notifyDataSetChanged();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mStepNumber;
        TextView mStepShortDescription;
        ImageView mPlayIcon;

        public StepViewHolder(View itemView) {
            super(itemView);
            this.mStepNumber = itemView.findViewById(R.id.tv_step_number);
            this.mStepShortDescription = itemView.findViewById(R.id.tv_short_description);
            this.mPlayIcon = itemView.findViewById(R.id.iv_play_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(mSteps, adapterPosition);
        }
    }
}
