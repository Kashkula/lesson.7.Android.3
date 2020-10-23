package com.example.lesson3android3.shared;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Prefs {

    private static SharedPreferences sharedPreferences;
    private static final String MAPS_KEY = "Latlng";
    private String name;
    private static Context context;
    public static final String PREFS_KEY = "prefs";

    public static void init(Context context) {
        Prefs.context = context;
        sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    public static void saveLocation(List<LatLng> lng) {
        sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String list = gson.toJson(lng);
        editor.putString(MAPS_KEY, list).apply();
    }

    public static void clear() {
        sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    public static List<LatLng> getLocation(List<LatLng> list) {
        sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        String gsonStr = sharedPreferences.getString(MAPS_KEY, null);
        Type type = new TypeToken<List<LatLng>>() {
        }.getType();
        Gson gson = new Gson();
        list = gson.fromJson(gsonStr, type);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}
