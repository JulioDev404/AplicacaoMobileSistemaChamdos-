package com.example.myapplication.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit = null;

    private  static final String URL_BASE = "";

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(URL_BASE).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
