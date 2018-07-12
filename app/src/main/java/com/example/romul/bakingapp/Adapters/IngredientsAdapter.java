package com.example.romul.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romul.bakingapp.Models.Ingredient;
import com.example.romul.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    List<Ingredient> mIngredients = new ArrayList<>();

    public IngredientsAdapter(){
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ingredients_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        IngredientViewHolder viewHolder = new IngredientViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

        holder.mIngredientNameTextView.setText(mIngredients.get(position).getIngredient());
        holder.mQtdTextView.setText("" + mIngredients.get(position).getQtd());
        holder.mMeasureTextView.setText(mIngredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        if (mIngredients == null)
            return 0;
        else
            return mIngredients.size();
    }

    public void setIngredients(List<Ingredient> mIngredients) {
        this.mIngredients = mIngredients;
        notifyDataSetChanged();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView mIngredientNameTextView;
        TextView mQtdTextView;
        TextView mMeasureTextView;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            this.mIngredientNameTextView = itemView.findViewById(R.id.tv_ingredient_label);
            this.mQtdTextView = itemView.findViewById(R.id.tv_qtd_label);
            this.mMeasureTextView = itemView.findViewById(R.id.tv_mensure_label);
        }
    }
}
