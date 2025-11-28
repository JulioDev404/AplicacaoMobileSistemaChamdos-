package com.example.myapplication.services;

// ApiService.java

import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.models.TicketResponse;
import com.example.myapplication.models.TicketDetalhesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ApiService {

    @POST("api/mobile/auth/login")
    Call<LoginResponse> postlogin(@Body LoginRequest loginRequest);

    @GET("api/mobile/ticket")
    Call<List<TicketResponse>> getTickets();

    @GET("api/mobile/ticketdetalhe/{id}")
    Call<TicketDetalhesResponse> getTicketDetalhe(@Path("id") int id);
}
