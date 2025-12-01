package com.example.myapplication.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class AuthInterceptor implements Interceptor {

    private SharedPreferences prefs;

    public AuthInterceptor(Context context) {
        prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        String token = prefs.getString("jwt", null);

        Request original = chain.request();
        Request.Builder builder = original.newBuilder();

        if (token != null && !token.trim().isEmpty()) {
            builder.addHeader("Authorization", "Bearer " + token.trim());
            Log.e("AUTH_DEBUG", "TOKEN ENVIADO: " + token);
        } else {
            Log.e("AUTH_DEBUG", "SEM TOKEN — requisição sem Authorization");
        }

        Log.e("AUTH_DEBUG", "URL chamada: " + original.url());

        return chain.proceed(builder.build());
    }
}
