package com.example.myapplication.services;

// ApiService.java

import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import java.util.Map;

public interface ApiService {

    @POST("api/mobile/auth/login")
    Call<LoginResponse> postlogin(@Body LoginRequest loginRequest);

    @GET("api/alguma-rota-protegida")
    Call<Map<String, Object>> rotaProtegida(
            @Header("Authorization") String token
    );
}
