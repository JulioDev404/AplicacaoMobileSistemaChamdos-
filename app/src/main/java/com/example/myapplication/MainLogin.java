package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.services.ApiService;
import com.example.myapplication.services.RetrofitClient;

import com.example.myapplication.models.LoginRequest

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLogin extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

private void fazerLogin(String email, String senha){
    ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
    LoginRequest loginRequest = new LoginRequest(email,senha);

    Call<LoginResponse> call = apiService.getLogin(loginRequest);

    call.enqueue(
            new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            }
    );

}