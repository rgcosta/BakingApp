package com.example.romul.bakingapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.romul.bakingapp.API.RecipesApiService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NetworkUtils {

    public final static String RECIPES_BASE_URL =
            "https://d17h27t6h515a5.cloudfront.net/";     //Terminar com / para não causar Exception no Retrofit2

    public final static String FULL_PATH = "topher/2017/May/59121517_baking/baking.json";

    private final Retrofit retrofit;

    public NetworkUtils(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(RECIPES_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public RecipesApiService getRecipesApiService(){
        return this.retrofit.create(RecipesApiService.class);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
