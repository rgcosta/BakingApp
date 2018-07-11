package com.example.romul.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romul.bakingapp.Models.Recipe;
import com.example.romul.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private List<Recipe> mRecipes = new ArrayList<>();

    public RecipesAdapter(){
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recipes_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        holder.mRecipeNameTextView.setText(mRecipes.get(position).getName());
        holder.mServingsQtdTextView.setText("" + mRecipes.get(position).getServings());
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null)
            return 0;
        else
            return mRecipes.size();
    }

    public void setRecipesData(List<Recipe> recipes){
        this.mRecipes = recipes;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView mRecipeNameTextView;
        TextView mServingsQtdTextView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            this.mRecipeNameTextView = itemView.findViewById(R.id.tv_recipe_name);
            this.mServingsQtdTextView = itemView.findViewById(R.id.tv_servings_qtd);
        }
    }
}
