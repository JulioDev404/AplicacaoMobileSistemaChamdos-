package com.example.myapplication.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageLogin {

    private static final String PREF_NAME = "app_login";
    private static final String KEY_TOKEN = "token";

    SharedPreferences prefs;

    public StorageLogin(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }
}

