package com.example.romul.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.romul.bakingapp.Database.AppDatabase;
import com.example.romul.bakingapp.Database.AppExecutors;
import com.example.romul.bakingapp.Models.Ingredient;
import com.example.romul.bakingapp.R;

import java.util.List;

public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }
}


class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = ListRemoteViewsFactory.class.getSimpleName();

    Context mContext;
    List<Ingredient> mIngredients;
    AppDatabase mDb;

    public ListRemoteViewsFactory(Context context){
        this.mContext = context;
    }

    @Override
    public void onCreate() {

    }

    //called on start and when notifyAppWidgetViewDataChanged is called
    @Override
    public void onDataSetChanged() {
        mDb = AppDatabase.getInstance(mContext);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mIngredients = mDb.ingredientDao().loadAllIngredients();
            }
        });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mIngredients == null)
            return 0;
        else
            return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_view_item);

        if (position < mIngredients.size())
            views.setTextViewText(R.id.tv_ingredient_widget, mIngredients.get(position).getIngredient());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}