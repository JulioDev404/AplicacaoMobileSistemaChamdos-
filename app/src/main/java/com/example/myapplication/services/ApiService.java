package com.example.myapplication.services;

// ApiService.java

import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
public interface ApiService {

    // @GET("posts") diz ao Retrofit que esta é uma requisição GET
    // para o endpoint "posts" da nossa URL base.
    @GET("api/mobile/login")
    Call<LoginResponse> getLogin(@Body LoginRequest request);

}
