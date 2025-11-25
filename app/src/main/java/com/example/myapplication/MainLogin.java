package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.models.Usuario;
import com.example.myapplication.services.ApiService;
import com.example.myapplication.services.RetrofitClient;

import com.example.myapplication.models.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainLogin extends AppCompatActivity {

    EditText editEmail,editSenha;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_login);


        editEmail= findViewById(R.id.edtEmail);
        editSenha = findViewById(R.id.edtSenha);
        button = findViewById(R.id.btnLogin);

        button.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();


            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            fazerLogin(email,senha);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void fazerLogin(String email, String senha){
        ApiService apiService = RetrofitClient.getClient(MainLogin.this).create(ApiService.class);
        LoginRequest loginRequest = new LoginRequest(email,senha);

        Call<LoginResponse> call = apiService.postlogin(loginRequest);

        call.enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if (!response.isSuccessful()) {
                            Toast.makeText(MainLogin.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        LoginResponse loginResponse = response.body();

                        if (loginResponse == null || !loginResponse.isSucesso()) {
                            Toast.makeText(MainLogin.this, "Login inválido", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // PEGAR O TOKEN
                        String token = loginResponse.getToken();

                        // SALVAR O TOKEN
                        getSharedPreferences("app", MODE_PRIVATE)
                                .edit()
                                .putString("jwt", token)
                                .apply();

                        Toast.makeText(MainLogin.this, "Login bem sucedido!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(MainLogin.this, "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}

    