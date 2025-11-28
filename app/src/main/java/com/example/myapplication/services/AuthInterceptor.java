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



        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + token.trim())
                .build();
        Log.e("AUTH_DEBUG", "TOKEN ENVIADO: " + token);
        Log.e("AUTH_DEBUG", "Token enviado: Bearer " + token);
        Log.e("AUTH_DEBUG", "URL chamada: " + chain.request().url());

        return chain.proceed(request);
    }
}
