package com.example.myapplication.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageLogin {

    public static final String PREF_NAME = "app_login";
    private static final String KEY_TOKEN = "token"; // Chave que armazena o token

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

    /**
     * Remove o token JWT armazenado nas preferências compartilhadas.
     */
    public void clearToken() {
        // Usa o editor para modificar o SharedPreferences
        prefs.edit()
                .remove(KEY_TOKEN) // Remove a chave do token
                .apply();          // Aplica a mudança de forma assíncrona
    }
}